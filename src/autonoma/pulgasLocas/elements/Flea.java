package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteMobile;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public abstract class Flea extends SpriteMobile {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    
    /**
     * Constructor de la clase Flea.
     * 
     * @param x Coordenada x inicial de la pulga.
     * @param y Coordenada y inicial de la pulga.
     * @param width Ancho de la pulga.
     * @param height Alto de la pulga.
     */
    public Flea(int x, int y, int width, int height) {
        super(x, y, height, width);
        setStep(step);
    }
    /**
     * Verifica si un punto dado está dentro del área ocupada por la pulga.
     * 
     * @param punto 
     * @return true si el punto está dentro del área de la pulga, false en caso contrario.
     */
    public boolean checkCollision(Point punto) {
        return punto.x >= this.getX() && punto.x <= this.getX() + this.getWidth()
            && punto.y >= this.getY() && punto.y <= this.getY() + this.getHeight();
    }
    
    /**
     * Método estático crear una nueva pulga de tipo específico (NormalFlea o MutantFlea).
     * Evita que la nueva pulga se superponga con otras existentes.
     * 
     * @param type Tipo de la clase de la pulga a crear (NormalFlea o MutantFlea).
     * @param width Ancho del área donde se colocará la pulga.
     * @param height Alto del área donde se colocará la pulga.
     * @param sprites Lista de sprites existentes para verificar superposición.
     * @return Un objeto de tipo Flea (NormalFlea o MutantFlea).
     * 
     */
    public static Flea create(Class type, int width, int height,List<Sprite> sprites) 
            throws InstantiationException, IllegalAccessException
    {
        int x, y;
        boolean overlaps;

        do {
            x = (int)(Math.random() * (width - Flea.WIDTH));
            y = (int)(Math.random() * (height - Flea.HEIGHT));
            overlaps = false;

            Rectangle newFleaRect = new Rectangle(x, y, Flea.WIDTH, Flea.HEIGHT);

            for (Sprite sprite : sprites) {
                if (sprite instanceof Flea) {
                    Rectangle existingFleaRect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
                    if (newFleaRect.intersects(existingFleaRect)) {
                        overlaps = true;
                        break;
                    }
                }
            }
        } while (overlaps);

        Flea m = null;

        if(type.equals(NormalFlea.class))
            m = new NormalFlea(x, y, Flea.WIDTH, Flea.HEIGHT);
        else if(type.equals(MutantFlea.class))
            m = new MutantFlea(x, y, Flea.WIDTH, Flea.HEIGHT);
        else
            System.out.println("ERROR: Flea.create unknown type of flea");

        return m;
    } 
    /**
    *Método que debe implementar cada tipo de pulga (normal o mutante)
     * @param battlefield
    */
    public abstract void delete(Battlefield battlefield);
}
