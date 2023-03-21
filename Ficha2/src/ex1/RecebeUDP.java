/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 *
 * @author inoca
 */
public class RecebeUDP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Indique o porto:");
        int porto = in.nextInt();

        byte[] inbuf = new byte[1000];
        try {
            DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
            DatagramSocket socket = new DatagramSocket(porto);
            while (true) {
                socket.receive(dp);
                System.out.print("Ip origem: " + dp.getAddress().getHostAddress() + " - Porto origem: " + dp.getPort());
                String msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println(" - Mensagem: " + msg + " (" + dp.getLength() + " bytes)");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
