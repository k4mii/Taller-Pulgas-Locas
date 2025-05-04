package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author marti
 */
public class NormalFlea extends Flea {
    private Image normalFleaImage;
    public NormalFlea(int x, int y, int height, int width) {
        super(x, y, height, width);
        normalFleaImage = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/PulgaNormal.png")).getImage();
       
    }

    
    public void registerHit(Battlefield battlefield) {
        delete(battlefield);
    }

    @Override
    public void delete(Battlefield battlefield) {
        battlefield.eliminarPulga(this);
        battlefield.getPlayer().setPuntaje(
        battlefield.getPlayer().getPuntaje() + 1
        );
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(normalFleaImage, x, y, null);
    }
}
