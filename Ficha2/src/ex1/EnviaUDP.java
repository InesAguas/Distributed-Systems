/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author inoca
 */
public class EnviaUDP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Indique o IP do destino:");
        String ip = in.nextLine();
        int porto = 0;
        do {
            try {
                System.out.print("Indique o porto destino:");
                porto = in.nextInt();
            } catch (Exception ex) {
                porto = 0;
                System.out.println("Tem de ser um numero");
                in.nextLine();
            }
        } while (porto <= 0);

        String msg = "";
        in.nextLine();

        try {
            InetAddress ia = InetAddress.getByName(ip);

            do {
                System.out.print("Digite 'sair' para sair do programa.\nMensagem a enviar:");
                msg = in.nextLine();
                if (!msg.equalsIgnoreCase("sair")) {
                    byte[] data = msg.getBytes();
                    DatagramPacket dp = new DatagramPacket(data, data.length, ia, porto);
                    DatagramSocket socket = new DatagramSocket();
                    socket.send(dp);
                }
            } while (!msg.equalsIgnoreCase("sair"));
        } catch (Exception ex) {
            System.out.print("Erro");
        }

    }
}
