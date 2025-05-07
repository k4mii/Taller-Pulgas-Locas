package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteMobile;
import java.awt.Point;

/**
 *
 * @author marti
 */
public abstract class Flea extends SpriteMobile {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    
    public Flea(int x, int y, int width, int height) {
        super(x, y, height, width);
        setStep(step);
    }
    public boolean checkCollision(Point punto) {
        return punto.x >= this.getX() && punto.x <= this.getX() + this.getWidth()
            && punto.y >= this.getY() && punto.y <= this.getY() + this.getHeight();
    }
    public static Flea create(Class type, int width, int height) 
            throws InstantiationException, IllegalAccessException
    {
        int x, y;
        boolean overlaps;

        do {
            x = (int)(Math.random() * (width - Flea.WIDTH));
            y = (int)(Math.random() * (height - Flea.HEIGHT));
            overlaps = false;

            // Verifica superposición
            for (Sprite sprite : sprites) {
                if (sprite instanceof Flea) {
                    Flea flea = (Flea) sprite;
                    if (flea.checkCollision(new Point(x, y)) || 
                        flea.checkCollision(new Point(x + Flea.WIDTH, y)) ||
                        flea.checkCollision(new Point(x, y + Flea.HEIGHT)) ||
                        flea.checkCollision(new Point(x + Flea.WIDTH, y + Flea.HEIGHT))) {
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
    */
    public abstract void delete(Battlefield battlefield);
}
