package autonoma.pulgasLocas.elements;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Encargado de generar pulgas normales y mutantes de forma automática
 * en intervalos de tiempo definidos dentro del campo de batalla.
 * 
 * Implementa Runnable para ejecutarse en un hilo independiente.
 * @author Maria Camila Prada Cortes
 * @version 1.0.0
 * @since 2025-05-02
 */
public class FleaSpawner implements Runnable{
    private Battlefield battlefield;
    private Image normalFleaImage;
    private Image mutantFlea;
    
    protected long delay;
    private boolean running;
    private boolean paused;
    protected Thread thread;

    public FleaSpawner(Battlefield battlefield) {
        this.battlefield = battlefield;       
        thread = new Thread(this);
        thread.start();
        
        running = false;
        paused = false;
    }
    


    @Override
    public void run() {
        running = true;
        while(running){
            try{
                Thread.sleep(5000);
                if(!paused){
                    System.out.println("Agregando pulga normal...");
                    normalFleaImage = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/PulgaNormal.png")).getImage();
                    battlefield.addNormalFlea();
                }
                Thread.sleep(5000);
                if(!paused){
                    System.out.println("Agregando pulga mutante...");
                    mutantFlea = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/PulgaMutante.png")).getImage();
                    battlefield.addMutantFlea();
                }
            }catch(InterruptedException ex){
                
            }

        }
    }
    
    
    
    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
    
}
