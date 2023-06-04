/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

import Fabricas.Fabrica;
import java.util.ArrayList;

/**
 *
 * @author dmora
 */
public class Vertice {
    public int dato;
    boolean visitado;
    ArrayList<Vertice> aristas;
    ArrayList<Conector> conectores;
    int peso;//peso
    Fabrica fabrica;

    // cosntructor
    public Vertice(int dato)
    {
        aristas = new ArrayList<Vertice>();
        conectores = new ArrayList<Conector>();
        this.dato = dato;
        this.visitado = false;
    }

    public Vertice(int dato, int peso)
    {
        aristas = new ArrayList<Vertice>();
        this.dato = dato;
        this.visitado = false;
        this.peso = peso;
    }
    
    public Vertice(int dato, Fabrica _fabrica)
    {
        aristas = new ArrayList<Vertice>();
        this.dato = dato;
        this.visitado = false;
        this.fabrica = _fabrica;
    }

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
    
    public boolean agregarArista (Vertice arista, Fabrica _fabrica)
    {
        if(buscarArista(arista) == -1){
            // si no está la arista para no repetir
            if (conectores.stream().anyMatch(p->p.conectar(arista)))
                aristas.add(new Vertice(arista.dato, _fabrica));
                return true;
        }
        return false;
    }

    public int buscarArista(Vertice v)
    {
        for (int i = 0; i < aristas.size(); i++) {
            if (v.dato == aristas.get(i).dato)
                return i;
        }
        return -1;
    }
    
    public void mostrarTodo(){
        conectores.stream().forEach(p->p.mostrar());
        fabrica.mostrar();
    }
}

