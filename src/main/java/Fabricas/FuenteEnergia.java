/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import General.IConstants;

/**
 *
 * @author Usuario
 */
public class FuenteEnergia extends Fabrica implements IConstants{
    private int costo;

    public FuenteEnergia(int x, int y) {
        super(x,y,0);
        this.costo = PRECIO_FUENTE_ENERGIA;
        this.x = x;
        this.y = y;
        this.nombre = "Fuente de energia";
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
