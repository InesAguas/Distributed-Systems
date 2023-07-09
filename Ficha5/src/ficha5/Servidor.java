/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ines Aguas
 */
public class Servidor {
    
    public static void main(String[] args) {
        try {
            ServerSocket serversocket = new ServerSocket(4000);
            
            while(true) {
                Socket socket = serversocket.accept();
                //fazer print de IP remoto, porto remoto, IP local e porto local.
                System.out.println("IP local: " + socket.getLocalAddress().getHostAddress() 
                        + "Porto local: " + socket.getLocalPort());
                System.out.println("IP remoto: " + socket.getInetAddress().getHostAddress() 
                        + "Porto remoto: " + socket.getPort());
                ThreadServidor thr = new ThreadServidor(socket);
                thr.start();
            }
        } catch (Exception ex) {
           System.out.println("Erro: " + ex.getMessage());
        }
        
    }
}
