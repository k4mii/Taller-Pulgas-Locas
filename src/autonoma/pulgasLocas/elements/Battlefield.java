package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

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
    private List<Sprite> sprites;
/**
 * Constructor
 */  
    public Battlefield(Player player, List<Sprite> sprites, int x, int y, int height, int width) {
        super(x, y, height, width);
        this.player = player;
        this.sprites = sprites;
    }
/**
 * Metodos de acepso
 */  
    public Player getPlayer() {
        return player;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }
    
    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void refresh() {
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
/**
 * Metodo para remplazar la pulga si es mutalte y esta resive un impacto este metdo sera llamado y replaza la pu;ga por una norma;
 */  
    public void reemplazarPulga(Flea vieja, Flea nueva) {
        sprites.remove(vieja);
        sprites.add(nueva);
    }
/**
 * Metodo de comando 
 */  
    public void keyPressed(KeyEvent e) {
       
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.usarArma(battlefield, null); // sin punto necesario
        }
    }

}
