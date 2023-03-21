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
public class Prog1 extends Thread{
    Conta conta;
    String nome;
    
    public Prog1(Conta conta, String nome) {
        this.conta = conta;
        this.nome = nome;
    }
    
    @Override
    public void run() {
        System.out.println(nome + " - Saldo atual: " + conta.obtemSaldo());
        int i = 0;
        while(i < 200) {
            conta.deposita(10);
            i+=10;
            System.out.println(nome + " - Depositou 10");
        }
        
        int j = 0;
        while(j < 100) {
            if(conta.retira(5)) {
                System.out.println(nome + " - Retirou 5");
            } else {
                System.out.println(nome + " - Saldo insuficiente");
            }
            j+=5;
        }
        
        System.out.println(nome + " - Saldo atual: " + conta.obtemSaldo());
    }
    
    public static void main(String[] args) {
        Conta conta1 = new Conta(0);
        int num = 0;
        do {
            System.out.println("Quantas threads deseja? ");
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            if(num <= 0) {
                System.out.println("O numero tem de ser maior que 0");
            }
        }while(num <= 0);
        
        Prog1 threads[] = new Prog1[num];
        for(int i = 0; i < num; i++) {
            threads[i] = new Prog1(conta1, "thread"+i);
            threads[i].start();
            
        }
        
        for(int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
               System.out.println("Erro");
            }
            
        }
        System.out.println("Saldo final: " + conta1.obtemSaldo());
       
    }
}
