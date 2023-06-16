/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.Jugador;
import Mapa.Grafo;
import General.IConstants;
import java.util.ArrayList;

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
    public ArrayList<Jugador> atacar(Jugador atacando, Jugador victima) {
        ArrayList<Jugador> jugadores = new ArrayList();
        if (victima.getMapa().matriz[targetX][targetY] == CODIGO_REMOLINO){
            ataquesRemolino(atacando);
            ganaFuente(victima, atacando);
        }
        else{
            victima.getMapa().setDatoMatriz(targetX, targetY, CODIGO_DISPARO);
            ganaFuente(atacando, victima);
        }
        jugadores.add(atacando);
        jugadores.add(victima);
        return jugadores;
    }
}
