/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gamebase.elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Camila prada
 * @version 1.0.0
 * @since 2025-05-02
 */
public interface Escritor {
        /**
     * Escribe el archivo de memoria interna por lineas
     * 
     * @param archivo
     * @throws java.io.IOException si el archivo no existe
     */
    public abstract void escribir(ArrayList<String> archivo) throws IOException ;
}
