/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sotic
 */
public class Server {
    
    private ServerSocket serverSocket;
    private int clientesMax;
    
    public Server (ServerSocket serverSocket, int clientesMax){
        this.serverSocket = serverSocket;
        this.clientesMax = clientesMax;
    }
    
    public void startServer(){
        System.out.println("Server activo");
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                if (ClientHandler.clientHandlers.size() <= clientesMax){
                    System.out.println("Cliente "+ ClientHandler.clientHandlers.size() + " conectado");
                    ClientHandler clientHandler = new ClientHandler(socket);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                }else{
                    System.out.println("No se puede ingresar. Cantidad máxima de usuarios");                }
            }        }
        catch(IOException e){
            
        }
    }
    
    public void closeServerSocket(){
        try{
            if (serverSocket != null){
                serverSocket.close();
            }
        }
        catch(IOException e){
        }
        System.out.println("Server cerrado");
    }
    
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(Constants.PUERTO);
        Server server = new Server(serverSocket, 4);
        server.startServer();
    }
}
