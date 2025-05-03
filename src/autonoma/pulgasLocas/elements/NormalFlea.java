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
public class NormalFlea extends Flea {
    public NormalFlea(int x, int y, int height, int width,int step) {
        super(x, y, height, width,step);
    }

    
    public void registerHit(Battlefield battlefield) {
        detach(battlefield);
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
