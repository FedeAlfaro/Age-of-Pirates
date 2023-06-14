/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.Jugador;
import Islas.Grafo;
import java.util.Random;
import Fabricas.Enum.ORIENTACION;

/**
 *
 * @author sotic
 */
public class Bomba extends Arma {
    
    // Atributos
    private ORIENTACION orientacion;

    // Construtor
    public Bomba(){
        super(-1, -1, PRECIO_BOMBA);
        this.orientacion = ORIENTACION.values()[0];
    }
    
    public Bomba(int targetX, int targetY, int orientacion){
        super(targetX, targetY, PRECIO_BOMBA);
        this.orientacion = ORIENTACION.values()[orientacion];
    }
    
    // Gets y sets
    public ORIENTACION getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(ORIENTACION orientacion) {
        this.orientacion = orientacion;
    }
    
    
    // Métodos
    @Override
    public void atacar(Jugador atacando, Jugador victima) {
        if (victima.getMapa().matriz[targetX][targetY] == CODIGO_REMOLINO){
            ataquesRemolino(atacando);
            ganaFuente(victima, atacando);
        }
        else{
            victima.getMapa().setDatoMatriz(targetX, targetY, CODIGO_DISPARO);
            if (orientacion.getValue() == 1){
                if (targetX == TAMANO_MATRIZ-1){
                    victima.getMapa().setDatoMatriz(targetX-1, targetY, CODIGO_DISPARO);
                }
                else{
                    victima.getMapa().setDatoMatriz(targetX+1, targetY, CODIGO_DISPARO);
                }
            }
            else{
                if (targetY == TAMANO_MATRIZ-1){
                    victima.getMapa().setDatoMatriz(targetX, targetY-1, CODIGO_DISPARO);
                }
                else{
                    victima.getMapa().setDatoMatriz(targetX, targetY+1, CODIGO_DISPARO);
                }
            }
        }
        ganaFuente(atacando, victima);
    }
    
}
