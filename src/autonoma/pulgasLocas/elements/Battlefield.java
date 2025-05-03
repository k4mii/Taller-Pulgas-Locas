package autonoma.pulgasLocas.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 /**
 * Atributos
 */  
    
/**
 * Atributo de la instancia de la clase Player
 */  
    private Player player;
/**
 * Atributo la lista de sprite que contiene las pulgas
 */  
    private ArrayList<Sprite> sprites;
/**
 * Constructor
 */  
    public Battlefield(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.player = player;
        this.sprites = sprites;
    }
/**
 * Metodos de acceso
 */  
    public Player getPlayer() {
        return player;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }
    /**
     * Metodo para agregar la pulga normal al campo de batalla
     */ 
    
    public void addNormalFlea(){
        Flea f = null;
        
        try {
            f = Flea.create(NormalFlea.class, width, height);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sprites.add(f);      
    }
   
/**
 *  Metodo para agregar la pulga mutante al campo de batalla
 */ 
   public void addMutantFlea(){
        Flea f = null;
        
        try {
            f = Flea.create(MutantFlea.class, width, height);
        } catch (InstantiationException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Battlefield.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sprites.add(f);      
   }
   
   

/**
 * Metodo para remplazar la pulga si es mutalte y esta resive un impacto este metdo sera llamado y replaza la pu;ga por una norma;
 */  
    public void reemplazarPulga(Flea vieja, Flea nueva) {
        sprites.remove(vieja);
        sprites.add(nueva);
    }
/**
 * Metodo de comando 
 */  
    public void keyPressed(KeyEvent e) {
       
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.usarArma(battlefield, null); // sin punto necesario
        }
    }
    public void eliminarPulga(Flea pulga) {
        sprites.remove(pulga); // o el arreglo/lista que estés usando para almacenar las pulgas
    }
    
    public void saltarPulga(){
        
    }
    
        public void keyPressed(int code)
    {
        if(code == KeyEvent.VK_UP |
           code == KeyEvent.VK_DOWN |
           code == KeyEvent.VK_LEFT |
           code == KeyEvent.VK_RIGHT)
        {
            if(flea.move(code))
            {
                processMushroomsEaten();
            }
        }
        
        if(code == KeyEvent.VK_P)
        {
            addNormalFlea();
            refresh();
        }

        if(code == KeyEvent.VK_M)
        {
            addMutantFlea();
            refresh();
        }

        if(code == KeyEvent.VK_S)
        {
            addTroll();
            refresh();
        }
    }
        
    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void refresh() {
        if(gameContainer != null)
            gameContainer.refresh();
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
