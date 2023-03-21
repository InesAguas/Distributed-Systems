/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author inoca
 */
public class DatagramSend {
    public static void main(String[] args) {
        String s = "This is a test. Ines";
        byte[] data = s.getBytes();
        try {
            InetAddress ia = InetAddress.getByName("192.168.2.90");
            int port = 4000;
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, port); 
            DatagramSocket socket = new DatagramSocket();
            socket.send(dp);
            }
            catch (Exception e) {
            System.out.println(e);
        }
    }
}
