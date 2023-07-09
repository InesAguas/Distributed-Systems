/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.util.Scanner;
import java.rmi.*;
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

        String url = "//" + ip + ":1099/oliveira";

        try {
            InterfaceOliveira obj = (InterfaceOliveira) Naming.lookup(url);
            int opcao;
            do {
                
                System.out.println("---- MENU ----");
                System.out.println("1 - Inicializar temperatura atual");
                System.out.println("2 - Obter temperatura atual (ºC)");
                System.out.println("3 - Obter temperatura atual em ºF");
                System.out.println("4 - Obter temperatura minima");
                System.out.println("5 - Obter a temperatura maxima");
                System.out.println("6 - Alterar temperatura atual");
                System.out.println("7 - Obter todos os dados");

                System.out.println("Opcao: ");
                opcao = in.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Temperatura atual: ");
                        int tempA;
                        while (true) {
                            try {
                                tempA = in.nextInt();
                                in.nextLine();
                                break;
                            } catch (InputMismatchException ex) {
                                System.out.println("Tem de ser um numero");
                                in.nextLine();
                            }
                        }
                        obj.iniciarTemp(tempA);
                        System.out.println("Temperatura incializada com sucesso. As temperatuas maxima e minima sao identicas a atual");
                        break;
                    case 2:
                        System.out.println("Temperatua atual: " + obj.getTempAtual() + "ºC");
                        break;
                    case 3:
                        System.out.println("Temperatua atual: " + obj.getTempAtualF() + "ºF");
                        break;
                    case 4:
                        System.out.println("Temperatua minima: " + obj.getTempMinima() + "ºC");
                        break;
                    case 5:
                        System.out.println("Temperatua maxima: " + obj.getTempMaxima() + "ºC");
                        break;
                    case 6:
                        System.out.println("Nova temperatura: ");
                        int tempB;
                        while (true) {
                            try {
                                tempB = in.nextInt();
                                in.nextLine();
                                break;
                            } catch (InputMismatchException ex) {
                                System.out.println("Tem de ser um numero");
                                in.nextLine();
                            }
                        }
                        obj.setTempAtual(tempB);
                        System.out.println("Temperatura alterada com sucesso.");
                        break;
                    case 7:
                        System.out.println(obj.getDados());
                        break;
                    case 0:
                        System.out.println("Adeus");
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
            } while (opcao != 0);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
