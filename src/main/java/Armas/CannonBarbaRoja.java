/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.Jugador;
import Islas.Grafo;
import java.util.ArrayList;

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
    public ArrayList<Jugador> atacar(Jugador atacando, Jugador victima) {
        ArrayList<Jugador> jugadores = new ArrayList();
        if (victima.getMapa().matriz[targetX][targetY] == CODIGO_REMOLINO){
            ataquesRemolino(atacando);
            ganaFuente(victima, atacando);
        }
        else{
            victima.getMapa().setDatoMatriz(targetX, targetY, CODIGO_DISPARO); 
            for (int i = 0; i < 9; i++){ // 9 disparos más
                int posX = (int)Math.floor(Math.random() * (TAMANO_MATRIZ - 0 + 1) + 0);
                int posY = (int)Math.floor(Math.random() * (TAMANO_MATRIZ - 0 + 1) + 0);
                victima.getMapa().setDatoMatriz(posX, posY, CODIGO_DISPARO);
            }  
        }
        ganaFuente(atacando, victima);
        jugadores.add(atacando);
        jugadores.add(victima);
        return jugadores;
    }
    
}