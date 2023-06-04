/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Mapa.Grafo;
import java.util.Random;

/**
 *
 * @author sotic
 */
public class Bomba extends Arma{
    
    private boolean horizontal;
    
    // Construtor
    public Bomba(int targetX, int targetY){
        this.targetX = targetX;
        this.targetY = targetY;
        this.coste = PRECIO_CANNON;
        this.horizontal = new Random().nextBoolean(); 
    }
    
    // Métodos
    @Override
    public void atacar(Grafo atacando, Grafo victima) {
        victima.atacarVertice(targetX, targetY, coste);
        if (horizontal){
            victima.atacarVertice(targetX, targetY+1, coste);
        }
        else{
            victima.atacarVertice(targetX+1, targetY, coste);
        }
    }
    
}
