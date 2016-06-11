package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import facade.TipoFacade;
import model.Tipo;

@Path("/tipos")
public class TipoService {
	
	@EJB 
	TipoFacade tipoEJB;

	Logger logger = Logger.getLogger(TipoService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Tipo> findAll() {
		return tipoEJB.findAll();
	}
	


}
 