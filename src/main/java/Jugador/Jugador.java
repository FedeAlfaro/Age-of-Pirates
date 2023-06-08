/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import General.IConstants;
import Islas.*;

/**
 *
 * @author sotic
 */
public class Jugador implements IConstants{
 
    // Atributos
    private Grafo mapa;
    private Inventario inventario;
    private int dinero;
    private boolean vivo;
    private int id;
    
    // Constructor
    public Jugador(int id) {
        vivo = true;
        dinero = DINERO_INICIAL;
        this.mapa = new Grafo();
        this.inventario = new Inventario();
        this.id = id; 
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
}
