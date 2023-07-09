/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author Ines Aguas
 */
public class Servidor extends UnicastRemoteObject implements InterfaceOliveira {

    int tempAtual, tempMinima, tempMaxima;

    Servidor() throws RemoteException {
        super();
        this.tempAtual = 0;
        this.tempMinima = 0;
        this.tempMaxima = 0;
    }

    @Override
    public void iniciarTemp(int temp) throws RemoteException {
        this.tempAtual = temp;
        this.tempMinima = temp;
        this.tempMaxima = temp;
    }

    @Override
    public int getTempAtual() {
        return tempAtual;
    }

    @Override
    public int getTempAtualF() {
        return (tempAtual * 9 / 5) + 32;
    }

    @Override
    public int getTempMinima() {
        return tempMinima;
    }

    @Override
    public int getTempMaxima() {
        return tempMaxima;
    }

    @Override
    public void setTempAtual(int tempAtual) {
        this.tempAtual = tempAtual;

        if (tempAtual < tempMinima) {
            tempMinima = tempAtual;
        }

        if (tempAtual > tempMaxima) {
            tempMaxima = tempAtual;
        }

    }

    @Override
    public String getDados() {
        return "Temperatura atual: " + tempAtual + "\nTemperatura minima: " + tempMinima + "\nTemperatura maxima: " + tempMaxima;
    }

    public static void main(String[] args) {
        try {
            Servidor serv = new Servidor();
            Naming.rebind("oliveira", serv);

            System.out.println("Servidor RMI iniciado. IP: " + InetAddress.getLocalHost().getHostAddress());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
    }

}
