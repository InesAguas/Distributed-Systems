/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author inoca
 */
public class MulticastSend {

    public static void main(String[] args) {
        try {
            InetAddress in = InetAddress.getByName("225.0.0.6");
            int port = 4000;
            String msg = "Ola sou a Ines";
            MulticastSocket s = new MulticastSocket();
            byte[] send = msg.getBytes();
            DatagramPacket dp = new DatagramPacket(send, send.length, in, port);
            s.setTimeToLive(5);
            s.send(dp);
            s.close();
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
}
