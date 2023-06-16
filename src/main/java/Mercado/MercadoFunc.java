/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mercado;

import Jugador.*;
import java.util.ArrayList;

/**
 *
 * @author sotic
 */
public class MercadoFunc {
    
    // La idea es crear una instancia que haga el intercambio en base a los datos de intercambio del modelo
    // jugadorTurno, jugadorLlamado, boolean comprar, Inventario intercambio, int dinero
    
    public static ArrayList<Jugador> comprar(Jugador jugadorTurno, Jugador jugadorLlamado, 
            Inventario intercambio, int dinero){ // Si se necesita, se puede 
                                                  // devolver un array de los dos Jugadores y luego asignarlos
        ArrayList<Jugador> jugadores = new ArrayList();
        if (jugadorLlamado.getInventario().validaTransaccion(intercambio)){
            jugadorTurno.getInventario().comprar(intercambio);
            jugadorLlamado.getInventario().vender(intercambio);
        }
        jugadores.add(jugadorTurno);
        jugadores.add(jugadorLlamado);
        return jugadores;
    }
    
    public static ArrayList<Jugador> vender(Jugador jugadorTurno, Jugador jugadorLlamado, 
            Inventario intercambio, int dinero){ // Si se necesita, se puede 
                                                  // devolver un array de los dos Jugadores y luego asignarlos
        ArrayList<Jugador> jugadores = new ArrayList();
        if (jugadorTurno.getInventario().validaTransaccion(intercambio)){
            jugadorLlamado.getInventario().comprar(intercambio);
            jugadorTurno.getInventario().vender(intercambio);
        }
        jugadores.add(jugadorTurno);
        jugadores.add(jugadorLlamado);
        return jugadores;
    }
    
    public static void intercambio(Jugador jugadorTurno, Jugador jugadorLlamado, boolean comprar,
            Inventario intercambio, int dinero){ // Si se necesita, se puede 
                                                  // devolver un array de los dos Jugadores y luego asignarlos
        if (comprar){
            comprar(jugadorTurno, jugadorLlamado, intercambio, dinero);
        }
        else{
            vender(jugadorTurno, jugadorLlamado, intercambio, dinero);
        }
        // return ?
    }   
}
