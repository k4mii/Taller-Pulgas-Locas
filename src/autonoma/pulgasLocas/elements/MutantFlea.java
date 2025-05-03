/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author marti
 */
public class MutantFlea extends Flea {
    private Image mutantFlea;
    private int hitsReceived;

    public MutantFlea(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.hitsReceived = 0;
        
        mutantFlea = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/images/PulgaMutante.png")).getImage();

    }

    public void registerHit(Battlefield battlefield) {
        hitsReceived++;
        if (hitsReceived >= 2) {
            delete(battlefield);
        }
    }    

   @Override
    public void delete(Battlefield battlefield) {
        battlefield.remove(this);
    } 

    @Override
    public void paint(Graphics g) {
        g.drawImage(mutantFlea, x, y, null);
    }
}
