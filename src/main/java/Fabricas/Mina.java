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
public class Mina extends Fabrica implements IConstants{
    private int costo;
    private int velocidadProduccion;
    private int cantidadAceroMinado;

    public Mina(int x, int y, int orientacion) {
        super(x,y,orientacion);
        this.costo = PRECIO_ARMERIA;
        this.velocidadProduccion = TIEMPO_MINADO;
        this.cantidadAceroMinado = CANTIDAD_ACERO_PRODUCIDA;
        this.nombre = "Armeria";
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getVelocidadProduccion() {
        return velocidadProduccion;
    }

    public void setVelocidadProduccion(int velocidadProduccion) {
        this.velocidadProduccion = velocidadProduccion;
    }

    public int getCantidadAceroMinado() {
        return cantidadAceroMinado;
    }

    public void setCantidadAceroMinado(int cantidadAceroMinado) {
        this.cantidadAceroMinado = cantidadAceroMinado;
    }
    
    @Override
    public void producir(){
        
    }
}
