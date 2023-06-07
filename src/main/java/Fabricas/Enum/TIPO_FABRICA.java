/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas.Enum;

/**
 *
 * @author Usuario
 */
public enum TIPO_FABRICA {
    ARMERIA(0), MINA(1),TEMPLO_BRIJA(2);
    
    private int value;
    
    TIPO_FABRICA(int pValue){
        value = pValue;
    }
    
    public int getValue() {
	return value;
    }
}
