package pulgaslocasgame;

import autonoma.oulgaslocas.ui.GameWindow;
import autonoma.pulgasLocas.elements.Battlefield;
import autonoma.pulgasLocas.elements.Player;

/**
 *
 * @author Kamii
 */
public class Main {
    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield(0, 0, 500, 500);
        Player player = new Player(100, 100, 50, 50);
        battlefield.setPlayer(player);
        GameWindow window = new GameWindow();
        window.setBattlefield(battlefield);
        battlefield.setGraphicContainer(window);
        window.setSize(500, 500);
        window.setTitle("Gnome Game");
        window.setLocationRelativeTo(null);
        window.setVisible(true);       
    }
}
