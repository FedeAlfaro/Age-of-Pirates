/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

import Jugador.Jugador;
import Mapa.Grafo;
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
        /*
        victima.atacarVertice(targetX, targetY, coste);
        if (horizontal){
            victima.atacarVertice(targetX, targetY+1, coste);
        }
        else{
            victima.atacarVertice(targetX+1, targetY, coste);
        }
 */
    }
    
}
