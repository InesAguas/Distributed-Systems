/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author inoca
 */
public class DatagramReceive {
    public static void main(String[] args) {
    byte[] inbuf = new byte[1000];
    try {
        int port = 4000;
        DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
        DatagramSocket socket = new DatagramSocket(port);
        while(true) {
            socket.receive(dp);
            String conteudo = new String(dp.getData(), 0, dp.getLength());
            System.out.println(conteudo);
        }
        }
        catch (Exception e) {
        System.out.println(e);
        }
    }
}
