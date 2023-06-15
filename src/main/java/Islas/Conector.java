/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Conector {
    private int x;
    private int y;
    private boolean visible;
    private boolean usado;
    public ArrayList<Vertice> vertices;
    
    Conector(int _x,int _y){
        x = _x;
        y= _y;
        visible = false;
        usado = false;
        vertices = new ArrayList<Vertice>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void mostrar(){
        visible = true;
    }        
    
    public void noMostrar(){
        visible = false;
    }        
    
    public boolean conectar(Vertice v){
        if(v.fabrica!=null){
            if(v.dato==0){
                if(esAdyacienteFuente(v.fabrica.getX(),v.fabrica.getX()));
                    vertices.add(v);
                    return true;
            }else if(esAdyaciente(v.fabrica.getX(),v.fabrica.getY(), v.fabrica.getOrientacion().getValue())){
                vertices.add(v);
                return true;
            }
        }
        return false;    
    }
    
    public boolean esAdyaciente(int _x,int _y, int orientacion){
        if(orientacion == 0){  //vertical
            if(x-1==_x && (y+1 == _y || y+2 == _y || y==_y || y-1==_y)  ){
                return true;
            }else if(x==_x && (y+2 == _y || y==_y-1)  ){
                return true;
            }else if(x+1==_x && (y+1 == _y || y+2 == _y || y==_y || y-1==_y)  ){
                return true;
            }
        }else if(orientacion == 1){ //horizontal
            if( (x-2==_x || x-1==_x || x==_x || x+1 == _x) && y+1 == _y ){
                return true;
            }else if( (x-2==_x || x+1 == _x) && y-1==_y ){
                return true;
            }else if((x-2==_x || x-1==_x || x==_x || x+1 == _x) && y==_y  ){
                return true;
            }
        }    
        return false;
    }
    
    public boolean esAdyacienteFuente(int _x,int _y){
        
        if(x-2==_x && (y+1 == _y || y+2 == _y || y==_y || y-1==_y)  ){
                return true;
        }else if(x-1==_x && (y-1==_y || y+2 == _y)  ){
            return true;
        }else if(x==_x && (y+2 == _y || y==_y-1)  ){
            return true;
        }else if(x+1==_x && (y+1 == _y || y+2 == _y || y==_y || y-1==_y)  ){
            return true;
        }
         
        return false;
    }
    
    public void eliminarVertice(Vertice v){
        for (int j = 0; j < vertices.size(); j++) {
            if (vertices.get(j).dato == v.dato)
                vertices.remove(j);
        }
    }
}
