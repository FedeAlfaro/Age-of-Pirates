/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package General;

import Fabricas.FuenteEnergia;
import Interfaz.PantallaInicio;
import Islas.*;

/**
 *
 * @author Usuario
 */
public class AgeOfPirates {

    public static void main(String[] args) {
        Grafo g = new Grafo();
        
        g.agregarFuente(0,15,15);
        g.agregarFabrica(1,14,13,0,0);
        g.agregarFabrica(2,13,12,0,0);
        g.agregarFabrica(3,12,11,0,1);
        g.agregarFabrica(3,11,10,0,1);
        g.agregarFabrica(4,10,9,0,1);
        g.agregarFabrica(5,9,8,0,2);
        g.agregarFabrica(6,8,7,0,2);
        g.agregarFabrica(7,7,6,0,0);
        
        g.imprimirMatriz2();
        
        PantallaInicio pantallaI = new PantallaInicio();
        
        pantallaI.setVisible(true);
        /*
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
        */
    }
}
