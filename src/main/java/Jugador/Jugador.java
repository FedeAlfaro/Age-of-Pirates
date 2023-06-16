/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import Fabricas.Mina;
import Fabricas.TemploBruja;
import General.IConstants;
import Islas.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sotic
 */
public class Jugador implements IConstants{
 
    // Atributos
    private Grafo mapa;
    public Inventario inventario;
    private int dinero;
    private boolean vivo;
    private int id;
    private int numeroComponente;
    
    // Constructor
    public Jugador(int id) {
        vivo = true;
        dinero = DINERO_INICIAL;
        this.mapa = new Grafo();
        this.inventario = new Inventario();
        this.id = id; 
        numeroComponente = 0;
    }
    
    // Gets y sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Grafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo mapa) {
        this.mapa = mapa;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public boolean getVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public int getNumeroComponente(){
        numeroComponente++;
        return numeroComponente-1;
    }
    
    public boolean recoger(){
        for(Vertice vertice: mapa.getVertices()){
            try {
                if(vertice.getFabrica().getClass() == Mina.class){
                    Mina mina = (Mina)vertice.getFabrica();
                    inventario.incrementarAcero(mina.recogerAcero());
                    return true;
                }else if(vertice.getFabrica().getClass() == TemploBruja.class){
                    TemploBruja temploBruja = (TemploBruja)vertice.getFabrica();
                    return true;
                }
            } catch (Exception ex) {
                System.out.println("Error al parsear fabrica");
            }
        }
        return false;
    }
}
