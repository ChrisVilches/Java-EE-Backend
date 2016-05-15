package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import facade.CarreraFacade;
import model.Carrera;

@Path("/carreras")
public class CarreraService {
	
	@EJB 
	CarreraFacade CarreraEJB;

	
	Logger logger = Logger.getLogger(CarreraService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Carrera> findAll(){
		return CarreraEJB.findAll();
	}
	


}
 