package autonoma.pulgasLocas.elements;

import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;

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

    public Battlefield(int x, int y, int height, int width) {
        super(x, y, height, width);
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
    
}
