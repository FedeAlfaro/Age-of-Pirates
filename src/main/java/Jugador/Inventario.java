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
    private int tamanno = 6;
    
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
    
   // Métodos
    public boolean validaTransaccion(Inventario peticion){
        if (this.getCannon() < peticion.getCannon()){// No hay inventario
            return false;
        }
        if (this.getCannonMultiple() < peticion.getCannonMultiple()){
            return false;
        }
        if (this.getBombas() < peticion.getBombas()){
            return false;
        }
        if (this.getCannonBarbaRoja() < peticion.getCannonBarbaRoja()){
            return false;
        }
        if (this.getBarcosPirata() < peticion.getBarcosPirata()){
            return false;
        }
        if (this.getFuentesEnergia() < peticion.getFuentesEnergia()){
            return false;
        }
        return true;
    }
    
    public void comprar(Inventario peticion){ // Luego de validada
        if (peticion.getCannon() != 0){
            this.setCannon(this.getCannon() + peticion.getCannon());
        }
        if (peticion.getCannonMultiple() != 0){
            this.setCannonMultiple(this.getCannonMultiple() + peticion.getCannonMultiple());
        }
        if (peticion.getBombas() != 0){
            this.setBombas(this.getBombas() + peticion.getBombas());
        }
        if (peticion.getCannonBarbaRoja()!= 0){
            this.setCannonBarbaRoja(this.getCannonBarbaRoja() + peticion.getCannonBarbaRoja());
        }
        if (peticion.getBarcosPirata() != 0){
            this.setBarcosPirata(this.getBarcosPirata() + peticion.getBarcosPirata());
        }
        if (peticion.getFuentesEnergia() != 0){
            this.setFuentesEnergia(this.getFuentesEnergia() + peticion.getFuentesEnergia());
        }
    }
    
    public void vender(Inventario peticion){ // Luego de validada
        if (peticion.getCannon() != 0){
            this.setCannon(this.getCannon() - peticion.getCannon());
        }
        if (peticion.getCannonMultiple() != 0){
            this.setCannonMultiple(this.getCannonMultiple() - peticion.getCannonMultiple());
        }
        if (peticion.getBombas() != 0){
            this.setBombas(this.getBombas() - peticion.getBombas());
        }
        if (peticion.getCannonBarbaRoja()!= 0){
            this.setCannonBarbaRoja(this.getCannonBarbaRoja() - peticion.getCannonBarbaRoja());
        }
        if (peticion.getBarcosPirata() != 0){
            this.setBarcosPirata(this.getBarcosPirata() - peticion.getBarcosPirata());
        }
        if (peticion.getFuentesEnergia() != 0){
            this.setFuentesEnergia(this.getFuentesEnergia() - peticion.getFuentesEnergia());
        }
    }
}
