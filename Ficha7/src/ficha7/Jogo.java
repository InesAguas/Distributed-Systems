/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha7;

/**
 *
 * @author Ines Aguas
 */
public class Jogo {

    private int id;
    private String equipa1, equipa2;
    private String datahora_inicio;
    private int estado;
    private int score1, score2;
    private String tempo;

    public Jogo(String equipa1, String equipa2, String datahora_inicio) {
        this.equipa1 = equipa1;
        this.equipa2 = equipa2;
        this.datahora_inicio = datahora_inicio;
    }
    
    public int getId() {
        return id;
    }
    
    public int getEstado() {
        return estado;
    }
}
