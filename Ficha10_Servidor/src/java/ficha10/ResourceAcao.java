/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author Ines Aguas
 */
@Path("")
public class ResourceAcao {
    
    ArrayList<Acao> acoes = new ArrayList<>();
    
    @GET
    @Produces({"application/json", "application/xml"})
    @Path("acoes")
    public Response obterAcoes() {
        return Response.ok(acoes).build();
    }
    
    @GET
    @Produces({"application/json", "application/xml"})
    @Path("acao/{sigla}")
    public Response obterAcao(@PathParam("sigla") String sigla) {
        for(Acao a : acoes) {
            if(a.getSigla().equalsIgnoreCase(sigla)){
                return Response.ok(a).build();
            }
        }
        
        return Response.status(404, "Essa acao nao existe.").build();
    }
    
    @GET
    @Produces({"application/json", "application/xml"})
    @Path("maiorvariacao")
    public Response obterAcaoMaiorVariacao() {
        if(acoes.isEmpty()) {
            return Response.status(204, "Ainda não existem ações registadas").build();
        }
        Acao acao = acoes.get(0);
        for(Acao a : acoes) {
            acao = a.getVariacao() > acao.getVariacao() ? a : acao;
        }
        return Response.ok(acao).build();
    }
    
    @POST
    @Consumes({"application/json", "application/xml"})
    @Path("novaacao")
    public synchronized Response novaAcao(Acao acao) {
        if(acao.getSigla() == null || acao.getNome() == null || acao.getSigla().isBlank() || acao.getNome().isBlank() ) {
             return Response.status(400, "Tem de introduzir um nome e uma sigla.").build();
        }
        
        for(Acao a : acoes) {
            if(a.getNome().equalsIgnoreCase(acao.getNome())) {
                return Response.status(400, "Ja existe uma acao com esse nome.").build();
            } else if(a.getSigla().equalsIgnoreCase(acao.getSigla())) {
                return Response.status(400, "Ja existe uma acao com essa sigla.").build();
            }
        }
        
        acoes.add(acao);
        return Response.ok("Acao inserida com sucesso").build();
    }
    
    @PUT
    @Consumes({"application/json", "application/xml"})
    @Path("acao/{sigla}")
    public synchronized Response atualizarAcao(@PathParam("sigla") String sigla, Acao acao) {
        for(Acao a : acoes) {
            if(a.getSigla().equalsIgnoreCase(sigla)){
                a.setValor_atual(acao.getValor_atual());
                return Response.ok("Valor alterado com sucesso.").build();
            }
        }
        return Response.status(404, "Não existe ação com essa sigla.").build();
    }
    
    @DELETE
    @Path("acao/{sigla}")
    public synchronized Response removerAcao(@PathParam("sigla") String sigla) {
       for(Acao a : acoes) {
            if(a.getSigla().equalsIgnoreCase(sigla)){
                acoes.remove(a);
                return Response.ok("Acao removida com sucesso.").build();
            }
        }
        return Response.status(404, "Não existe ação com essa sigla.").build();
    }
}
