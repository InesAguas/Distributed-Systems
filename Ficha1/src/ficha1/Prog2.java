/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha1;

import java.util.Scanner;

/**
 *
 * @author inoca
 */
public class Prog2 extends Thread {
    
    Conta contaA, contaB, contaC;
    String nome;
    
    public Prog2(Conta contaA, Conta contaB, Conta contaC, String nome) {
        this.contaA = contaA;
        this.contaB = contaB;
        this.contaC = contaC;
        this.nome = nome;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            if(contaA.transferir(contaB, 1)) {
                System.out.println(nome + " - A transferiu 1€ para B");
            } else {
                System.out.println(nome + " - Saldo insuficiente na conta A");
            }
            if(contaB.transferir(contaC, 1)) {
                System.out.println(nome + " - B transferiu 1€ para C");
            } else {
                System.out.println(nome + " - Saldo insuficiente na conta B");
            }
        }
    }
    
    public static void main(String[] args) {
        
        int num = 0;
        do {
            System.out.println("Quantas threads deseja? ");
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            if(num <= 0) {
                System.out.println("O numero tem de ser maior que 0");
            }
        }while(num <= 0);
        
        Conta contaA = new Conta(10*num);
        Conta contaB = new Conta(0);
        Conta contaC = new Conta(0);
        
        Prog2 threads[] = new Prog2[num];
        for(int i = 0; i < num; i++) {
            threads[i] = new Prog2(contaA, contaB, contaC, "thread"+i);
            threads[i].start();
        }
        
        //segundo loop para dar join nas threads...
        for(int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
               System.out.println("Erro");
            }
        }
        
        System.out.println("Saldo final A: " + contaA.obtemSaldo());
        System.out.println("Saldo final B: " + contaB.obtemSaldo());
        System.out.println("Saldo final C: " + contaC.obtemSaldo());
       
    }
    
}
