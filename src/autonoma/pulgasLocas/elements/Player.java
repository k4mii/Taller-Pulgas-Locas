/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import java.awt.Point;

/**
 * Representa el jugador que utilizara el simulador.
 * 
 * 
 * @author Valerie Moreno
 * @version 1.0.0
 * @since 2025-05-02
 */
public class Player {
/**
 * Atributos
 */
/**
 * Atributo de la instancia de la clase Weapon
 */  
    private Weapon armaActual;
/**
 * Atributo del puntaje que tendra el jugador
 */  
    private int puntaje;
/**
 * Constructor
 */  
    public Player(Weapon armaActual, int puntaje) {
        this.armaActual = armaActual;
        this.puntaje = puntaje;
    }
/**
 * Metodos de acepso 
 */  
    public Weapon getArmaActual() {
        return armaActual;
    }

    public void setArmaActual(Weapon armaActual) {
        this.armaActual = armaActual;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
/**
 * Metdo para usar el arma, el cual se le envia una instancia y el punto en cuan se va a discaparar
 */  
    public void usarArma(Battlefield battlefield, Point punto) {
        if (armaActual != null) {
            armaActual.impact(battlefield, punto);
        }
    }
}
