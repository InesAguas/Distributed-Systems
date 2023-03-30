/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ines Aguas
 */
public class ClienteQOTD {

    public static void main(String[] args) {
        try {
            String ip;
            do {
                System.out.println("Indique o IP/Hostname do servidor: ");
                Scanner in = new Scanner(System.in);
                ip = in.nextLine();
            
                if(ip.isBlank()) {
                    System.out.println("Ip não pode estar vazio");
                }
            }while(ip.isBlank());
            
            InetAddress ia = InetAddress.getByName(ip);

            Socket socket = new Socket(ia, 17);
            DataInputStream input = new DataInputStream(socket.getInputStream());

            byte[] buffer = new byte[5000];
            int bytesRead = input.read(buffer);

            String quote = new String(buffer, 0, bytesRead);

            System.out.println("Resposta: " + quote);

            input.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
