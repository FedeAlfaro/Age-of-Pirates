/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.Jugador;
import Mapa.Grafo;

/**
 *
 * @author sotic
 */
public class Cannon extends Arma{

    // Construtor
    public Cannon(){
        super(-1, -1, PRECIO_CANNON);
    }
    
    public Cannon(int targetX, int targetY){
        super(targetX, targetY, PRECIO_CANNON);
    }
    
    // Métodos
    @Override
    public void atacar(Jugador atacando, Jugador victima) {
    }
}
