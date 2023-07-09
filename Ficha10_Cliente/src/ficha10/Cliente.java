/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha10;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ines Aguas
 */
public class Cliente {

    static String baseUrl = "http://localhost:8080/Ficha10_Servidor/";
    static Client cliente;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        cliente = ClientBuilder.newClient();

        int opcao;
        System.out.println("Bem-vindo\n");

        do {
            System.out.println("----- MENU -----");
            System.out.println("1 - Obter dados de todas as ações");
            System.out.println("2 - Obter dados de uma ação");
            System.out.println("3 - Obter dados da ação com maior variação positiva");
            System.out.println("4 - Adicionar uma ação");
            System.out.println("5 - Atualizar valor atual de uma ação");
            System.out.println("6 - Remover uma ação");
            System.out.println("0 - Sair");

            System.out.print("Opção [0/1/2/3/4/5/6] : ");
            opcao = in.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Adeus");
                    break;
                case 1:
                    obterAcoes();
                    break;
                case 2:
                    obterAcao();
                    break;
                case 3:
                    obterAcaoVariacao();
                    break;
                case 4:
                    novaAcao();
                    break;
                case 5:
                    alterarAcao();
                    break;
                case 6:
                    removerAcao();
                    break;
            }
        } while (opcao != 0);
        
        cliente.close();
    }

    public static void obterAcoes() {
        List<Acao> acoes = cliente.target(baseUrl)
                .path("acoes")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Acao>>() {
                });
        if (acoes.isEmpty()) {
            System.out.println("Ainda não existem ações registadas");
        } else {
            System.out.println("Sigla\t\tNome\t\t\tValor Atual\t\tVariação");
            for (Acao a : acoes) {
                System.out.println(a.getSigla() + "\t\t" + a.getNome() + "\t\t\t" + String.format("%.2f", a.getValor_atual()) + "\t\t\t" + String.format("%.2f", a.getVariacao()) + "%");
            }
        }
    }
    
    public static void obterAcao() {
        Scanner in = new Scanner(System.in);
        System.out.print("Sigla da ação: ");
        String sigla = in.nextLine();
        
        Response response = cliente.target(baseUrl)
                            .path("acao/" + sigla)
                            .request()
                            .accept(MediaType.APPLICATION_JSON)
                            .get();
        
        if(response.getStatus() == 200) {
            Acao acao = response.readEntity(Acao.class);
            System.out.println(acao);
        } else {
            System.out.println("Não existe ação com essa sigla");
        }
        
        response.close();
    }
    
    public static void obterAcaoVariacao() {
        Response response = cliente.target(baseUrl)
                                    .path("maiorvariacao")
                                    .request()
                                    .accept(MediaType.APPLICATION_JSON)
                                    .get();
        
        if(response.getStatus() == 200) {
             Acao acao = response.readEntity(Acao.class);
             System.out.println(acao);
        } else {
            System.out.println("Ainda não existem ações registadas");
        }
    }
    
    public static void novaAcao() {
        Scanner in = new Scanner(System.in);
        System.out.print("Sigla: ");
        String sigla = in.nextLine();
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Valor de abertura: ");
        int valor;
        while(true){
            try {
                valor = in.nextInt();
                break;
            }catch(InputMismatchException ex) {
                System.out.println("Tem de ser um numero");
                in.nextLine();
                System.out.print("Valor de abertura: ");
            }
            
        }
        
        Acao acao = new Acao(sigla, nome, valor);
        
        Response response = cliente.target(baseUrl)
                                    .path("novaacao")
                                    .request()
                                    .post(Entity.json(acao));
        
        if(response.getStatus() == 200) {
             System.out.println("Ação inserida com sucesso");
        } else {
            System.out.println("Ja existe uma ação com essa sigla ou nome.");
        }
        
        response.close();
        
    }
    
    public static void alterarAcao() {
        Scanner in = new Scanner(System.in);
        System.out.print("Sigla da ação: ");
        String sigla = in.nextLine();
        
        System.out.print("Novo valor: ");
        int valor;
        while(true){
            try {
                valor = in.nextInt();
                break;
            }catch(InputMismatchException ex) {
                System.out.println("Tem de ser um numero");
                in.nextLine();
                System.out.print("Novo valor: ");
            }
        }
        
        Acao acao = new Acao();
        acao.setValor_atual(valor);
        
        Response response = cliente.target(baseUrl)
                            .path("acao/" + sigla)
                            .request()
                            .put(Entity.json(acao));
        
        if(response.getStatus() == 200) {
            System.out.println("Ação alterada com sucesso.");
        } else {
            System.out.println("Não existe ação com essa sigla");
        }
        
        response.close();
    }
    
    public static void removerAcao() {
        Scanner in = new Scanner(System.in);
        System.out.print("Sigla da ação: ");
        String sigla = in.nextLine();
        
        Response response = cliente.target(baseUrl)
                            .path("acao/" + sigla)
                            .request()
                            .delete();
        
        if(response.getStatus() == 200) {
            System.out.println("Ação removida com sucesso.");
        } else {
            System.out.println("Não existe ação com essa sigla");
        }
        
        response.close();
    }
}
