package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/")
@ApplicationPath("/")
public class Bienvenida {


	@GET
	@Path("/")
	@Produces("text/html")
	public String hello() {		

		return "hola";
	}

}