package Communication;

import java.io.Serializable;

/**
 * Clase que representa un package y contiene los datos que se quieren enviar y recibir utilizando sockets. 
 * @author Federico Alfaro Chaverri
 */
public class Package implements Serializable{
    
    private int codigoPaquete; // -1 info creador - unido|| 0 info unido - creador || 1 mensaje || 2 ataque || 3 daño recibido || 4 infoTerminar
    private String nickName;
    private String ip;
    private String message;
    private String enemyCharacter;
    private String secretImage;
    private String characterImage;
    private String cityName;
    private String serverName;
    private String winner;
    private int totalStrikes;
    private int effectiveStrikes;
    private int damageRecieved;
    private boolean effective;
    
    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEnemyCharacter() {
        return enemyCharacter;
    }

    public void setEnemyCharacter(String enemyCharacter) {
        this.enemyCharacter = enemyCharacter;
    }

    public String getSecretImage() {
        return secretImage;
    }

    public void setSecretImage(String secretImage) {
        this.secretImage = secretImage;
    }

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getTotalStrikes() {
        return totalStrikes;
    }

    public void setTotalStrikes(int totalStrikes) {
        this.totalStrikes = totalStrikes;
    }

    public int getEffectiveStrikes() {
        return effectiveStrikes;
    }

    public void setEffectiveStrikes(int effectiveStrikes) {
        this.effectiveStrikes = effectiveStrikes;
    }

    public int getDamageRecieved() {
        return damageRecieved;
    }

    public void setDamageRecieved(int damageRecieved) {
        this.damageRecieved = damageRecieved;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }
    
   
}
