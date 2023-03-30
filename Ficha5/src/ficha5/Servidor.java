/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ines Aguas
 */
public class Servidor {
    
    public static void main(String[] args) {
        int threads = 0;
        
        try {
            ServerSocket serversocket = new ServerSocket(4000);
            
            while(true) {
                Socket socket = serversocket.accept();
                //fazer print de IP remoto, porto remoto, IP local e porto local.
                ThreadServidor thr = new ThreadServidor(socket);
                thr.start();
                threads++;
                System.out.println("Nova thread iniciada.");
                System.out.println("Threads ativas: " + threads);
            }
        } catch (Exception ex) {
           System.out.println("Erro: " + ex.getMessage());
        }
        
    }
}
