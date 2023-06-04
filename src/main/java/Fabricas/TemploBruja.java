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
public class TemploBruja extends Fabrica implements IConstants{
    int tiempoComodin;

    public TemploBruja(int x, int y, int orientacion) {
        super(x,y,orientacion);
        this.tiempoComodin = TIEMPO_COMODIN;
        this.nombre = "Templo de brujas";
    }
    
    
}
