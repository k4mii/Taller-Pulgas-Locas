
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
 *
 * @author Valerie Moreno Catañeda
 * @version 1.0.0
 * @since 2025-05-02
 */
<<<<<<< HEAD
public class PulguinpiumGun extends Weapon {

    /**
     *
     * esta funcion representa el impacto de la pistola en la pulga
     */
    @Override

public class PulguinpiumGun extends Weapon{
/**
 * 
 * esta funcion representa el impacto de la pistola en la pulga
 */
   @Override

    public void impact(Battlefield battlefield, Point punto) {
        for (Sprite s : battlefield.getSprites()) {
            if (punto != null && s.getBoundaries().contains(punto)) {
                if (s instanceof MutantFlea) {
                    // Si es mutante, convertirla en normal
                    MutantFlea mutante = (MutantFlea) s;
                    NormalFlea nuevaPulga = new NormalFlea(
                        mutante.getX(), mutante.getY(), mutante.getHeight(), mutante.getWidth()
                    );
                    battlefield.reemplazarPulga(mutante, nuevaPulga);
                } else if (s instanceof Flea) {
                    // Si es pulga normal, eliminar y sumar puntaje
                    battlefield.eliminarPulga((Flea) s);

                    break;

                    battlefield.getPlayer().setPuntaje(
                        battlefield.getPlayer().getPuntaje() + 1
                    );

                }

                // Ya se aplicó el impacto, salir del bucle
                break;
            }
        }
    }

}
