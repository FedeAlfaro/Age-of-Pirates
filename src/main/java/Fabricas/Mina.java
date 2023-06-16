/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import General.IConstants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Mina extends Fabrica implements IConstants,Runnable{
    private int costo;
    private int velocidadProduccion;
    private int cantidadAceroMinado;
    private Thread miHilo;
    private int aceroActual;
    private boolean running;

    public Mina(int x, int y, int orientacion) {
        super(x,y,orientacion);
        this.costo = PRECIO_ARMERIA;
        this.velocidadProduccion = TIEMPO_MINADO;
        this.cantidadAceroMinado = CANTIDAD_ACERO_PRODUCIDA;
        this.nombre = "Mina";
        this.aceroActual = 0;
        this.running = true;
    }

    public int getAceroActual() {
        return aceroActual;
    }

    public void setAceroActual(int aceroActual) {
        this.aceroActual = aceroActual;
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
    
    public void ejecutar() {
        if (miHilo == null) {
            miHilo = new Thread(this);
            miHilo.start();
        }
    }
    
    @Override
    public void producir(){
        
    }

    @Override
    public void run() {
        while(running){
            try {
                Thread.sleep(velocidadProduccion);
                System.out.println("Se produce acero");
            } catch (InterruptedException ex) {
                Logger.getLogger(Mina.class.getName()).log(Level.SEVERE, null, ex);
            }
            aceroActual+=cantidadAceroMinado;
        }
        
    }
    
    public void stop(){
        running = false;
        miHilo = null;
    }        
    
    public int recogerAcero(){
        int acero = aceroActual;
        aceroActual = 0;
        return acero;
    }
            
}
