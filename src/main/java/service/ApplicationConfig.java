package service;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import model.Usuario;

@ApplicationPath("/")
public class ApplicationConfig extends Application{
	
	@GET
	@Produces("text/html")
	public String main(){
		return "Hola";
	}
	
}
