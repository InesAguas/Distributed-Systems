/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha5;

import java.io.Serializable;

/**
 *
 * @author Ines Aguas
 */
public class Temp implements Serializable {
    
    private String cidade;
    private float tempAtual, tempMaxima, tempMinima;
    
    public Temp(String cidade, float tempAtual) {
        this.cidade = cidade;
        this.tempAtual = tempAtual;
        this.tempMaxima = tempAtual;
        this.tempMinima = tempAtual;
    }
    
    public synchronized void setTempAtual(float tempAtual) {
        this.tempAtual = tempAtual;
        if(tempAtual < tempMinima) {
            this.tempMinima = tempAtual;
        }
        
        if(tempAtual > tempMaxima) {
            this.tempMaxima = tempAtual;
        }
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public float getTempAtual() {
        return tempAtual;
    }
    
    public float getTempMaxima() {
        return tempMaxima;
    }
    
    public float getTempMinima() {
        return tempMinima;
    }
}
