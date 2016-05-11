package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@ApplicationPath("/")
public class HelloWorldService {

    @GET
    @Produces("text/html")
    public String getClichedMessage() {
    	
    	String salida = "";
    	salida += "<!DOCTYPE html><html><head><meta charset=\"UTF-8\">"
    			+ "<title>Taller 1 TBD</title></head><body>"
    			+ "La documentación de la API esta en el <a href=\"https://github.com/alamatita/Taller-1-TBD\">repositorio github</a>."
    			+ "</body></html>";

        return salida;
    }
	
}

