package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import facade.ActividadFacade;
import model.Actividad;
import model.Usuario;
import util.StatusCodeExtra;


@Path("/actividades")
public class ActividadService {
	
	@EJB 
	ActividadFacade actividadEJB;

	
	Logger logger = Logger.getLogger(ActividadService.class.getName());
	
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Actividad> lista(@Context UriInfo ui){
		
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		
		/*
		 * Peticion por lista de tipos ?tipos=1-2-3-4
		 */
		
		
		if(queryParams.containsKey("tipos")){
			try{
				
				String[] tipos = queryParams.get("tipos").get(0).split("-");
				ArrayList<Integer> tiposIds = new ArrayList<Integer>();
				
				for(int i=0; i<tipos.length; i++){ // Crear el arreglo de IDs
					tiposIds.add(Integer.parseInt(tipos[i]));					
				}
				
				return actividadEJB.actividadesSegunTipos(tiposIds);
				
			} catch(NumberFormatException e){
				return null;
			}			
		}		
		
		/*
		 * Peticion paginada
		 */
		
		if(queryParams.containsKey("ultima_id") && queryParams.containsKey("mostrar")){
			try{
				
				int id = Integer.parseInt(queryParams.get("ultima_id").get(0));
				int cuantas = Integer.parseInt(queryParams.get("mostrar").get(0));				
				
				return actividadEJB.obtenerPagina(id, cuantas);
				
			} catch(NumberFormatException e){
				return null;
			}			
		}	
		
		/*
		 * Normal (lista completa)
		 */
		
		return actividadEJB.findAll();
	}
	

	
	@GET
	@Path("{actividad_id}/usuarios")
	@Produces({"application/xml", "application/json"})
	public List<Usuario> obtenerParticipantes(@PathParam("actividad_id") Integer actividad_id){						
		Actividad a = actividadEJB.find(actividad_id);
		return a.getParticipantes();		
	}
	
	
	
	@GET
	@Path("{actividad_id: [0-9]+}")
	@Produces({"application/xml", "application/json"})
	public Response find(@PathParam("actividad_id") Integer actividad_id){				
		Actividad a = actividadEJB.find(actividad_id);
		if(a == null){
			return Response.status(Status.BAD_REQUEST).entity("Actividad id="+actividad_id+" no encontrada.").build();		
		}		
		return Response.status(Status.OK).entity(a).build();		
	}
	
	

	
	@POST
	@Consumes({ "application/xml", "application/json" })
	public Response crearActividad(Actividad nuevaActividad){		
		actividadEJB.create(nuevaActividad);		
		return Response.status(Status.OK).build();
	}
	
	
	
	@PUT
	@Produces({"application/xml", "application/json"})
	@Consumes({ "application/xml", "application/json" })
	public Response editActividad(Actividad a){		
		
		if(a.getActividadId() == null) 
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).build();	
						
		actividadEJB.edit(a);
		return Response.status(Status.OK).build();		
	}
	
	
	
	
	@GET
	@Path("count")
	@Produces("text/html")
	public String count(){		
		return "Cantidad de actividades: "+actividadEJB.count();
	}


}
 