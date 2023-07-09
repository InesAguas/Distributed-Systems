/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha8;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.registry.*;
import java.util.InputMismatchException;

/**
 *
 * @author Ines Aguas
 */
public class Cliente {
    
    public static void main(String[] args) throws RemoteException {
        ClienteApp cli = new ClienteApp();
        
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
            
            System.out.println("Ligação ao servidor feita com sucesso.\nIP local: " 
                    + InetAddress.getLocalHost().getHostAddress());
            
            Jogo[] jogos = objRemoto.obtemJogoPorEstado(6);
            
            if(jogos == null) {
                System.out.println("Não existem jogos. Volte mais tarde");
                return;
            }
            
            System.out.println("--- Lista de jogos ----\n(id, equipa 1, equipa 2)");
            for(Jogo j: jogos) {
                System.out.println(j.getId() + ": " + j.getEquipa1() + " - " + j.getEquipa2());
            }
            
            int id;
            Boolean ligado;
            
            do {
                id = pedirNumero("Id do jogo a consultar:");
                ligado = objRemoto.login((InterfaceCliente)cli, id);
                
                Jogo j = objRemoto.obtemJogo(id);
                System.out.println("Dados atuais do jogo: ");
                System.out.println(j);
                
                if(!ligado) {
                    System.out.println("Não existe jogo com esse ID. Volte a tentar");
                }
            }while(!ligado);
            
            String fim;
            do {
                System.out.println("Digite 'fim' para sair.");
                fim = in.nextLine().trim();
            }while(!fim.equalsIgnoreCase("fim"));
            
            objRemoto.logout(cli);
            System.exit(0);
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            System.out.println(ex);
        }
    }
    
    public static int pedirNumero(String message) {
        Scanner in = new Scanner(System.in);
        int id;
        System.out.println(message);
        while (true) {
            try {
                id = in.nextInt();
                in.nextLine();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Tem de ser um numero");
                in.nextLine();
            }
        }
        return id;

    }
}
