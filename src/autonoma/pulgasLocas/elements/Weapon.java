/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.pulgasLocas.elements;

import java.awt.Point;

/**
 * Representa las armas de simulador.
 * 
 * este le hereda a las diferentes tipos de armas
 * 
 * @author Valerie Moreno
 * @version 1.0.0
 * @since 2025-05-02
 */
public abstract class Weapon {
    public abstract void impact(Battlefield battlefield, Point punto);
}
