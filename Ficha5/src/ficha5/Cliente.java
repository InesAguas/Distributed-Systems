/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ines Aguas
 */
public class Cliente {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip;
        int port;
        do {
            System.out.println("IP/Hostname: ");
            ip = in.nextLine();
            if (ip.isBlank()) {
                System.out.println("Ip não pode estar vazio");
            }
        } while (ip.isBlank());

        do {
            try {
                System.out.println("Port: ");
                port = in.nextInt();
            } catch (Exception ex) {
                in.nextLine();
                port = 0;
                System.out.println("Tem de ser um numero.");
            }
        } while (port <= 0);

        try {
            InetAddress ia = InetAddress.getByName(ip);
            Socket socket = new Socket(ia, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            int opcao;
            do {
                System.out.println("O que deseja fazer?");
                System.out.println("1 - Ver a temperatura atual de todas as cidades.");
                System.out.println("2 - Ver dados de uma cidade.");
                System.out.println("3 - Inserir nova cidade.");
                System.out.println("4 - O que deseja fazer?");
                System.out.println("5 - O que deseja fazer?");
                System.out.println("6 - Alterar a temperatura de uma cidade.");
                System.out.println("7 - Remover uma cidade.");
                System.out.println("0 - Sair.");

                opcao = in.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("opcao 1");
                        output.writeInt(1);
                        
                        break;
                    case 2:
                        output.writeInt(2);
                        break;
                    case 3:
                        output.writeInt(3);
                        if(input.readBoolean()) {
                            inserirCidade(input, output);
                        } else {
                            System.out.println("Erro");
                        }
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
                        System.out.println("Comando inválido.");
                }
            } while (opcao != 0);

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }
    
    private static void inserirCidade(DataInputStream input, DataOutputStream output) {
        Scanner in = new Scanner(System.in);
        System.out.println("Nome da cidade: ");
        String cidade = in.nextLine();
        
        try {
            output.writeUTF(cidade);
            if(input.readBoolean()) {
                System.out.println("Temperatura atual: ");
                int temp = in.nextInt();
                output.writeInt(temp);
                if(input.readBoolean()) {
                    System.out.println("Cidade adicionada com sucesso.");
                } else {
                    System.out.println("Erro ao inserir cidade");
                }
            } else {
                System.out.println("Essa cidade já existe");
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
