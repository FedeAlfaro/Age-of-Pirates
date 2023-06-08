/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import General.IConstants;
import Jugador.*;
import Mapa.Grafo;

/**
 *
 * @author sotic
 */
public abstract class Arma implements IConstants{
    
    // Atributos
    protected int targetX;
    protected int targetY;
    protected int coste;
    
    // Constructor
    public Arma(int targetX, int targetY, int coste) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.coste = coste;
    }
    
    // Sets y gets
    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }
    
    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }
    
    // Métodos
    public abstract void atacar(Jugador atacante, Jugador defensor);
    
}
