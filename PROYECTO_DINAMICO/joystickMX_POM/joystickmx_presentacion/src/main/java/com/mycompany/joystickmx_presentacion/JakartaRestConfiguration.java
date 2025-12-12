package com.mycompany.joystickmx_presentacion;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        /*
            Se añaden las clases Resource a la lista del
            servidor para que se consideren y se puedan invocar.
        */
        final HashSet<Class<?>> classes = new HashSet<>();
        classes.add(VideojuegoResource.class);
        classes.add(ResenasResource.class);
        classes.add(CarritoResource.class);
        return classes;
    }
    
}