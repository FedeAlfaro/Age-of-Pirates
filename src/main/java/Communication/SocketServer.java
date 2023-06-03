package Communication;

import General.IConstants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un socket servidor
 * @author Rodrigo Núñez Núñez
 * @author Federico Alfaro Chaverri
 * @see <a href="https://github.com/vsurak/cursostec/blob/62cd58bb16b931d0c872f0a9bb6339518db6bc24/poo/yate/src/robotwar/communication/SocketServer.java">Java Dcoumentation</a> 
 * @see <a href="chat.openai.com/chat">Java Dcoumentation</a> se pregunta al chat: "cómo obtener la hora local en java"
 */
public class SocketServer implements Runnable, IConstants{
    
    //private InterfazServidor panel;
    private boolean listening;
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private ServerSocket socketListener;
    private ArrayList<SocketClient> clientes;

     public SocketServer() {
        listening = true;
    }

    /**
     * Abre el hilo
     */
    public void startListening() {
        Thread t = new Thread(this);
        t.start();
    }
    
    /**
     * Cierra el hilo
     */
    public void stopListening() {
        listening = false;

    }
    
    /**
     * Se mantiene abierto para recibir y enviar paquetes de datos
     */
    public void run() {
        try{
            socketListener = new ServerSocket(PORT_NUMBER_SERVER);
            String nickName,ip, message;
            Package recievedData;
            socketListener.setReuseAddress(true);
            while (listening) {
                    
                    Socket connection = socketListener.accept();
                    ObjectInputStream data = new ObjectInputStream(connection.getInputStream());
                    recievedData = (Package) data.readObject();
                    //if(recievedData.getCodigoPaquete()==1){
                    //    processMessage(recievedData.getNickName()+": "+recievedData.getMessage());
                    //}
                    SocketClient socket = new SocketClient(recievedData.getIp(),PORT_NUMBER_SERVER_CLIENT);
                    socket.sendObj(recievedData);
                    socket.close();
                    connection.close();
                
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"Error binding to port",ex);
        }
    }

    public int getPORT_NUMBER() {
        return PORT_NUMBER_SERVER;
    }
    
    public ServerSocket getServerSocket() {
        return socketListener;
    }
    
    public String transformarMensaje(SocketClient client) throws IOException {
        return client.getInput().readLine();
    }
    /*
    public void processMessage(String pMsg) {
        panel.setArea(pMsg);
    }*/
    
}