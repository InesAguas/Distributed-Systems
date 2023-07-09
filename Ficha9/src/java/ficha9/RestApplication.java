/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficha9;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ines Aguas
 */
@ApplicationPath("oliveira")
public class RestApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    
    public RestApplication() {
        singletons.add(new ResourceOliveira());
    }
    
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

