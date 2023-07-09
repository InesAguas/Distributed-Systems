/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;
import java.rmi.*;

/**
 *
 * @author Ines Aguas
 */
public interface InterfaceOliveira extends Remote{
    
    public void iniciarTemp(int temp) throws RemoteException;
    public int getTempAtual() throws RemoteException;
    public int getTempAtualF() throws RemoteException;
    public int getTempMinima() throws RemoteException;
    public int getTempMaxima() throws RemoteException;
    public void setTempAtual(int tempAtual) throws RemoteException;
    public String getDados() throws RemoteException;

}
