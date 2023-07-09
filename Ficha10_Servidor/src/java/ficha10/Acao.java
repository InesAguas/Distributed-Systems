/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha10;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author Ines Aguas
 */
@XmlRootElement(name = "acao")
@XmlType(propOrder = {"sigla", "nome", "valor_abertura", "valor_atual"})
public class Acao {
    
    private String sigla, nome; 
    private float valor_abertura, valor_atual; 
    
    public Acao() {
        
    }
    
    public Acao(String sigla, String nome, float valor_abertura) {
       this.sigla = sigla;
       this.nome = nome;
       this.valor_abertura = valor_abertura;
       this.valor_atual = valor_abertura;
    }
    
    @XmlAttribute
    public String getSigla() {
        return sigla;
    }
    
    public String getNome() {
        return nome;
    }
    
    public float getValor_atual() {
        return valor_atual;
    }
    
    public float getVariacao() {
        return ((valor_atual - valor_abertura) / valor_abertura) * 100;
    }
    
    public float getValor_abertura() {
        return valor_abertura;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public void setValor_abertura(float valor_abertura) {
        this.valor_abertura = valor_abertura;
        this.valor_atual = valor_abertura;
    }
    
    public void setValor_atual(float valor_atual) {
        this.valor_atual = valor_atual;
    }
    
    @Override
    public String toString(){
        return "sigla: " + sigla +
              ", nome: " + nome +
                ", valor atual: " + valor_atual +
                ", variacao: " + String.format("%.2f", getVariacao()) + "%";
    }
}
