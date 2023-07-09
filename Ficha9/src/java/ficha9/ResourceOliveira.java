/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha9;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author inoca
 */
@Path("resource")
public class ResourceOliveira {
    int tempAtual = 0;
    int tempMin = 0;
    int tempMax = 0;
    
    int[] horas = new int[24];
    
    @GET
    @Path("/atual")
    @Produces("text/plain")
    public String getTempAtual() {
        return "Temperatura atual: " + tempAtual + "ºC";
    }
       
    @GET
    @Path("/extremos")
    @Produces("text/plain")
    public String getTempMax() {
        return "Temperatura minima: " + tempMin + "ºC\nTemperatura maxima: " + tempMax + "ºC";
    }
    
    @PUT
    @Path("/alterar")
    @Consumes("application/x-www-form-urlencoded")
    public Response alteraTemp(@FormParam("temp") int temp) {
        tempAtual = temp;
        
        if(temp < tempMin) {
            tempMin = temp;
        } else if(temp > tempMax) {
            tempMax = temp;
        }
        
        return Response.ok().build();
    }
    
    
    @POST
    @Path("/reiniciar")
    @Consumes("application/x-www-form-urlencoded")
    public Response iniciaTemp(@FormParam("temp") int temp) {
        tempAtual = temp;
        tempMin = temp;
        tempMax = temp;
        
        return Response.ok().build();
    }
    
    @GET
    @Path("/temperatura/{hora}")
    @Produces("text/plain")
    public Response getTempHora(@PathParam("hora") int hora) {
        if(hora < 0 || hora > 23) {
            return Response.status(400, "A hora tem de ser entre 0 e 23").build();
        }
        return Response.ok("Temperatura as " + hora + " horas: " + horas[hora] + "ºC").build();
    }
    
    @PUT
    @Path("/temperatura/{hora}")
    @Consumes("application/x-www-form-urlencoded")
    public Response setTempHora(@PathParam("hora") int hora, @FormParam("temp") int temp) {
        if(hora < 0 || hora > 23) {
            return Response.status(400, "A hora tem de ser entre 0 e 23").build();
        }
        
        horas[hora] = temp;
        
        return Response.ok().build();
    }
    
}
