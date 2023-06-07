/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package General;

import Islas.*;

/**
 *
 * @author Usuario
 */
public class AgeOfPirates {

    public static void main(String[] args) {
        FuenteEnergia fuente = new FuenteEnergia(0,0);
        Grafo g = new Grafo(fuente);
        
        g.agregarVertice(0);
        g.agregarVertice(1);
        g.agregarVertice(2);
        g.agregarVertice(3);
        g.agregarVertice(4);
        g.agregarVertice(5);
        g.agregarVertice(6);
        g.agregarVertice(7);
        
        g.agregarArista(g.buscarVertice(0), g.buscarVertice(2));
        g.agregarArista(g.buscarVertice(2), g.buscarVertice(0));
        g.agregarArista(g.buscarVertice(0), g.buscarVertice(7));
        g.agregarArista(g.buscarVertice(7), g.buscarVertice(0));
        g.agregarArista(g.buscarVertice(0), g.buscarVertice(5));
        g.agregarArista(g.buscarVertice(5), g.buscarVertice(0));
        g.agregarArista(g.buscarVertice(1), g.buscarVertice(7));
        g.agregarArista(g.buscarVertice(7), g.buscarVertice(1));
        g.agregarArista(g.buscarVertice(2), g.buscarVertice(6));
        g.agregarArista(g.buscarVertice(6), g.buscarVertice(2));
        g.agregarArista(g.buscarVertice(3), g.buscarVertice(4));
        g.agregarArista(g.buscarVertice(4), g.buscarVertice(3));
        g.agregarArista(g.buscarVertice(3), g.buscarVertice(5));
        g.agregarArista(g.buscarVertice(5), g.buscarVertice(3));
        g.agregarArista(g.buscarVertice(4), g.buscarVertice(7));
        g.agregarArista(g.buscarVertice(7), g.buscarVertice(4));
        g.agregarArista(g.buscarVertice(4), g.buscarVertice(6));
        g.agregarArista(g.buscarVertice(6), g.buscarVertice(4));
        g.agregarArista(g.buscarVertice(4), g.buscarVertice(5));
        g.agregarArista(g.buscarVertice(5), g.buscarVertice(4));
    }
}
