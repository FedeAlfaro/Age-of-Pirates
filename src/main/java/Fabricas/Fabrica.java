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
public class Fabrica implements Productor{
    protected String nombre;
    protected int x;
    protected int y;
    protected ORIENTACION orientacion;
    protected boolean visible;

    public Fabrica(int x, int y, int orientacion) {
        this.x = x;
        this.y = y;
        this.orientacion = ORIENTACION.values()[orientacion];
        this.visible = false;
    }

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
    
    public void mostrar(){
        visible = true;
    }
    
    public void noMostrar(){
        visible = false;
    }

    @Override
    public void producir() {
        
    }
    
}
