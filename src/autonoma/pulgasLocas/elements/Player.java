package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * Representa el jugador que utilizara el simulador.
 * 
 * 
 * @author Valerie Moreno
 * @version 1.0.0
 * @since 2025-05-02
 */
public class Player extends Sprite{
/**
 * Atributos
 */
/**
 * Atributo de la instancia de la clase Weapon
 */  
    private Weapon armaActual;
/**
 * Atributo del puntaje que tendra el jugador
 */  
    private int puntaje;
/**
 * Atributo de la imagen del  jugador
 */    
    private Image playerImage;
/**
 * Atributo para saber el paso del soldado
 */  
    protected int step = 7;  
/**
 * Constructor
 */  
    public Player(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.armaActual = null;
        this.puntaje = 0;
        
        playerImage = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/soldado.png")).getImage();

    }

/**
     * Metodos de acepso
     */  
    public Weapon getArmaActual() {
        return armaActual;
    }

    public void setArmaActual(Weapon armaActual) {
        this.armaActual = armaActual;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
        public boolean move(int direction)
    {
        int nx = x;
        int ny = y;
        
        switch(direction)
        {
            case KeyEvent.VK_UP:
                ny -= step;
            break;

            case KeyEvent.VK_DOWN:
                ny += step;
            break;

            case KeyEvent.VK_LEFT:
                nx -= step;
            break;

            case KeyEvent.VK_RIGHT:
                nx += step;
            break;
        }
        
        
        if(!isOutOfGraphicContainer(nx, ny, width, height))
        {
            x = nx;
            y = ny;

            if(gameContainer != null)
                gameContainer.refresh();
            
            return true;
        }
        
        return false;
    }
/**
 * Metdo para usar el arma, el cual se le envia una instancia y el punto en cuan se va a discaparar
 */  
    public void usarArma(Battlefield battlefield, Point punto) {
        if (armaActual != null) {
            armaActual.impact(battlefield, punto);
        }
    }

    @Override
    public void paint(Graphics g) {
         g.drawImage(playerImage, x, y, width, height, null);
    }
}
