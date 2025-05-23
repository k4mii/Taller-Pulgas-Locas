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


public class PulguinpiumGun extends Weapon{
/**
 * 
 * este metodo  representa el impacto de la pistola en la pulga
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

                }

                // Ya se aplicó el impacto, salir del bucle
                break;
            }
        }
    }

}
