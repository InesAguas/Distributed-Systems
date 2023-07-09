/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ficha8;

import java.rmi.*;

/**
 *
 * @author Ines Aguas
 */
public interface InterfaceCliente extends Remote{
    
    public void atualizaScore(Jogo jogo, String tempo) throws RemoteException;
    
    public void atualizaEstado(Jogo jogo, String tempo) throws RemoteException;
    
    public void atualizaTempo(Jogo jogo, String tempo) throws RemoteException;
    
    public void jogoRemovido(Jogo jogo, String tempo) throws RemoteException;
}
