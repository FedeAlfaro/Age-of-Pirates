/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

import General.IConstants;

/**
 *
 * @author Usuario
 */
public class FuenteEnergia implements IConstants{
    private int costo;
    private int x;
    private int y;

    public FuenteEnergia(int x, int y) {
        this.costo = PRECIO_FUENTE_ENERGIA;
        this.x = x;
        this.y = y;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
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
    
    
    
    
}
