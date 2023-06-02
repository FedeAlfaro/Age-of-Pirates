/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas.Enum;

/**
 *
 * @author Usuario
 */
public enum ORIENTACION {
    VERTICAL(0), HORIZONTAL(1);
    
    private int value;
    
    ORIENTACION(int pValue){
        value = pValue;
    }
    
    public int getValue() {
	return value;
    }
}
