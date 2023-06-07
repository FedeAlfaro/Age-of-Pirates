/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

import Fabricas.Fabrica;
import General.IConstants;
import java.util.ArrayList;

/**
 *
 * @author dmora
 * @author Federico Alfaro
 */
public class Grafo implements IConstants{

    ArrayList<Vertice> vertices;
    FuenteEnergia fuente;

    public Grafo(FuenteEnergia _fuente)
    {
        vertices = new ArrayList<Vertice>();
        fuente = _fuente;
    }

    // agrega a la lista
    public void agregarVertice(int valor)
    {
        vertices.add(new Vertice(valor));
    }

    /*
    // agrega las aristas
    public void agregarArista(Vertice origen, Vertice destino)
    {
        if (origen != null && destino != null)
            origen.agregarArista(destino);
    }
    */

    // agrega las aristas con peso
    public void agregarArista(Vertice origen, Vertice destino, int peso)
    {
        if (origen != null && destino != null)
            origen.agregarArista(destino, peso);
    }
    
    // agrega las aristas con peso
    public boolean agregarArista(Vertice origen, Vertice destino)
    {
        if (origen != null && destino != null){
            return origen.agregarArista(destino);
        }
        return false;
    }

    // busca un vertice en la lista
    public Vertice buscarVertice (int valor){

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).dato == valor)
                return vertices.get(i);
        }

        return null;
    }

    // imprime la lista con sus listas de adyacencia
    public void imprimir ()
    {
        for (int i = 0; i < vertices.size(); i++)
        {
            System.out.print("Vertice "+vertices.get(i).dato+":  ");
            for (int j = 0; j < vertices.get(i).aristas.size(); j++) {
                System.out.print(vertices.get(i).aristas.get(j).dato +"  ");
            }
            System.out.println("");
        }
    }

    // elimina un vertice, de la lista y de las listas de adyacencia
    // imprime la lista con sus listas de adyacencia
    public void eliminar (Vertice v)
    {
        for (int i = 0; i < vertices.size(); i++)
        {
            for (int j = 0; j < vertices.get(i).aristas.size(); j++) {
                if (vertices.get(i).aristas.get(j).dato == v.dato)
                    vertices.get(i).aristas.remove(j);
            }
        }

        vertices.remove(v);
    }


    // Recorrido PROFUNDIDAD
    public void profundidad()
    {
        // recorre todos los nodos
        for (int i = 0; i < vertices.size(); i++)
        {
            if(vertices.get(i).visitado == false)
            {
                visitarAdyacentes(vertices.get(i));
            }
        }

        limpiarVisitados();// quita todos los nodos visitados

    }

    // vissita los nodos en la lista de adyacencia
    public void visitarAdyacentes(Vertice nodo)
    {
        visitarVertice(nodo);
        System.out.print(nodo.dato+"  ");

        // para cada arista
        for (int i = 0; i < nodo.aristas.size(); i++)
        {
            // marca cada uno de los adyacentes
            if (visitadoVertice(nodo.aristas.get(i)) == false)
            {
                visitarAdyacentes(buscarVertice(nodo.aristas.get(i).dato));
            }
        }
    }

    public void visitarVertice(Vertice nodo)
    {
        for (int i = 0; i < vertices.size(); i++) {
            if (nodo.dato == vertices.get(i).dato)
                vertices.get(i).visitado = true;
        }
    }

    public boolean visitadoVertice(Vertice nodo)
    {
        for (int i = 0; i < vertices.size(); i++) {
            if (nodo.dato == vertices.get(i).dato)
                return vertices.get(i).visitado;
        }
        return false;// si no está
    }

    public void limpiarVisitados()
    {
        for (int i = 0; i < vertices.size(); i++) {
            Vertice vertice = vertices.get(i);
            vertice.visitado = false;
        }
    }


    //----------------------------------------
    public void anchura(Vertice v)
    {
        System.out.print(v.dato+"  ");
        visitarVertice(v);// marca el primer nodo
        ArrayList<Vertice> cola = new ArrayList<Vertice>();
        // mete a la cola los adyacentes del nodo inicial
        for (int i = 0; i < v.aristas.size(); i++) {
            cola.add(buscarVertice(v.aristas.get(i).dato));// es para buscar el nodo en vertices
            visitarVertice(v.aristas.get(i));
            //System.out.println("COLA "+v.aristas.get(i).dato);
        }
        // mientras no se vacíe la cola
        while(!cola.isEmpty())
        {
            // trabaja con el primero de la cola
            Vertice actual = cola.remove(0);
            visitarVertice(actual);
            System.out.print(actual.dato+"  ");
            // cada arista del vertice en la cola
            for (int i = 0; i < actual.aristas.size(); i++) {

                // si no se ha visitado se mete en la cola el adyacente
                if(visitadoVertice(actual.aristas.get(i))==false)
                {
                    // si no está ya en la cola, se mete
                    visitarVertice(buscarVertice(actual.aristas.get(i).dato));
//                    System.out.println("METE"+ actual.aristas.get(i).dato+ "  "+actual.aristas.get(i).visitado);
                    cola.add(actual.aristas.get(i));
                }       
            }
        }
        limpiarVisitados();
    }


    public void caminos(Vertice v, Vertice v2)
    {
        boolean resultado;
        //System.out.print(v.dato+"  ");
        visitarVertice(v);// marca el primer nodo
        ArrayList<Vertice> cola = new ArrayList<Vertice>();
        // mete a la cola los adyacentes del nodo inicial
        for (int i = 0; i < v.aristas.size(); i++) {
            
            if(buscarVertice(v.aristas.get(i).dato).visitado == false){
                System.out.println("Hola "+ buscarVertice(v.aristas.get(i).dato).dato );
                caminos(buscarVertice(v.aristas.get(i).dato),v2);
                if(v.dato != 0)
                    v.visitado = false;
                System.out.println("\t");
            }   
            
            //caminos(buscarVertice(v.aristas.get(i).dato), v2);
            /*
            if(buscarVertice(v.aristas.get(i).dato).visitado == true){
                return false;
            }            
            resultado = v2==buscarVertice(v.aristas.get(i).dato);
            visitarVertice(v.aristas.get(i));
            if(resultado || caminos(buscarVertice(v.aristas.get(i).dato), v2) ){
                System.out.println(v.dato + " ");;
            }
            limpiarVisitados();
            return resultado;
            */
        }
        //return false;
    }
    
    
    public void imprimirCaminos(int origen, int destino) {
        Vertice nodoOrigen = buscarVertice(origen);
        Vertice nodoDestino = buscarVertice(destino);

        ArrayList<Vertice> caminoActual = new ArrayList<Vertice>();
        caminoActual.add(nodoOrigen);

        imprimirCaminosAux(nodoOrigen, nodoDestino, caminoActual);
    }

    private void imprimirCaminosAux(Vertice actual, Vertice destino, ArrayList<Vertice> caminoActual) {
        actual.visitado = true;

        if (actual == destino) {
            for (Vertice vertice : caminoActual) {
                System.out.print(vertice.dato + " ");
            }
            System.out.println();
        } else {
            for (Vertice adyacente : actual.aristas) {
                if (!adyacente.visitado) {
                    caminoActual.add(adyacente);
                    imprimirCaminosAux(adyacente, destino, caminoActual);
                    caminoActual.remove(caminoActual.size() - 1);
                }
            }
        }

        actual.visitado = false;
    }
    
    public boolean finalizar(){
        if(fuente==null){ 
            if(vertices.isEmpty()){
                return true;
            }
            vertices.stream().forEach(p->p.mostrarTodo() );
        }
        return false;
    }
    
    public int[][] retornarMatriz(){ //-1 conector 
        int matriz[][] = new int[TAMANO_MATRIZ][TAMANO_MATRIZ];
        for(int i=0;i<vertices.size();i++){
            for(int j=0;j< vertices.get(i).conectores.size();j++){
                Conector conector = vertices.get(i).conectores.get(j);
                matriz[conector.getX()][conector.getY()] = CODIGO_CONECTOR;
            }
            if(vertices.get(i).fabrica!=null){
                if(vertices.get(i).fabrica.getNombre() == "Armeria"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                }else if(vertices.get(i).fabrica.getNombre() == "Mina"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                }else if(vertices.get(i).fabrica.getNombre() == "Templo de brujas"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                }
            }
            if(fuente!=null){
                matriz[fuente.getX()][fuente.getY()] = CODIGO_FUENTE;
            }
        }
        
        return matriz;
    }
    
    public int[][] retornarMatrizVisible(){ //-1 conector 
        int matriz[][] = new int[TAMANO_MATRIZ][TAMANO_MATRIZ];
        for(int i=0;i<vertices.size();i++){
            for(int j=0;j< vertices.get(i).conectores.size();j++){
                Conector conector = vertices.get(i).conectores.get(j);
                if(conector.isVisible()){
                    matriz[conector.getX()][conector.getY()] = CODIGO_CONECTOR;
                }
            }
            if(vertices.get(i).fabrica!=null){
                if(vertices.get(i).fabrica.isVisible()){
                    if(vertices.get(i).fabrica.getNombre() == "Armeria"){
                        matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                    }else if(vertices.get(i).fabrica.getNombre() == "Mina"){
                        matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                    }else if(vertices.get(i).fabrica.getNombre() == "Templo de brujas"){
                        matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                    }
                }
            }
        }
        
        return matriz;
    }
    
    public void imprimirMatriz(){
        int matriz[][] = retornarMatriz();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                switch (matriz[i][j]) {
                    case CODIGO_CONECTOR:
                        System.out.println("En la posición x: "+i+" y: "+j+ " hay un conector.");
                        break;
                    case CODIGO_ARMERIA:
                        System.out.println("En la posición x: "+i+" y: "+j+ " hay una armeria.");
                        break;
                    case CODIGO_MINA:
                        System.out.println("En la posición x: "+i+" y: "+j+ " hay una mina.");
                        break;
                    case CODIGO_TEMPLO_BRUJA:
                        System.out.println("En la posición x: "+i+" y: "+j+ " hay un templo de brujas.");
                        break;
                    case CODIGO_FUENTE:
                        System.out.println("En la posición x: "+i+" y: "+j+ " hay una fuente.");
                        break;    
                    default:
                        //System.out.println("En la posición x: "+i+" y: "+j+ " hay un .");
                        break;
                }
            }
        }
    }
}