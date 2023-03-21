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
public class MulticastReceive {

    public static void main(String[] args) {
        try {
            InetAddress in = InetAddress.getByName("225.0.0.6");
            int port = 4000;
            MulticastSocket s = new MulticastSocket(port);
            s.joinGroup(in);

            byte buf[] = new byte[100];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            s.receive(dp);
            String str = new String(dp.getData());
            System.out.println("Mensagem recebida:" + str);
            s.leaveGroup(in);
            s.close();
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
}
