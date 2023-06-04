/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Mapa.Grafo;

/**
 *
 * @author sotic
 */
public class Cannon extends Arma{

    // Construtor
    public Cannon(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
        this.coste = Precios.PRECIO_CANNON;
    }
    
    // Métodos
    @Override
    public void atacar(Grafo atacando, Grafo victima) {
        victima.atacarVertice(targetX, targetY, coste);
    }
}
