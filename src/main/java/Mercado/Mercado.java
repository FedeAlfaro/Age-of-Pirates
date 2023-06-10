/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mercado;

import Jugador.*;

/**
 *
 * @author sotic
 */
public class Mercado {
    
    // La idea es crear una instancia que haga el intercambio en base a los datos de intercambio del modelo
    // jugadorTurno, jugadorLlamado, boolean comprar, Inventario intercambio, int dinero
    
    public static void comprar(Jugador jugadorTurno, Jugador jugadorLlamado, 
            Inventario intercambio, int dinero){ // Si se necesita, se puede 
                                                  // devolver un array de los dos Jugadores y luego asignarlos
        if (jugadorLlamado.getInventario().validaTransaccion(intercambio)){
            jugadorTurno.getInventario().comprar(intercambio);
            jugadorLlamado.getInventario().vender(intercambio);
        }                                      
    }
    
    public static void vender(Jugador jugadorTurno, Jugador jugadorLlamado, 
            Inventario intercambio, int dinero){ // Si se necesita, se puede 
                                                  // devolver un array de los dos Jugadores y luego asignarlos
        if (jugadorTurno.getInventario().validaTransaccion(intercambio)){
            jugadorLlamado.getInventario().comprar(intercambio);
            jugadorTurno.getInventario().vender(intercambio);
        }     
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
