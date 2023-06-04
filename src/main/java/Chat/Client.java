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
import java.util.Scanner;

/**
 *
 * @author sotic
 */
public class Client {
    
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    
    public Client(Socket socket, String clientUsername) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = clientUsername;
        } catch (IOException ex) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void enviarMensaje(){
        try{
            bufferedWriter.write(clientUsername);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            Scanner scan = new Scanner(System.in);
            while (socket.isConnected()){
                String mensajeEnviar = scan.nextLine();
                bufferedWriter.write(clientUsername + ": " + mensajeEnviar);
                bufferedWriter.newLine();
                bufferedWriter.flush(); 
            }
            scan.close();

        }
        catch (IOException ex) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void escucharMensaje(){
        new Thread(() -> {
            String msj;
            
            while (socket.isConnected()){
                try{
                    msj = bufferedReader.readLine();
                    System.out.println(msj);
                }
                catch (IOException ex) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }
    
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
    
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba su nombre: ");
        Socket socket = new Socket("localhost", Constants.PUERTO);
        String username = scanner.nextLine();
        Client client = new Client(socket, username);
        client.escucharMensaje();        
        client.enviarMensaje();
        scanner.close();
    }
}
