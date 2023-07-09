/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha8;

import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author Ines Aguas
 */
public class ClienteApp extends UnicastRemoteObject implements InterfaceCliente {
    
    ClienteApp() throws RemoteException {
        super();
    }

    @Override
    public void atualizaScore(Jogo jogo, String tempo) throws RemoteException {
        System.out.println("Hora no servidor: " + tempo + "Score alterado: " + jogo.getScore1() + " - " + jogo.getScore2());
    }

    @Override
    public void atualizaTempo(Jogo jogo, String tempo) throws RemoteException {
        System.out.println("Hora no servidor: " + tempo + "Tempo de jogo atualizado: " + jogo.getTempo());
    }

    @Override
    public void atualizaEstado(Jogo jogo, String tempo) throws RemoteException {
        String[] estados = {"não-iniciado", "primeira parte", "intervalo", "segunda parte", "terminado"};
        System.out.println("Hora no servidor: " + tempo + " Estado alterado: " + estados[jogo.getEstado()-1]);
    }

    @Override
    public void jogoRemovido(Jogo jogo, String tempo) throws RemoteException {
        System.out.println("Hora no servidor: " + tempo + " Jogo terminado");
    }
    
}
