/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha1;

/**
 *
 * @author inoca
 */
public class Conta {
    int saldo;
    
    public Conta(int saldo) {
        this.saldo = saldo;
    }
    
    //fazer um deposito
    public synchronized void deposita(int valor) {
        saldo += valor;
    }
    
    //retirar dinheiro.. saldo insuficiente => retorna falso
    public synchronized Boolean retira(int valor) {
        if(saldo < valor) {
            return false;
        }
        saldo -= valor;
        return true;
        
    }
    
    //retorna o saldo atual
    public int obtemSaldo() {
        return saldo;
    }
    
    public synchronized Boolean transferir(Conta conta, int valor) {
        if(retira(valor)) {
            conta.deposita(valor);
            return true;
        }
        return false;
        
    }
    
}
