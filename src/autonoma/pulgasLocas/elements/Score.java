/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import gamebase.elements.Lector;
import java.io.File;

/**
 * Clase que utiliza un lector para leer un puntaje maximo 
 * @author Camila Prada
 * @version 1.0.0
 * @since 2025-05-02
 */
public class Score {
     private Lector lector;

    public Score(Lector lector) {
        this.lector = lector;
    }
     
    public boolean archivoExiste(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }
     
}
