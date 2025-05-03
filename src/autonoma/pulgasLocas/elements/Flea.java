/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import gamebase.elements.SpriteMobile;

/**
 *
 * @author marti
 */
public abstract class Flea extends SpriteMobile {
    
    public Flea(int x, int y, int width, int height, int step) {
        super(x, y, height, width);
        setStep(step);
    }

    // Método que debe implementar cada tipo de pulga (normal o mutante)
    public abstract void detach(Battlefield battlefield);
}
