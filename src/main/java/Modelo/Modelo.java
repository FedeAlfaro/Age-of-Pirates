/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Armas.*;
import Fabricas.Enum.ORIENTACION;
import Jugador.Inventario;
import Jugador.Jugador;
import Mercado.MercadoFunc;
import java.util.ArrayList;

/**
 *
 * @author sotic
 */
public class Modelo {
   
    // atributos
    public ArrayList<Jugador> jugadores;
    public int idJugadorTurno = 1;
    public int maxCantJugadores;
    public int numTurno = 1;    
    
    // construtor
    public Modelo(int maxCantJugadores) {
        this.jugadores = new ArrayList<>();
        this.maxCantJugadores = maxCantJugadores;
    }
    
    // gets y sets
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getIdJugadorTurno() {
        return idJugadorTurno;
    }

    public void setIdJugadorTurno(int idJugadorTurno) {
        this.idJugadorTurno = idJugadorTurno;
    }

    public int getMaxCantJugadores() {
        return maxCantJugadores;
    }

    public void setMaxCantJugadores(int maxCantJugadores) {
        this.maxCantJugadores = maxCantJugadores;
    }

    public int getNumTurno() {
        return numTurno;
    }

    public void setNumTurno(int numTurno) {
        this.numTurno = numTurno;
    }
    
    // Genera datos
    public void generaJugadores(){
        for (int id = 1; id <= maxCantJugadores; id++){
            jugadores.add(new Jugador(id));
        }
    }
    
    // Procesa datos
    public void atacar(int idJugAtacando, int idJugDefensor, int tipoArma, 
            int targetX, int targetY, ORIENTACION orientacion){ // Podría hacerse uno para cada arma y queda mejor
        Jugador atacando = jugadores.get(idJugAtacando);
        Jugador defendiendo = jugadores.get(idJugDefensor);
        Arma arma = FactoryArmas.generaArma(tipoArma);
        if (arma instanceof Bomba bomba){
            bomba.setOrientacion(orientacion);
            bomba.setTargetX(targetX);
            bomba.setTargetY(targetY);
            bomba.atacar(atacando, defendiendo);
        }
        else{
            arma.setTargetX(targetX);
            arma.setTargetY(targetY);
            arma.atacar(atacando, defendiendo);
        }
    }
    
    public void intercambio(int idJugadorTurno, int idJugadorLlamado, boolean comprar,
            Inventario intercambio, int dinero){ // Podría hacerse uno para cada arma y queda mejor
        Jugador jugTurno = jugadores.get(idJugadorTurno);
        Jugador jugLlamado = jugadores.get(idJugadorLlamado);
        MercadoFunc.intercambio(jugTurno, jugLlamado, comprar, intercambio, dinero);
    }
}
