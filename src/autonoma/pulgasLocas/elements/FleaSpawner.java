package autonoma.pulgasLocas.elements;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Encargado de generar pulgas normales y mutantes de forma automática en
 * intervalos de tiempo definidos dentro del campo de batalla.
 *
 * Implementa Runnable para ejecutarse en un hilo independiente.
 *
 * @author Maria Camila Prada Cortes
 * @version 1.0.0
 * @since 2025-05-02
 */
public class FleaSpawner implements Runnable {

    private Battlefield battlefield;
    private Image normalFleaImage;
    private Image mutantFlea;

    protected long delay;
    private boolean running;
    private boolean paused;
    protected Thread thread;

    public FleaSpawner(Battlefield battlefield) {
        this.battlefield = battlefield;
        running = false;
        paused = false;
    }

    /**
     * Método que ejecuta el hilo del generador de pulgas.
     * Este método se ejecuta en un hilo independiente y se encarga de generar
     * pulgas normales y mutantes en intervalos regulares de tiempo. El
     * generador de pulgas puede ser pausado o reanudado. Cada tipo de pulga es
     * añadida al campo de batalla de acuerdo con un intervalo de 5 segundos,
     * siempre que el juego no esté en pausa.
     * El proceso sigue estos pasos:
     *Esperar 5 segundos.
     * Si el juego no está pausado, agregar una pulga normal al campo de
     * batalla.
     *Esperar otros 5 segundos.
     * Si el juego no está pausado, agregar una pulga mutante al campo de
     * batalla.
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(5000);
                if (!paused) {

                    normalFleaImage = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/PulgaNormal.png")).getImage();
                    battlefield.addNormalFlea();
                }
                Thread.sleep(5000);
                if (!paused) {

                    mutantFlea = new ImageIcon(getClass().getResource("/autonoma/pulgasLocas/imagen/PulgaMutante.png")).getImage();
                    battlefield.addMutantFlea();
                }
            } catch (InterruptedException ex) {

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

    /**
     * Inicia la ejecución del hilo asociado a esta clase. Este método crea un
     * nuevo hilo y lo inicia, ejecutando la lógica definida en el método Este
     * hilo permite que el proceso se ejecute de manera concurrente sin bloquear
     * el hilo principal de la aplicación, permitiendo realizar tareas en
     * segundo plano o en paralelo.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

}
