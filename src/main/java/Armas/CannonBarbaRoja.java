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
public class CannonBarbaRoja extends Arma{
    
    // Construtor
    public CannonBarbaRoja(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
        this.coste = Precios.PRECIO_CANNONBARBAROJA;
    }
    
    // Métodos
    @Override
    public void atacar(Grafo atacando, Grafo victima) {
        victima.atacarVertice(targetX, targetY, coste);
        // Más lógica
    }
    
}