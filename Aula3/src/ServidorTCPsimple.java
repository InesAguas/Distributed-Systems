
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author inoca
 */
public class ServidorTCPsimple {

    public static void main(String[] args) {
        try {
            ServerSocket serversocket = new ServerSocket(4000);
            while (true) {
                Socket socket = serversocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                String mensagem = input.readUTF();
                System.out.println("Mensagem: " + mensagem);
                output.writeUTF("Tudo bem?");
                input.close();
                output.close();
                socket.close();
            }
//serversocket.close();
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
    }

}
