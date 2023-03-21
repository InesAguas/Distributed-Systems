/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author inoca
 */
public class ServidorEcho {
    public static void main(String[] args) {

        byte[] inbuf = new byte[1000];
        try {
            DatagramPacket recebe = new DatagramPacket(inbuf, inbuf.length);
            DatagramSocket socketRecebe = new DatagramSocket(7);
            while (true) {
                socketRecebe.receive(recebe);
                String msg = new String(recebe.getData(), 0, recebe.getLength());
                System.out.println("Recebido: " + msg);
                
                DatagramPacket envia = new DatagramPacket(recebe.getData(), recebe.getLength(), recebe.getAddress(), recebe.getPort());
                DatagramSocket socketEnvia = new DatagramSocket();
                socketEnvia.send(envia);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
