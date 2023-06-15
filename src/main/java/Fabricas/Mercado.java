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
public class Mercado extends Fabrica implements IConstants{
    
    int costo;
    
    public Mercado(int x, int y, int orientacion) {
        super(x, y, orientacion);
        costo = PRECIO_MERCADO;
    }
    
}
