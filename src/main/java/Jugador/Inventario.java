/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugador;

/**
 *
 * @author sotic
 */
public class Inventario {
    
    private int cannon;
    private int cannonMultiple;
    private int bombas;
    private int cannonBarbaRoja;
    private int barcosPirata;
    private int fuentesEnergia;
    
    public Inventario() {
        this.cannon = 0;
        this.cannonMultiple = 0;
        this.bombas = 0;
        this.cannonBarbaRoja = 0;
        this.barcosPirata = 0;
        this.fuentesEnergia = 0;
    }
    
    public int getCannon() {
        return cannon;
    }

    public void setCannon(int cannon) {
        this.cannon = cannon;
    }

    public int getCannonMultiple() {
        return cannonMultiple;
    }

    public void setCannonMultiple(int cannonMultiple) {
        this.cannonMultiple = cannonMultiple;
    }

    public int getBombas() {
        return bombas;
    }

    public void setBombas(int bombas) {
        this.bombas = bombas;
    }

    public int getCannonBarbaRoja() {
        return cannonBarbaRoja;
    }

    public void setCannonBarbaRoja(int cannonBarbaRoja) {
        this.cannonBarbaRoja = cannonBarbaRoja;
    }

    public int getBarcosPirata() {
        return barcosPirata;
    }

    public void setBarcosPirata(int barcosPirata) {
        this.barcosPirata = barcosPirata;
    }

    public int getFuentesEnergia() {
        return fuentesEnergia;
    }

    public void setFuentesEnergia(int fuentesEnergia) {
        this.fuentesEnergia = fuentesEnergia;
    }
}
