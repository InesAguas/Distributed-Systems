/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 *
 * @author inoca
 */
public class ClienteEcho {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Indique o IP (ou hostname) do destino:");
        String ip = in.nextLine();
        String msg = "";

        try {
            InetAddress ia = InetAddress.getByName(ip);

            do {
                System.out.println("Digite 'sair' para sair do programa.\nMensagem a enviar: ");
                msg = in.nextLine();
                if (msg.equalsIgnoreCase("sair")) {
                    break;
                }
                byte[] data = msg.getBytes();
                
                DatagramPacket envia = new DatagramPacket(data, data.length, ia, 7);
                DatagramSocket socket = new DatagramSocket();
                socket.send(envia);
                long start = System.currentTimeMillis();

                byte[] inbuf = new byte[1000];
                DatagramPacket recebe = new DatagramPacket(inbuf, inbuf.length);
                socket.setSoTimeout(5000);
                socket.receive(recebe);
                long end = System.currentTimeMillis();
                String recebido = new String(recebe.getData(), 0, recebe.getLength());
                System.out.print("Mensagem recebida: " + recebido);
                System.out.println(" - Tempo de resposta: " + (end - start) + "ms");
            } while (!msg.equalsIgnoreCase("sair"));

        } catch (SocketTimeoutException ex) {
            System.out.println("Erro - Sem resposta do servidor");
        } catch (Exception ex) {
            System.out.print("Erro");
        }
    }
}
