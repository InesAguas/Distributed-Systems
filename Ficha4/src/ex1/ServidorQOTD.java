/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Ines Aguas
 */
public class ServidorQOTD {
    
    public static void main(String[] args) {
        String[] quotes = {"\"We cannot solve problems with the kind of thinking we employed when we came up with them.\" (Albert Einstein)", 
            "\"Learn as if you will live forever, live like you will die tomorrow.\"(Mahatma Gandhi)", 
            "\"It is better to fail in originality than to succeed in imitation.\" (Herman Melville)",
            "\"Success usually comes to those who are too busy looking for it.\" (Henry David Thoreau)",
            "\"Talent wins games, but teamwork and intelligence win championships.\" (Michael Jordan)"};
        
        try {
            ServerSocket serversocket = new ServerSocket(17);
            while (true) {
                Socket socket = serversocket.accept();

                System.out.println("IP local: " + serversocket.getInetAddress().getHostAddress() 
                        + " - Porto: " + serversocket.getLocalPort());
                System.out.println("IP remoto: " + socket.getInetAddress().getHostAddress()
                        + " - Porto: " + socket.getLocalPort());
                
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                String msg = quotes[new Random().nextInt(quotes.length)];

                byte[] data = msg.getBytes();
                output.write(data, 0, data.length);

                output.close();
                socket.close();
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

}
