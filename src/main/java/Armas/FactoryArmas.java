/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Armas;

/**
 *
 * @author sotic
 */
public class FactoryArmas {
    
    public static Arma generaArma(int tipo){
        switch(tipo){
            case 0 -> {
                return new Cannon();
            }
            case 1 -> {
                return new CannonMultiple();
            }
            case 2 -> {
                return new Bomba();
            }
            case 3 -> {
                return new CannonBarbaRoja();
            }
            default -> {
                return null;
            }
        }    
    }
}
