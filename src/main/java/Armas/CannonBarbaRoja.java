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
public class CannonBarbaRoja extends Arma{
    
    // Construtor
    public CannonBarbaRoja(){
        super(-1, -1, PRECIO_CANNONBARBAROJA);
    }
    
    public CannonBarbaRoja(int targetX, int targetY){
        super(targetX, targetY, PRECIO_CANNONBARBAROJA);
    }
    
    // Métodos
    @Override
    public void atacar(Jugador atacando, Jugador victima) {
        // Más lógica
    }
    
}