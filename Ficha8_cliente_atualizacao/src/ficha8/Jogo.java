/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha8;

import java.io.Serializable;

/**
 *
 * @author Ines Aguas
 */
public class Jogo implements Serializable{

    private int id;
    private String equipa1, equipa2;
    private String datahora_inicio;
    private int estado;
    private int score1, score2;
    private String tempo;
    private String[] estados = {"não-iniciado", "primeira parte", "intervalo", "segunda parte", "terminado"};
    private long inicio, fim, tempoTotal;

    public Jogo(String equipa1, String equipa2, String datahora_inicio) {
        this.equipa1 = equipa1;
        this.equipa2 = equipa2;
        this.datahora_inicio = datahora_inicio;
        this.estado = 1;
        this.tempo = "00:00";
        this.tempoTotal = 0;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
        if(estado == 2 || estado == 4) {
            inicio = System.currentTimeMillis();
            fim = 0;
        } else if(estado == 3 || estado == 5) {
            fim = System.currentTimeMillis();
            calculaTempo();
        }
            
    }
    
    public int getId() {
        return id;
    }
    
    public int getEstado() {
        return estado;
    }
    
    public String getEquipa1() {
        return equipa1;
    }
    
    public String getEquipa2() {
        return equipa2;
    }
    
    public String getData() {
        return datahora_inicio;
    }
    
    public int getScore1() {
        return score1;
    }
    
    public int getScore2() {
        return score2;
    }
    
    public void setScores(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
    }
    
     public void calculaTempo() { 
        if(estado == 2 || estado == 4) {
            fim = System.currentTimeMillis();
        }
            long t = fim - inicio;
            tempoTotal += t;
            inicio = System.currentTimeMillis();
            int m = (int) (tempoTotal / 1000)  / 60;
            int s = (int)((tempoTotal / 1000) % 60);
            tempo = String.format("%02d", m) + ":" + String.format("%02d", s);
    }
    
    public String getTempo() {
         return tempo;
     }
     
    @Override
    public String toString() { 
        return "Jogo com ID: " + id + "\nEquipas: " + equipa1 + " - " + equipa2 
                + "\nData de inicio: " + datahora_inicio + "\nEstado: " + estados[estado-1]
                + "\nScore: " + score1 + " - " + score2 + "\nTempo: " + tempo;
    }
}
