package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.CategoriaFacade;
import model.Categoria;
import model.Tipo;


@Path("/categorias")
public class CategoriaService {
	
	@EJB 
	CategoriaFacade categoriaEJB;
	

	
	Logger logger = Logger.getLogger(CategoriaService.class.getName());
	
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Categoria> findAll(){		
		return categoriaEJB.findAll();
	}
	
	
	@GET
	@Path("{categoria_id}/tipos")
	@Produces({"application/xml", "application/json"})
	public List<Tipo> tiposEnCategoria(@PathParam("categoria_id") Integer categoria_id){
		return categoriaEJB.find(categoria_id).getTipos();
	}


}
 