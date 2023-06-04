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
public class Armeria extends Fabrica implements IConstants{
    private int costo;
    private int velocidadProduccion;

    public Armeria(int x, int y, int orientacion) {
        super(x,y,orientacion);
        this.costo = PRECIO_ARMERIA;
        this.velocidadProduccion = velocidadProduccion;
        this.nombre = "Armeria";
    }

    
    
}
