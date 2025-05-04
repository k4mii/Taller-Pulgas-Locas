package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa la pistola contra las pulgas.
 * 
 * 
 * Hereda de Weapon.
 * @author Valerie Moreno Catañeda
 * @version 1.0.0
 * @since 2025-05-02
 */
public class FleaMissile extends Weapon{
/**
 * 
 * esta funcion representa el impacto del misil en la pulga
 */
    @Override
    public void impact(Battlefield battlefield, Point punto) {
        List<Flea> todasPulgas = new ArrayList<>();

        for (Sprite s : battlefield.getSprites()) {
            if (s instanceof Flea) {
                todasPulgas.add((Flea) s);
            }
        }

        int cantidadAEliminar = todasPulgas.size() / 2;

        // Barajamos aleatoriamente la lista
    Collections.shuffle(todasPulgas);

        for (int i = 0; i < cantidadAEliminar; i++) {
            Flea pulga = todasPulgas.get(i);

            if (pulga instanceof MutantFlea) {
                MutantFlea mutante = (MutantFlea) pulga;

                NormalFlea nuevaPulga = new NormalFlea(
                    mutante.getX(), mutante.getY(), mutante.getHeight(), mutante.getWidth()
                );

                battlefield.reemplazarPulga(mutante, nuevaPulga);
            } else {
                battlefield.eliminarPulga(pulga);

                // Sumar puntaje
                battlefield.getPlayer().setPuntaje(
                    battlefield.getPlayer().getPuntaje() + 1
                );
            }
        }
    }
}
