/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.*;
import Mapa.Grafo;

/**
 *
 * @author sotic
 */
public abstract class Arma {
    
    public int targetX;
    public int targetY;
    public int coste;
    
    public abstract void atacar(Grafo atacando, Grafo victima);
    
}
