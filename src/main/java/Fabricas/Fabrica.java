/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import Fabricas.Enum.ORIENTACION;

/**
 *
 * @author Usuario
 */
public class Fabrica {
    protected int x;
    protected int y;
    protected ORIENTACION orientacion;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ORIENTACION getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(ORIENTACION orientacion) {
        this.orientacion = orientacion;
    }
    
    
}
