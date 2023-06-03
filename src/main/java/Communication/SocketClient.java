//package Communication;
//
////import GraphicInterface.Game.Game;
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * Clase que representa un socket cliente 
// * @author Rodrigo Núñez Núñez
// * @author Federico Alfaro Chaverri
// * @see <a href="https://github.com/vsurak/cursostec/blob/62cd58bb16b931d0c872f0a9bb6339518db6bc24/poo/yate/src/robotwar/communication/SocketServer.java">Java Dcoumentation</a> 
// * @see <a href="https://www.youtube.com/watch?v=L0Y6hawPB-E&t=829s">Java Dcoumentation</a> primer video de una serie de videos que explica sobre la generación de un chat por medio de sockets.
// * @see <a href="https://www.youtube.com/watch?v=fNj6qeQo0X0">Java Dcoumentation</a> video que explica como obtener la dirección IP.
// */
//
//public class SocketClient extends Thread {
//    private Socket conexion;
//    private PrintWriter output;
//    private DataOutputStream binOutput;
//    private ObjectOutputStream obOutput;
//    private BufferedReader input;
//    private boolean listen = false;
//    private static final Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
//    //private Game panel;
//    
//    /**
//     * Constructor que contiene panel
//     * @param pIpAddress String corresponde a la direccion ip del servidor
//     * @param pPort int corresponde al puerto del servidor
//     * @param pPanel Game corresponde a un panel
//     * @throws Exception 
//     */
//    public SocketClient(String pIpAddress, int pPort, Game pPanel) throws Exception {
//        panel = pPanel;
//        try {
//            Socket socket = new Socket( pIpAddress, pPort);
//            setSocket(socket);
//        } catch (Exception ex) {
//                LOGGER.log(Level.SEVERE, "Error creating socket", ex);
//        }
//        
//    }
//    
//    /**
//     * Constructor que no contiene panel
//     * @param pIpAddress String corresponde a la direccion ip del servidor
//     * @param pPort int corresponde al puerto del servidor
//     * @throws Exception 
//     */
//    public SocketClient(String pIpAddress, int pPort) throws Exception {
//        try {
//            Socket socket = new Socket( pIpAddress, pPort);
//            setSocket(socket);
//        } catch (Exception ex) {
//                LOGGER.log(Level.SEVERE, "Error creating socket", ex);
//        }
//    }
//    
//    /**
//     * Constructor a partir del socket de referencia
//     * @param pConexion Socket corresponde al socket de referencia
//     * @throws Exception 
//     */
//    public SocketClient(Socket pConexion) throws Exception {
//        try {
//            setSocket(pConexion);
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "Error setting socket", ex);
//        }
//    }
//    
//    /**
//     * Establece la informacion que se enviara a traves del socket
//     * @param pConexion corresponde al socket al que se quiere realizar una conexion
//     * @throws Exception 
//     */
//    public void setSocket(Socket pConexion) throws Exception {
//        this.conexion = pConexion;
//        this.input = new BufferedReader(new InputStreamReader(pConexion.getInputStream()));
//        this.output = new PrintWriter(pConexion.getOutputStream(), true);
//        this.binOutput = new DataOutputStream(pConexion.getOutputStream());
//        this.obOutput = new ObjectOutputStream(pConexion.getOutputStream());
//        this.listen = true;
//    }
//    
//    /**
//     * Cierra las conexiones del socket
//     * @throws IOException 
//     */
//    public void deSetSocket() throws IOException{
//        conexion.close();
//        input.close();
//        output.close();
//        binOutput.close();
//        obOutput.close();
//    }
//
//    /**
//     * Termina el hilo y cierra el socket
//     */
//    public void close() {
//        this.listen = false;
//        try {
//            conexion.close();
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, "Error closing socket", ex);
//        }
//    }
//
//    /**
//     * Procesa un mensaje recibido
//     * @param pMsg String corresponde al mensaje recibido
//     */
//    public void processMessage(String pMsg) {
//        panel.setArea(pMsg);
//        System.out.println("Client: " + pMsg);
//    }
//    
//    /**
//     * Procesa un objeto recibido
//     * @param pData Package corresponde al paquete de datos recibido
//     */
//    public void processObject(Package pData) {
//        panel.setArea(pData.getNickName()+": "+pData.getMessage());
//    }
//
//    public void processMessage(byte[] pMsg) {
//        //This method should be implemented with the specific processing 
//        //logic that you want to apply to the received message.
//        //You may use dependency injection to separate the processing logic from the communication logic.
//    }
//
//    public void sendMsg(String pMsg) throws IOException {
//        /*
//        try {
//            this.output.println(pMsg);
//            this.output.flush();
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "Error sending message", ex);
//        }
//        */
//        binOutput.writeUTF(pMsg);
//    }
//    
//    public void sendObj(Package pObj) throws IOException {
//        obOutput.writeObject(pObj);
//        this.obOutput.flush();
//    }
//    
//
//    public void sendMsg(byte[] pMsg) {
//        try {
//            this.binOutput.write(pMsg);
//            this.binOutput.flush();
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "Error sending message", ex);
//        }
//    }
//
//    public String transformarMensaje() throws IOException {
//        return input.readLine();
//    }
//
//    public BufferedReader getInput() {
//        return input;
//    }
//        
//        
//}