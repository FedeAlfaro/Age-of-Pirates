import General.IConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un listener y escucha llamadas de un socket. 
 * @author Federico Alfaro Chaverri
 */
public class Listener extends Thread implements IConstants{
    /*
    private Game panel;

    public Listener(Game panel) {
        this.panel = panel;
    }
    
    //
    // Se mantiene abierto para escuchar cualquier llamada de un socket
    //
    public void run() {        
        try {
            ServerSocket serverClient = new ServerSocket(PORT_NUMBER_SERVER_CLIENT);
            Socket socket;
            Package dataRecieved;
            while (true) {
                socket = serverClient.accept();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                dataRecieved = (Package) inputStream.readObject();
                if(dataRecieved.getCodigoPaquete()==-1){
                    panel.setEnemy2(dataRecieved.getNickName(), dataRecieved.getSecretImage()
                            , dataRecieved.getCharacterImage(), dataRecieved.getCityName(),dataRecieved.getServerName());
                }
                if(dataRecieved.getCodigoPaquete()==0){
                    panel.setEnemy(dataRecieved.getNickName(), dataRecieved.getSecretImage(), dataRecieved.getCharacterImage());
                }
                if(dataRecieved.getCodigoPaquete()==1){
                    panel.setArea(dataRecieved.getNickName()+": "+dataRecieved.getMessage());
                }
                if(dataRecieved.getCodigoPaquete()==2){
                    panel.recieveAttack(dataRecieved.getDamageRecieved(), dataRecieved.isEffective());
                }
                if(dataRecieved.getCodigoPaquete()==3){
                    panel.actualizarVidaContraria(dataRecieved.getDamageRecieved());
                }
                
                
                inputStream.close();
                socket.close();
                panel.reducirVida1();
                panel.reducirVida2();
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    */
}
