/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha7;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inoca
 */
public class Cliente {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip;
        do {
            System.out.println("IP ou hostname: ");
            ip = in.nextLine();
            if (ip.isBlank()) {
                System.out.println("Nao pode introduzir dados vazios.");
            }
        } while (ip.isBlank());

        try {
            Registry reg = LocateRegistry.getRegistry(ip, 1099);
            InterfaceJogo objRemoto = (InterfaceJogo) reg.lookup("jogos");
            
            System.out.println("Ligação ao servidor feita com sucesso.\nIP local: " + InetAddress.getLocalHost().getHostAddress());
            
            int opcao;
            do {
                System.out.println("----- MENU -----");
                System.out.println("1 - Ver dados de um jogo");
                System.out.println("2 - Ver dados de jogos por estado");
                System.out.println("3 - Adicionar um jogo");
                System.out.println("4 - Remover um jogo");
                System.out.println("5 - Alterar estado de um jogo");
                System.out.println("6 - Alterar score de um jogo");
                System.out.println("0 - Sair do programa");
              
                opcao = in.nextInt();
                in.nextLine();
                
                switch(opcao) {
                    case 1: break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                    case 6: break;
                    case 0: break;
                    default: break;
                }
            }while(opcao != 0);
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        
    }
}
