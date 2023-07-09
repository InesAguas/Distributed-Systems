/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Ines Aguas
 */
public class ThreadServidor extends Thread {

    Socket socket;
    static ArrayList<Temp> cidades = new ArrayList<>();
    static int threads = 0;
    static int threadsTotal = 0;
    int numero;
    ObjectOutputStream output;
    ObjectInputStream input;

    public ThreadServidor(Socket socket) {
        this.socket = socket;
        threads++;
        threadsTotal++;
        this.numero = threadsTotal;
        
        System.out.println("Nova thread iniciada.");
        System.out.println("Threads ativas: " + threads);
                
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Erro na thread: " + ex.getMessage());
        }

    }

    @Override
    public void run() {
        int opcao;
        try {
            do {
                opcao = input.readInt();
                System.out.println("Thread " + numero + " - Comando: " + opcao);
                String nome = "";
                int indice;
                switch (opcao) {
                    case 1:
                        output.writeObject(cidades);
                        output.flush();
                        output.reset();
                        break;
                    case 2:
                        nome = input.readUTF();
                        
                        indice = cidadeExiste(nome);
                        if(indice >= 0) {
                            Temp c = cidades.get(indice);
                            output.writeObject(c);
                            output.flush();
                            output.reset();
                        } else {
                            output.writeObject(null);
                            output.flush();
                        }
                        break;
                    case 3:
                        inserirCidade();
                        break;
                    case 4:
                        nome = input.readUTF();
                        System.out.println("hello");
                        System.out.println(nome);
                        indice = cidadeExiste(nome);
                        if(indice >= 0) {
                            output.writeBoolean(true);
                            output.flush();
                            Temp c = cidades.get(indice);
                            int temp = input.readInt();
                            c.setTempAtual(temp);
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                        
                        break;
                    case 5:
                        nome = input.readUTF();
                        indice = cidadeExiste(nome);
                        if(indice >= 0) {
                            cidades.remove(indice);
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                        break;
                    case 0:
                        input.close();
                        output.close();
                        socket.close();
                        break;
                    default:
                        System.out.println("Comando inválido");
                }
            } while (opcao != 0);

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        threads--;
        System.out.println("Fim da thread " + numero + " - Threads ativas: " + threads);
        

    }

    private void inserirCidade() {  
        try {
            //synchronized para nao haver varias threads a inserir a mesma cidade ao mesmo tempo...
            synchronized(cidades) {
            String nome = input.readUTF();
            int temp;
            int i = cidadeExiste(nome);
            if(i >= 0) {
                output.writeBoolean(false);
                output.flush();
            }else {
                output.writeBoolean(true);
                output.flush();
                temp = input.readInt();
                Temp t = new Temp(nome, temp);
                cidades.add(t);
                output.writeBoolean(true);
                output.flush();
            }
            }
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    private int cidadeExiste(String nome) {
        for (Temp c : cidades) {
            if (c.getCidade().equalsIgnoreCase(nome)) {
                return cidades.indexOf(c);
            }
        }
        return -1;
    }

}
