/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();

            int opcao;
            do {
                System.out.println("O que deseja fazer?");
                System.out.println("1 - Ver a temperatura atual de todas as cidades.");
                System.out.println("2 - Ver dados de uma cidade.");
                System.out.println("3 - Inserir nova cidade.");
                System.out.println("4 - Alterar a temperatura de uma cidade.");
                System.out.println("5 - Remover uma cidade.");
                System.out.println("0 - Sair.");

                while (true) {
                    try {
                        System.out.print("Opcao: ");
                        opcao = in.nextInt();
                        in.nextLine(); //para dar reset ao input...
                        break;
                    } catch (InputMismatchException ex) {
                        System.out.println("Tem de ser um numero");
                        in.nextLine();

                    }
                }
                output.writeInt(opcao);
                output.flush();

                switch (opcao) {
                    case 1:
                        verTemperaturas(input, output);
                        break;
                    case 2:
                        dadosCidade(input, output);
                        break;
                    case 3:
                        inserirCidade(input, output);
                        break;
                    case 4:
                        alterarCidade(input, output);
                        break;
                    case 5:
                        removerCidade(input, output);
                        break;
                    case 0:
                        System.out.println("Adeus");
                        break;
                    default:
                        System.out.println("Comando inválido.");
                }
            } while (opcao != 0);

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    private static void verTemperaturas(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        ArrayList<Temp> cidades = (ArrayList<Temp>) input.readObject();
        if (cidades.size() > 0) {
            for (Temp c : cidades) {
                System.out.println("Cidade: " + c.getCidade() + " - Temperatura atual: " + c.getTempAtual());
            }
        } else {
            System.out.println("Sem dados para apresentar");
        }
    }

    private static void dadosCidade(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nome da cidade a apresentar: ");
        String cidade = in.nextLine();
        output.writeUTF(cidade);
        output.flush();
        try {
            Temp c = (Temp) input.readObject();
            System.out.println("Cidade: " + c.getCidade());
            System.out.println("Temperatura Minima: " + c.getTempMinima());
            System.out.println("Temperatura Atual: " + c.getTempAtual());
            System.out.println("Temperatura Maxima: " + c.getTempMaxima());
        } catch (Exception ex) {
            System.out.println("Essa cidade não existe no sistema.");
        }
    }

    private static void inserirCidade(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nome da cidade: ");
        String cidade = in.nextLine();
        output.writeUTF(cidade);
        output.flush();
        if (input.readBoolean()) {
            int temp;
            while (true) {
                try {
                    System.out.println("Temperatura atual: ");
                    temp = in.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Tem de ser um numero.");
                    in.nextLine();
                }
            }

            output.writeInt(temp);
            output.flush();
            if (input.readBoolean()) {
                System.out.println("Cidade adicionada com sucesso.");
            } else {
                System.out.println("Erro ao inserir cidade");
            }
        } else {
            System.out.println("Essa cidade já existe");
        }
    }

    private static void alterarCidade(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nome da cidade a alterar: ");
        String nome = in.nextLine();
        output.writeUTF(nome);
        output.flush();
        if (input.readBoolean()) {

            int temp;
            while (true) {
                try {
                    System.out.print("Nova temperatura: ");
                    temp = in.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Tem de ser um numero");
                    in.nextLine();

                }
            }
            output.writeInt(temp);
            System.out.println("Temperatura alterada");
        } else {
            System.out.println("Essa cidade não existe no sistema.");
        }
    }

    private static void removerCidade(ObjectInputStream input, ObjectOutputStream output) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Nome da cidade a remover: ");
        String nomeC = in.nextLine();
        output.writeUTF(nomeC);
        output.flush();
        if (input.readBoolean()) {
            System.out.print("Cidade removida com sucesso.");
        } else {
            System.out.println("Essa cidade não existe no sistema.");
        }
    }
}
