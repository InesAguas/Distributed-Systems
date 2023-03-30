
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author inoca
 */
public class ClienteTCPsimple {
    

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("192.168.1.106", 4000);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("Olá servidor - Ines");
            String answer = input.readUTF();
            System.out.println("Resposta:" + answer);
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

}
