package autonoma.pulgasLocas.elements;

import autonoma.oulgaslocas.ui.GameWindow;
import gamebase.elements.Sprite;
import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import gamebase.elements.EscritorArchivoTextoPlano;
import gamebase.elements.LectorArchivoTextoPlano;
import java.io.IOException;
import java.util.Collections;

/**
 * Representa el campo de batalla donde ocurre la simulación antipulgas.
 *
 * Contiene y gestiona multiples sprites como pulgas, armas y el jugador. Define
 * sus límites y provee métodos para actualizar y pintar el campo.
 *
 * Hereda de SpriteContainer.
 *
 * @author Maria Camila Prada Cortes
 * @version 1.0.0
 * @since 2025-05-02
 */
public class Battlefield extends SpriteContainer {

    /**
     * Atributos
     */
    /**
     * Atributo de la instancia de la clase Player
     */
    private Player player;

    /**
     * Atributo la lista de sprite que contiene las pulgas
     */
    private ArrayList<Sprite> sprites;

    protected FleaSpawner fleaSpawner;

    String[] options = {"Sí", "No"};

    private EscritorArchivoTextoPlano escritor;
    private LectorArchivoTextoPlano lector;

    private int maxScore = 0;

    /**
     * Constructor
     */
    public Battlefield(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.sprites = new ArrayList<>();
        PulguinpiumGun armaInicial = new PulguinpiumGun();
        this.fleaSpawner = new FleaSpawner(this);
        this.fleaSpawner.start();
        this.escritor = new EscritorArchivoTextoPlano("Score.txt");
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Metodos de acceso
     */
    public Player getPlayer() {
        return player;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Metodo para agregar la pulga normal al campo de batalla
     */
    public void addNormalFlea() {
        Flea f = null;

        try {
            f = Flea.create(NormalFlea.class, width, height, sprites);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }

        sprites.add(f);
        refresh();
    }

    /**
     * Metodo para agregar la pulga mutante al campo de batalla
     */
    public void addMutantFlea() {
        Flea f = null;

        try {
            f = Flea.create(MutantFlea.class, width, height, sprites);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }

        sprites.add(f);
        refresh();
    }

    /**
     * Metodo para remplazar la pulga si es mutalte y esta resive un impacto
     * este metdo sera llamado y replaza la pu;ga por una norma;
     */
    public void reemplazarPulga(Flea vieja, Flea nueva) {
        sprites.remove(vieja);
        sprites.add(nueva);
        refresh();
    }

    public void eliminarPulga(Flea pulga) {
        // Aumenta el puntaje del jugador por cada pulga eliminada
        this.player.aumentarPuntaje(1);
        sprites.remove(pulga);

        // Si ya no hay pulgas en el campo de batalla, manejar el fin de partida
        if (this.sprites.size() == 0) {
            manejarFinDePartida();
        }
    }

    private void manejarFinDePartida() {
        // Detenemos el generador de pulgas
        this.fleaSpawner.stop();
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Quieres reiniciar la partida?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        boolean continuar = (opcion == JOptionPane.YES_OPTION);
        if (continuar) {
            // Reiniciamos el puntaje y refrescamos el campo de batalla
            this.player.setPuntaje(0);
            this.refresh();
            this.fleaSpawner.start();  
        } else {
            JOptionPane.showMessageDialog(null, " Gracias por jugar :)" );
            try {
                // Guardamos el puntaje
                Score score = new Score("puntajes.txt");
                score.guardarPuntaje(player.getPuntaje());

                // Leemos todos los puntajes guardados
                ArrayList<Integer> puntajes = score.leerPuntajes();

                // Si el puntaje actual es el más alto, actualizamos el puntaje máximo
                if (!puntajes.isEmpty()) {
                    int nuevoMax = Collections.max(puntajes);
                    if (nuevoMax > maxScore) {
                        maxScore = nuevoMax;
                    }
                }

                // Actualizamos la pantalla con el nuevo puntaje máximo
                if (gameContainer instanceof GameWindow) {
                    ((GameWindow) gameContainer).setMaxScore(maxScore);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar puntaje: " + e.getMessage());
            }
            System.exit(0);
        }
        
    }

    /**
     * Metodo que recorre cada Sprite, verifica que sea de tipo Flea, si es asi,
     * cada pulga se mueve a una nueva posicion aleatoria dentro de los limites
     * del campo de batalla.
     *
     */
    public void saltarPulga() {
        for (Sprite sprite : sprites) {
            if (sprite instanceof Flea) {
                boolean posicionValida = false;
                int nuevaX = 0;
                int nuevaY = 0;

                for (int intentos = 0; intentos < 100; intentos++) {
                    nuevaX = (int) (Math.random() * (width - sprite.getWidth()));
                    nuevaY = (int) (Math.random() * (height - sprite.getHeight()));
                    Rectangle nuevaPosicion = new Rectangle(nuevaX, nuevaY, sprite.getWidth(), sprite.getHeight());

                    boolean overlap = false;
                    for (Sprite otro : sprites) {
                        if (otro != sprite && otro instanceof Flea) {
                            Rectangle otraPulga = new Rectangle(otro.getX(), otro.getY(), otro.getWidth(), otro.getHeight());
                            if (nuevaPosicion.intersects(otraPulga)) {
                                overlap = true;
                                break;
                            }
                        }
                    }

                    if (!overlap) {
                        posicionValida = true;
                        break;
                    }
                }

                if (posicionValida) {
                    sprite.setX(nuevaX);
                    sprite.setY(nuevaY);
                }
            }
        }
        refresh();

    }

    /**
     * Maneja los eventos del teclado y ejecuta las acciones correspondientes
     * Espacio (SPACE): El jugador usa el arma (pistola o misil), 'this'
     * representa el campo de batalla actual P': Agrega una nueva pulga normal
     * al campo de batalla. 'M': Agrega una nueva pulga mutante al campo de
     * batalla. 'S': Hace que todas las pulgas salten a una nueva posición
     * aleatoria. Flechas direccionales (opcional): Mueve al jugador (si está
     * implementado)
     */
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_SPACE) {
            player.setArmaActual(new FleaMissile());
            player.usarArmaMisil(this, null);
            player.setArmaActual(new PulguinpiumGun());
        }
        if (code == KeyEvent.VK_P) {
            addNormalFlea();
            refresh();
        }

        if (code == KeyEvent.VK_M) {
            addMutantFlea();
            refresh();
        }

        if (code == KeyEvent.VK_S) {
            saltarPulga();
            refresh();
        }

    }

    @Override
    public void paint(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.paint(g);
        }
    }

    @Override
    public void refresh() {
        if (gameContainer != null) {
            gameContainer.refresh();
        }
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
