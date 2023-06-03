/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

import Mapa.*;

/**
 *
 * @author sotic
 */
public class Jugador {
 
    // Atributos
    private Grafo mapa;
    private Inventario inventario;
    private int dinero = 4000;
    private boolean vivo = true;
    private String username;
    
    // Constructor
    public Jugador(Grafo mapa, String username) {
        this.mapa = mapa;
        this.inventario = new Inventario();
        this.username = username; 
    }
    
    // Gets y sets
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
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
