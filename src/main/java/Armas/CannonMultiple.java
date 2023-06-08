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
public class CannonMultiple extends Arma{
    
    // Construtor
    public CannonMultiple(){
        super(-1, -1, PRECIO_CANNONMULTIPLE);
    }
    
    public CannonMultiple(int targetX, int targetY){
        super(-1, -1, PRECIO_CANNONMULTIPLE);
    }
    
    // Métodos
    @Override
    public void atacar(Jugador atacando, Jugador victima) {
        // Más lógica
    }
    
}
