/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ines Aguas
 */
public class ClienteQOTD {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip, command;
        int port = 0;
        do {
            System.out.println("IP/Hostname: ");
            ip = in.nextLine();
            if(ip.isBlank()) {
                System.out.println("Ip não pode estar vazio");
            }
        }while(ip.isBlank());
        
        do {
            try {
                System.out.println("Port: ");
                port = in.nextInt();
            }catch(Exception ex) {
                in.nextLine();
                port = 0;
                System.out.println("Tem de ser um numero.");
            }
        }while(port <= 0);
            
        
        try {
            InetAddress ia = InetAddress.getByName(ip);
            Socket socket = new Socket(ia, port);
            
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            byte[] buffer = new byte[5000];
            int bytesRead = input.read(buffer);

            String quote = new String(buffer, 0, bytesRead);
            System.out.println(quote);
            in.nextLine();
            
            do {
                System.out.println("Type a command [like, dislike, cancel, end]");
                System.out.println("Command: ");

                command = in.nextLine();
                
                byte[] sendCommand = command.getBytes();
                output.write(sendCommand, 0, sendCommand.length);
                
                bytesRead = input.read(buffer);
                String msg = new String(buffer, 0, bytesRead);
                System.out.println(msg);
            }while(!command.equalsIgnoreCase("end"));
            
            input.close();
            output.close();
            socket.close();
                    
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}
