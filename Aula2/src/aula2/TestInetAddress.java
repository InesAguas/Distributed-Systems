/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author inoca
 */
public class TestInetAddress {
    public static void main (String[] args) {
        try {
            //InetAddress address = InetAddress.getByName("www.estgoh.ipc.pt");
            //InetAddress address = InetAddress.getByName("193.137.79.42");
            //InetAddress address = InetAddress.getByName("localhost");
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("toString: " + address);
            System.out.println("getHostName: " + address.getHostName());
            System.out.println("getHostAddress: " + address.getHostAddress());System.out.println("getCannonical: " + address.getCanonicalHostName());}
            catch (UnknownHostException e) {
            System.out.println(e);
        }
    }
}
