/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha8;

import java.net.InetAddress;
import java.util.Scanner;
import java.rmi.registry.*;
import java.util.InputMismatchException;

/**
 *
 * @author Ines Aguas
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

            System.out.println("Ligação ao servidor feita com sucesso.\nIP local: " 
                    + InetAddress.getLocalHost().getHostAddress());

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
                
                System.out.print("Opcao: ");
                opcao = in.nextInt();
                in.nextLine();

                switch (opcao) {
                    case 1:
                        int idJogo = pedirNumero("Id do jogo:");
                        Jogo j = objRemoto.obtemJogo(idJogo);
                        if (j == null) {
                            System.out.println("Jogo não encontrado.");
                        } else {
                            System.out.println(j);
                        }
                        break;
                    case 2:
                        int estadoJogos = pedirNumero("""
                                                      Estados: não-iniciado (1), primeira parte (2), intervalo (3), segunda parte (4), terminado (5)
                                                      Insira o estado: """);
                        Jogo[] jogos = objRemoto.obtemJogoPorEstado(estadoJogos);
                        if (jogos != null && jogos.length > 0) {
                            for (Jogo jogo : jogos) {
                                System.out.println(jogo);
                            }
                        } else {
                            System.out.println("Não foram encontrados jogos.");
                        }
                        break;
                    case 3:
                        String eq1, eq2, data, hora;
                        System.out.print("Primeira equipa: ");
                        eq1 = in.nextLine();
                        System.out.print("Segunda equipa: ");
                        eq2 = in.nextLine();
                        System.out.print("Data de inicio[dd/mm/aaaa]: ");
                        data = in.nextLine();
                        System.out.print("Hora de inicio[hh:mm]: ");
                        hora = in.nextLine();
                        Jogo novoJogo = new Jogo(eq1, eq2, data + " - " + hora);
                        if(objRemoto.adicionaJogo(novoJogo)) {
                            System.out.println("Jogo adicionado com sucesso");
                        } else {
                            System.out.println("Erro ao adicionar jogo. Verifique os dados introduzidos");
                        }
                        break;
                    case 4:
                        int idRemover = pedirNumero("Id do jogo a remover: ");
                        if(objRemoto.removeJogo(idRemover)) {
                            System.out.println("Jogo removido com sucesso");
                        } else {
                            System.out.println("Jogo não encontrado");
                        }
                        break;
                    case 5:
                        int idAlterar = pedirNumero("Id do jogo: ");
                        int novoEstado = pedirNumero("""
                                                      Estados: n\não-iniciado (1), primeira parte (2), intervalo (3), segunda parte (4), terminado (5)
                                                      Novo estado: """);
                        if(objRemoto.alteraEstado(idAlterar, novoEstado)) {
                            System.out.println("Estado alterado com sucesso");
                        } else {
                            System.out.println("Erro ao alterar estado. Verifique os dados");
                        }
                        break;
                    case 6:
                        int idJ = pedirNumero("Id do jogo: ");
                        int s1 = pedirNumero("Score 1: ");
                        int s2 = pedirNumero("Score 2: ");
                        if(objRemoto.alteraScore(idJ, s1, s2)) {
                            System.out.println("Score alterado com suceso");
                        } else {
                            System.out.println("Erro ao alterar score. Verifique os dados inseridos");
                        }
                        break;
                    case 0:
                        System.out.println("Adeus");
                        break;
                    default:
                        break;
                }
            } while (opcao != 0);

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
