/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author sotic
 */
public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: " + clientUsername + " ha entrado al chat!");
        } catch (IOException ex) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    @Override
    public void run() {
         String mensajeCliente;
         
         while(socket.isConnected()){
            try{
                 mensajeCliente = bufferedReader.readLine();
                 broadcastMessage(mensajeCliente);
            }
            catch (IOException ex) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
         }
    }

    private void broadcastMessage(String mensaje) {
       for (ClientHandler client : clientHandlers){
            try {
                if (!client.clientUsername.equals(clientUsername)){
                    client.bufferedWriter.write(mensaje);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException ex) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
       }
    }
    
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("Server: " +clientUsername+" ha dejado el chat.");
    }
    
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }
        catch (IOException ex) {
        }
    }
}