/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

import Fabricas.*;
import static Fabricas.Enum.ORIENTACION.*;
import General.IConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmora
 * @author Federico Alfaro
 */
public class Grafo implements IConstants{

    ArrayList<Vertice> vertices;
    public int matriz[][];
    ArrayList<Conector> conectores;

    public Grafo()
    {
        vertices = new ArrayList<Vertice>();
        matriz = new int[TAMANO_MATRIZ][TAMANO_MATRIZ];
        conectores = new ArrayList<Conector>();

        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void agregarFuente(int x,int y){
        Vertice vertice = new Vertice(0);
        vertices.add(vertice);
        Fabrica fuente = new FuenteEnergia(x,y);
        vertice.fabrica = fuente;
        matriz[x][y] = CODIGO_FUENTE;
        matriz[x+1][y] = CODIGO_FUENTE;
        matriz[x][y+1] = CODIGO_FUENTE;
        matriz[x+1][y+1] = CODIGO_FUENTE;
    }
    
    public boolean agregarFabrica(int IDvertice,int x,int y, int orientacion, int tipoFabrica){ //Mina(0), Armeria(1), TemploBrujas(2)
        if(verificarEspacioFabrica(x,y, orientacion)){
            Vertice vertice = new Vertice(IDvertice);
            vertices.add(vertice);

            switch (tipoFabrica) {
                case 0:
                    Fabrica mina = new Mina(x,y,orientacion);
                    Mina m = (Mina)mina;
                    m.ejecutar();
                    vertice.fabrica = mina;
                    matriz[x][y] = CODIGO_MINA;
                    if(orientacion == 0){
                        matriz[x][y+1] = CODIGO_MINA;
                    }else if(orientacion == 1){
                        matriz[x+1][y] = CODIGO_MINA;
                    }
                    break;
                case 1:
                    Fabrica armeria = new Armeria(x,y,orientacion);
                    vertice.fabrica = armeria;
                    matriz[x][y] = CODIGO_ARMERIA;
                    if(orientacion == 0){
                        matriz[x][y+1] = CODIGO_ARMERIA;
                    }else if(orientacion == 1){
                        matriz[x+1][y] = CODIGO_ARMERIA;
                    }
                    break;
                case 2:
                    Fabrica templo = new TemploBruja(x,y,orientacion);
                    vertice.fabrica = templo;
                    matriz[x][y] = CODIGO_TEMPLO_BRUJA;
                    if(orientacion == 0){
                        matriz[x][y+1] = CODIGO_TEMPLO_BRUJA;
                    }else if(orientacion == 1){
                        matriz[x+1][y] = CODIGO_TEMPLO_BRUJA;
                    }
                    break;
                case 3:
                    Fabrica mercado = new Mercado(x,y,orientacion);
                    vertice.fabrica = mercado;
                    matriz[x][y] = CODIGO_TEMPLO_BRUJA;
                    if(orientacion == 0){
                        matriz[x][y+1] = CODIGO_TEMPLO_BRUJA;
                    }else if(orientacion == 1){
                        matriz[x+1][y] = CODIGO_TEMPLO_BRUJA;
                    }
                    break;    
                default:
                    System.out.println("Opción de fábrica no válida");
                    break;
            }
            return true;
        }
        return false;
    }
    
    public boolean agregarConector(int x,int y){
        if(verificarEspacioConector(x,y)){
            Conector conector = new Conector(x,y);
            matriz[x][y] = CODIGO_CONECTOR;
            conectores.add(conector);
            conectarConectores();
            System.out.println("Se llama a agregar conectores");
            return true;
        }
        return false;
    }
    
    public void conectarConectores(){
        for(Conector conector: conectores){
            for(Vertice v: vertices){
                conector.conectar(v);
            }
        }
        System.out.println("Se llama a agregar conectores");
        visibilizar();
    }
    
    public void visibilizar(){
        conectores.stream().forEach(p->p.setVisible(true));
        for(Vertice v: vertices){
            if(v.fabrica != null){
                v.fabrica.mostrar();
            }
        }
        if(vertices.get(0).fabrica!=null){
            vertices.get(0).fabrica.noMostrar();
        }
        conectores.stream()
            .filter(p->p.vertices.stream().anyMatch(f->f.dato==0))
            .forEach(m->m.noMostrar());    //Todos los vertices conectados a la fuente de poder los hace invisibles
        System.out.println("Hay algún conector al rededor de la fuente: "
                + conectores.get(0).conectar(vertices.get(0))+" \n Fuente x: "
                +vertices.get(0).getFabrica().getX()+" y: "+vertices.get(0).getFabrica().getY()+"\nconector x: "+conectores.get(0).getX()+" y:"+ conectores.get(0).getY());
        conectores.stream()
                .filter(p->!p.isVisible())
                .forEach(q->q.vertices.stream()
                        .forEach(f->f.fabrica.noMostrar())); //Todos los vectores dentro de los conectores invisibles se hacen invisibles
        List<Vertice> verticesInv = conectores.stream()
            .filter(p->!p.isVisible())
            .flatMap(conector -> conector.getVertices().stream())      
            .collect(Collectors.toList());                     //Hace una lista con todos los vectores invisibles dentro de los conectores
        for(Vertice vertice:vertices){
            if(verticesInv.stream().anyMatch(v -> v.getDato() == vertice.dato) ){
                vertice.fabrica.noMostrar();
            }
        } //Hace invisible todos los vectores del grafo con conector invisible 
        conectores.stream().filter(p->p.vertices.stream()
                .anyMatch(v->!v.fabrica.isVisible()))
                .forEach(m->m.noMostrar()); //Hace invisible los conectores con algún vertice invisible
        
        
        for(Conector conector:conectores){
           
            verticesInv = conectores.stream()
                .filter(p->!p.isVisible())
                .flatMap(c -> c.getVertices().stream())      
                .collect(Collectors.toList());                     //Hace una lista con todos los vectores invisibles dentro de los conectores

            for(Vertice vertice:vertices){
                if(verticesInv.stream().anyMatch(v -> v.getDato() == vertice.dato) ){
                    vertice.fabrica.noMostrar();
                }
            } //Hace invisible todos los vectores del grafo con conector invisible 
            conectores.stream().filter(p->p.vertices.stream()
                    .anyMatch(v->!v.fabrica.isVisible()))
                    .forEach(m->m.noMostrar());
            
        }
    }
    
    // agrega a la lista
    
    private void agregarVertice(int valor)
    {
        if(valor == 0){
            vertices.add(valor, new Vertice(valor));
        }
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
        conectores.stream().forEach(p->p.eliminarVertice(v));
        if(v.getFabrica().getClass() == Mina.class){
            Mina mina = (Mina)v.getFabrica();
            mina.stop();
        }else if(v.getFabrica().getClass() == TemploBruja.class){
            TemploBruja temploBruja = (TemploBruja)v.getFabrica();
            
        }
        vertices.remove(v);
    }
    
    public void eliminarFuente(){
        vertices.get(0).fabrica = null;
    }

    public void eliminarConector(){
        
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
        if(vertices.size()==1 && vertices.get(0).fabrica==null){
            return true;
            
        }else if( vertices.get(0).fabrica == null){
            vertices.stream().forEach(p->p.fabrica.mostrar() );
        }
        return false;
    }
    
    public int[][] retornarMatriz(){ //-1 conector 
        int matriz[][] = new int[TAMANO_MATRIZ][TAMANO_MATRIZ];
        for(int i=0;i<conectores.size();i++){
            
            Conector conector = conectores.get(i);
            matriz[conector.getX()][conector.getY()] = CODIGO_CONECTOR;
            
            if(vertices.get(i).fabrica!=null){
                if(vertices.get(i).fabrica.getNombre() == "Armeria"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                }else if(vertices.get(i).fabrica.getNombre() == "Mina"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                }else if(vertices.get(i).fabrica.getNombre() == "Templo de brujas"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                }else if(vertices.get(i).fabrica.getNombre() == "Fuente de energia"){
                    matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_FUENTE;
                }
            }
            
        }
        
        return matriz;
    }
    
    public int[][] retornarMatrizVisible(){ 
        int matrizAux[][] = new int[TAMANO_MATRIZ][TAMANO_MATRIZ];
        for(int i=0;i<vertices.size();i++){
            if(vertices.get(i).fabrica!=null){
                if(vertices.get(i).fabrica.isVisible()){
                    if(vertices.get(i).fabrica.getOrientacion()==VERTICAL){
                        if(vertices.get(i).fabrica.getNombre() == "Armeria"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()+1] = CODIGO_ARMERIA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Mina"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()+1] = CODIGO_MINA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Templo de brujas"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()+1] = CODIGO_TEMPLO_BRUJA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Mercado"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MERCADO;
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()+1] = CODIGO_MERCADO;
                        }
                    }else{
                        if(vertices.get(i).fabrica.getNombre() == "Armeria"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                            matrizAux[vertices.get(i).fabrica.getX()+1][vertices.get(i).fabrica.getY()] = CODIGO_ARMERIA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Mina"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                            matrizAux[vertices.get(i).fabrica.getX()+1][vertices.get(i).fabrica.getY()] = CODIGO_MINA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Templo de brujas"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                            matrizAux[vertices.get(i).fabrica.getX()+1][vertices.get(i).fabrica.getY()] = CODIGO_TEMPLO_BRUJA;
                        }else if(vertices.get(i).fabrica.getNombre() == "Mercado"){
                            matrizAux[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()] = CODIGO_MERCADO;
                            matrizAux[vertices.get(i).fabrica.getX()+1][vertices.get(i).fabrica.getY()] = CODIGO_MERCADO;
                        }
                    }
                }
            }
        }
        for(Conector c:conectores){
            if(c.isVisible()){
                matrizAux[c.getX()][c.getY()] = CODIGO_CONECTOR;
            }
        }
        for(int i=0;i<TAMANO_MATRIZ;i++){
            for(int j=0;j<TAMANO_MATRIZ;j++){
                if(matriz[i][j]==CODIGO_DISPARO){
                    matrizAux[i][j]=CODIGO_DISPARO;
                }
            }
        }
        return matrizAux;
    }
    
    public void imprimirMatriz(){
        int matrizAux[][] = retornarMatriz();
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                switch (matrizAux[i][j]) {
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
    
    public void imprimirMatriz2(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public String imprimirMatriz3(){
        String resultado = "";
        int[][] m = retornarMatrizVisible();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                resultado+= m[j][i]+" ";
            }
            resultado+= "\n";
        }
        return resultado;
    }
    
    
    public void verificarFabricaDisparo(){
        for(int i=0;i<vertices.size();i++){
            if(vertices.get(i).fabrica.getOrientacion()==VERTICAL){
                if(matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()]==CODIGO_DISPARO 
                        && matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()+1]==CODIGO_DISPARO){
                    
                    eliminar(vertices.get(i)); //se elimina del grafo pero no de las aristas
                    /*
                    for(int j=0;j< vertices.get(i).aristas.size();j++){
                        if (vertices.get(i).dato == vertices.get(i).aristas.get(j).dato){
                            
                            vertices.get(i).aristas.get(j).fabrica = null; //se elimina de la arista
                        }    
                    }
                    */
                    
                }
            }else if(vertices.get(i).fabrica.getOrientacion()==HORIZONTAL){
                if(matriz[vertices.get(i).fabrica.getX()][vertices.get(i).fabrica.getY()]==CODIGO_DISPARO 
                        && matriz[vertices.get(i).fabrica.getX()+1][vertices.get(i).fabrica.getY()]==CODIGO_DISPARO){
                    
                    eliminar(vertices.get(i));
                    /*
                    for(int j=0;j< vertices.get(i).aristas.size();j++){
                        if (vertices.get(i).dato == vertices.get(i).aristas.get(j).dato){ 
                            
                            vertices.get(i).aristas.get(j).fabrica = null;
                        }    
                    }
                    */
                }
            }
        }
    }
    
    public void verificarConectorDisparo(){
        for(Conector conector:conectores){
            if(matriz[conector.getX()][conector.getY()] == CODIGO_DISPARO){
                conectores.remove(conector);
            }
        }
    }
    
    public void verificarFuenteDisparo(){
        if(vertices.size()>0){
            if(matriz[vertices.get(0).fabrica.getX()][vertices.get(0).fabrica.getY()]==CODIGO_DISPARO 
                    && matriz[vertices.get(0).fabrica.getX()][vertices.get(0).fabrica.getY()+1]==CODIGO_DISPARO
                    && matriz[vertices.get(0).fabrica.getX()+1][vertices.get(0).fabrica.getY()]==CODIGO_DISPARO
                    && matriz[vertices.get(0).fabrica.getX()+1][vertices.get(0).fabrica.getY()+1]==CODIGO_DISPARO){

                eliminarFuente();

            }
        }
    }
    
    //int IDvertice,int x,int y, int orientacion, int tipoFabrica
    public boolean verificarEspacioFabrica(int x,int y, int orientacion){
        if(orientacion == 0){
            if( (matriz[x][y]==CODIGO_DISPARO || matriz[x][y]==0 ) 
                    && (matriz[x][y+1]==CODIGO_DISPARO || matriz[x][y+1]==0) ){
                return true;
            }
        }else if(orientacion == 1){
            if( (matriz[x][y]==CODIGO_DISPARO || matriz[x][y]==0 )  
                    && (matriz[x+1][y]==CODIGO_DISPARO || matriz[x+1][y]==0) ){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarEspacioConector(int x,int y){
        if( (matriz[x][y]==CODIGO_DISPARO || matriz[x][y]==0 )  ){
            return true;
        }
        return false;
    }
    
    public boolean verificarEspacioRemolino(int x,int y){
        if( (matriz[x][y]==CODIGO_DISPARO || matriz[x][y]==0 ) ){
            return true;
        }
        return false;
    }
    
    public int getDatoMatriz(int x, int y){
        return matriz[x][y];
    }
    
    public void setDatoMatriz(int x, int y, int dato){
        matriz[x][y] = dato;
    }
    
    public boolean hayFuente(){
        for (int i = 0; i < vertices.size(); i++){
            if (vertices.get(i).fabrica instanceof FuenteEnergia){
                return true;
            }
        }
        return false;
    }
}