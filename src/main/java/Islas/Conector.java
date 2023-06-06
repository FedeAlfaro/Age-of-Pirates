/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Islas;

/**
 *
 * @author Usuario
 */
public class Conector {
    private int x;
    private int y;
    private boolean visible;
    private boolean usado;
    private Vertice fin;
    
    Conector(int _x,int _y){
        x = _x;
        y= _y;
        visible = false;
        usado = false;
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

    public Vertice getFin() {
        return fin;
    }

    public void setFin(Vertice fin) {
        this.fin = fin;
    }
    
    public void mostrar(){
        visible = true;
    }        
    
    public void noMostrar(){
        visible = false;
    }        
    
    public boolean conectar(Vertice v2){
        if(!usado && esAdyacenciente(v2.fabrica.getX(),v2.fabrica.getY(),v2.fabrica.getOrientacion().getValue()) ){
            fin = v2;
            usado = true;
        }
        return false;
    }
    
    public boolean esAdyacenciente(int _x,int _y, int orientacion){
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
}
