package pulgaslocasgame;

import autonoma.oulgaslocas.ui.GameWindow;
import autonoma.pulgasLocas.elements.Battlefield;
import autonoma.pulgasLocas.elements.FleaSpawner;
import autonoma.pulgasLocas.elements.Player;
import autonoma.pulgasLocas.elements.PulguinpiumGun;
import autonoma.pulgasLocas.elements.Weapon;

/**
 *
 * @author Kamii
 */
public class Main {
    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield(0, 0, 500, 500);
        Weapon armaInicial = new PulguinpiumGun();  
        Player player = new Player(armaInicial,100, 100, 50, 50);
        battlefield.setPlayer(player);
        FleaSpawner fleaSpawner = new FleaSpawner(battlefield);
        GameWindow window = new GameWindow();
        window.setBattlefield(battlefield);
        battlefield.setGraphicContainer(window);
        window.setSize(500, 500);
        window.setTitle("Pulgas locas");
        window.setLocationRelativeTo(null);
        window.setVisible(true);       
    }
}
