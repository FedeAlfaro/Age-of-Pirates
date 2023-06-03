/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import Jugador.*;

/**
 *
 * @author sotic
 */
public class Modelo {
    
    // Atributos
    ArrayList<Jugador> jugadores;
    
    // Construtor
    public Modelo(){
        jugadores = new ArrayList<>();
    }
    
    // Gets y sets
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    // Métodos manejo datos
    
    
}
