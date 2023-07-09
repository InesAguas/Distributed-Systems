/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha7;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

/**
 *
 * @author Ines Aguas
 */
public class Servidor extends UnicastRemoteObject implements InterfaceJogo{
    
    ArrayList<Jogo> jogos = new ArrayList<>();
    
    Servidor() throws RemoteException{
        super();
    }
    
    @Override
    public Jogo obtemJogo(int id) throws RemoteException {
        if(jogos.size() <= 0) {
            return null;
        }
        
        for(Jogo j: jogos) {
            if(j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    @Override
    public Jogo[] obtemJogoPorEstado(int estado) throws RemoteException {
        ArrayList<Jogo> jogosEstado = new ArrayList<>();
        if(jogos.size() <= 0) {
            return null;
        }
        
        for(Jogo j: jogos) {
            if(j.getEstado() == estado) {
                jogosEstado.add(j);
            }
        }
        
        if(!jogosEstado.isEmpty()) {
            Jogo[] lista = (Jogo[]) jogosEstado.toArray();
            return lista;
        }
        return null;
    }

    @Override
    public boolean adicionaJogo(Jogo novo) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeJogo(int id) throws RemoteException {
        return false;
    }

    @Override
    public boolean alteraEstado(int id, int novo_estado) throws RemoteException {
        return false;
    }

    @Override
    public boolean alteraScore(int id, int score1, int score2) throws RemoteException {
        return false;
    }
    
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Servidor serv = new Servidor();
            
            reg.bind("jogos", serv);
            
            System.out.println("Servidor RMI iniciado.\nIP: " + InetAddress.getLocalHost().getHostAddress());
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}
