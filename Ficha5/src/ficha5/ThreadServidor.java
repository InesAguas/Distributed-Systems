/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ines Aguas
 */
public class ThreadServidor extends Thread {
    
    Socket socket;
    static ArrayList<Temp> cidades = new ArrayList<Temp>();
    DataOutputStream output;
    DataInputStream input;
    
             
    public ThreadServidor(Socket socket) {
        this.socket = socket;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
           System.out.println("Erro na thread: " + ex.getMessage());
        }
        
    }
    
    @Override
    public void run() {
        int opcao;
        try {
            do {
                opcao = input.readInt();
                switch(opcao) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        output.writeBoolean(true);
                        inserirCidade();
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 0:
                        break;
                    default: 
                }
            }while(opcao != 0);
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
    
    
    private void inserirCidade() {
        try {
            boolean exists = false;
            String nome = input.readUTF();
            int temp;
            
            for( Temp c : cidades) {
                if(c.getCidade().equalsIgnoreCase(nome)) {
                    output.writeBoolean(false);
                    exists = true;
                }
            }
            
            
            if(!exists) {
                output.writeBoolean(true);
                temp = input.readInt();
                Temp t = new Temp(nome, temp);
                cidades.add(t);
                output.writeBoolean(true);
            }
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
    
    
}
