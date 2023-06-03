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
public class CannonMultiple extends Arma{
    
    // Construtor
    public CannonMultiple(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
        this.coste = Precios.PRECIO_CANNONMULTIPLE;
    }
    
    // Métodos
    @Override
    public void atacar(Grafo atacando, Grafo victima) {
        victima.atacarVertice(targetX, targetY, coste);
        // Más lógica
    }
    
}
