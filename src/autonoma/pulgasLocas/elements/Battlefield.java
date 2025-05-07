package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Representa el campo de batalla donde ocurre la simulación antipulgas.
 * 
 * Contiene y gestiona multiples sprites como pulgas, armas y el jugador.
 * Define sus límites y provee métodos para actualizar y pintar el campo.
 * 
 * Hereda de SpriteContainer.
 * @author Maria Camila Prada Cortes
 * @version 1.0.0
 * @since 2025-05-02
 */
public  class Battlefield extends SpriteContainer{
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
/**
 * Constructor
 */  
    public Battlefield(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.sprites = new ArrayList<>();
        PulguinpiumGun armaInicial = new PulguinpiumGun();
        this.player = new Player(armaInicial, width / 2, height / 2, 50, 50); 
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
    
    public void addNormalFlea(){
        Flea f = null;
        
        try {
            f = Flea.create(NormalFlea.class, width, height);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sprites.add(f); 
    }
   
/**
 *  Metodo para agregar la pulga mutante al campo de batalla
 */ 
   public void addMutantFlea(){
        Flea f = null;
        
        try {
            f = Flea.create(MutantFlea.class, width, height);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sprites.add(f);  
        refresh();
   }
   
   

/**
 * Metodo para remplazar la pulga si es mutalte y esta resive un impacto este metdo sera llamado y replaza la pu;ga por una norma;
 */  
    public void reemplazarPulga(Flea vieja, Flea nueva) {
        sprites.remove(vieja);
        sprites.add(nueva);
        refresh();
    }
    
    public void eliminarPulga(Flea pulga) {
        sprites.remove(pulga); // o el arreglo/lista que estés usando para almacenar las pulgas
    }
    
    /**
     * Metodo que recorre cada Sprite, verifica que sea de tipo Flea, si es asi, cada pulga
     * se mueve a una nueva posicion aleatoria dentro de los limites del campo de batalla.
     * 
     */
    public void saltarPulga(){
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

    
    
    /**
    *Maneja los eventos del teclado y ejecuta las acciones correspondientes
    *Espacio (SPACE): El jugador usa el arma (pistola o misil), 'this' representa el campo de batalla actual
    *P': Agrega una nueva pulga normal al campo de batalla.
    *'M': Agrega una nueva pulga mutante al campo de batalla.
    *'S': Hace que todas las pulgas salten a una nueva posición aleatoria.
    *Flechas direccionales (opcional): Mueve al jugador (si está implementado)
     */
    
    public void keyPressed(int code){
        if (code == KeyEvent.VK_SPACE) {
            player.setArmaActual(new FleaMissile());
            player.usarArmaMisil(this, null);
            player.setArmaActual(new PulguinpiumGun());
        }

        if(code == KeyEvent.VK_UP |
                code == KeyEvent.VK_DOWN |
                code == KeyEvent.VK_LEFT |
                code == KeyEvent.VK_RIGHT)
        {
            player.move(code);
            refresh();
        }
        
        if(code == KeyEvent.VK_P)
        {
            addNormalFlea();
            refresh();
        }

        if(code == KeyEvent.VK_M)
        {
            addMutantFlea();
            refresh();
        }

        if(code == KeyEvent.VK_S)
        {
            saltarPulga();
            refresh();
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
       for (Sprite sprite : sprites) {
            sprite.paint(g);
        }
        if (player != null) player.paint(g);
     }

    @Override
    public void refresh() {
        if(gameContainer != null)
            gameContainer.refresh();
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
