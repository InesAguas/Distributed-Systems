/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2;

import java.io.DataInputStream;
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
        //inicia os valores todos a 0
        int[][] stats = new int[quotes.length][3];

        try {
            ServerSocket serversocket = new ServerSocket(17);
            while (true) {
                Socket socket = serversocket.accept();
                String comando = "";
                int action = 0;

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                int indice = new Random().nextInt(quotes.length);
                
                stats[indice][0] += 1;
                
                String msg = quotes[indice] + "\nviews: " + stats[indice][0] + " - likes: " + stats[indice][1] + " - dislikes: " + stats[indice][2];
                byte[] data = msg.getBytes();
                output.write(data, 0, data.length);
                
                do {
                    byte[] buffer = new byte[5000];
                    int bytesRead = input.read(buffer);

                    comando = new String(buffer, 0, bytesRead);

                    switch (comando.toLowerCase()) {
                        case "like":
                            action = 1;
                            msg = "Like received successfully.";
                            break;
                        case "dislike":
                            action = 2;
                            msg = "Dislike received successfully.";
                            break;
                        case "cancel":
                            action = 0;
                            msg = "Last command cancelled.";
                            break;
                        case "end":
                            msg = "Goodbye.";
                            break;
                        default:
                            msg = "Invalid command";
                    }

                    data = msg.getBytes();
                    output.write(data, 0, data.length);

                } while (!comando.equalsIgnoreCase("end"));
 
                if (action == 1 || action == 2) {
                    stats[indice][action] += 1;
                }
                
                input.close();
                output.close();
                socket.close();
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
