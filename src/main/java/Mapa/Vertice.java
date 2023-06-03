/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapa;

import java.util.ArrayList;

/**
 *
 * @author dmora
 */
public class Vertice {
    
    public int dato;
    boolean visitado;
    ArrayList<Vertice> aristas;
    int peso;//peso
    boolean visible;

    // cosntructor
    public Vertice(int dato)
    {
        aristas = new ArrayList<>();
        this.dato = dato;
        this.visitado = false;
        this.visible = false;
        this.peso = 0;
    }

    public Vertice(int dato, int peso)
    {
        aristas = new ArrayList<>();
        this.dato = dato;
        this.visitado = false;
        this.peso = peso;
        this.visible = false;
    }
    
    // Gets y sets
    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public ArrayList<Vertice> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<Vertice> aristas) {
        this.aristas = aristas;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    // Metodos
    public void agregarArista (Vertice arista)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.dato));
    }
    
    public void agregarArista (Vertice arista, int peso)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.dato, peso));
    }

    public int buscarArista(Vertice v)
    {
        for (int i = 0; i < aristas.size(); i++) {
            if (v.dato == aristas.get(i).dato)
                return i;
        }
        return -1;
    }
    
}
