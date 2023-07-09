/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ficha7;

import java.rmi.*;

/**
 *
 * @author inoca
 */
public interface InterfaceJogo extends Remote{
    
    Jogo obtemJogo(int id) throws RemoteException;
    Jogo[] obtemJogoPorEstado(int estado) throws RemoteException;
    boolean adicionaJogo(Jogo novo) throws RemoteException;
    boolean removeJogo(int id) throws RemoteException;
    boolean alteraEstado(int id, int novo_estado) throws RemoteException;
    boolean alteraScore(int id, int score1, int score2) throws RemoteException;
}
