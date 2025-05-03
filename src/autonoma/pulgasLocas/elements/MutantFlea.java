/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import java.awt.Graphics;

/**
 *
 * @author marti
 */
public class MutantFlea extends Flea {

    private int hitsReceived;

    public MutantFlea(int x, int y, int height, int width,int step) {
        super(x, y, height, width,step);
        this.hitsReceived = 0;
    }

    public void registerHit(Battlefield battlefield) {
        hitsReceived++;
        if (hitsReceived >= 2) {
            detach(battlefield);
        }
    }    

   @Override
    public void detach(Battlefield battlefield) {
        battlefield.remove(this);
    } 

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
