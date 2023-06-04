/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General;

/**
 *
 * @author Usuario
 */
public class PorMientras {
    private int acero;
    private int dinero;
    
    void minar(int cantidadAcero){
        acero += cantidadAcero;
    }
    
    boolean comprar(int precio){
        if(dinero - precio >= 0){
            dinero-=precio;
            return true;
        }
        return false;
    }
    
    
}
