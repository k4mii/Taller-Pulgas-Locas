/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import java.awt.Point;

/**
 * Representa la pistola contra las pulgas.
 * 
 * 
 * Hereda de Weapon.
 * @author Valerie Moreno Catañeda
 * @version 1.0.0
 * @since 2025-05-02
 */
public class PulguinpiumGun extends Weapon{
/**
 * 
 * esta funcion representa el impacto de la pistola en la pulga
 */
    @Override
    public void impact(Battlefield battlefield, Point punto) {
        for (Sprite sprite : battlefield.getSprites()) {
            if (sprite instanceof Flea && sprite.checkCollision(punto)) {
                Flea pulga = (Flea) sprite;

                if (pulga instanceof MutantFlea) {
                     Se convierte en una pulga normal (en la misma posición)
                    MutantFlea mutante = (MutantFlea) pulga;

                    NormalFlea nuevaPulga = new NormalFlea(
                        mutante.getX(), mutante.getY(), mutante.getHeight(), mutante.getWidth(),mutante.getStep()
                    );

                    nuevaPulga.setGraphicContainer(mutante.gameContainer); // opcional

                    battlefield.reemplazarPulga(mutante, nuevaPulga);  // método que debes tener
                } else {
                     Pulga normal: se elimina
                    battlefield.eliminarPulga(pulga);
                    battlefield.getPlayer().setPuntaje(
                        battlefield.getPlayer().getPuntaje() + 1
                    );
                }
                break; // solo se afecta una pulga
            }
        }
    }  

}
