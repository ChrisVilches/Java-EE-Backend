package service;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import facade.ReporteFacade;
import facade.UsuarioFacade;
import model.Reporte;
import model.Usuario;
import util.MensajeRespuesta;

@Path("/reportes")
public class ReporteService {
	
	@EJB 
	ReporteFacade reporteEJB;
	
	@EJB
	UsuarioFacade usuarioEJB;

	
	Logger logger = Logger.getLogger(ReporteService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Reporte> findAll(@Context UriInfo ui) {
		return reporteEJB.findAll(ui.getQueryParameters());
	}
	
	
	@GET
	@Path("{reporte_id}")
	@Produces({"application/xml", "application/json"})
	public Response find(@PathParam("reporte_id") Integer reporte_id){				
		Reporte r = reporteEJB.find(reporte_id);
		if(r == null){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Reporte id="+reporte_id+" no encontrado.")).build();		
		}		
		return Response.status(Status.OK).entity(r).build();		
	}
	
	@POST
	@Consumes({ "application/xml", "application/json" })
	public Response crearReporte(Reporte nuevoReporte){		
		reporteEJB.create(nuevoReporte);				
		return Response.status(Status.OK).entity(nuevoReporte).build();
	}
	
	
	
	@PUT
	@Path("{reporte_id}/revisar/{administrador_id}")
	@Produces({"application/xml", "application/json"})
	public Response revisar(@PathParam("reporte_id") Integer reporte_id, @PathParam("administrador_id") Integer administrador_id){
		
		Reporte r = reporteEJB.find(reporte_id);
		Usuario admin;
		
		if(r == null){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("El reporte no existe.")).build();
		}
		
		if(r.getAdministrador() != null){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("El reporte ya fue revisado por un administrador.")).build();
		}
		
		admin = usuarioEJB.find(administrador_id);
		
		if(admin == null){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("El usuario administrador no existe.")).build();
		}
		
		if(!admin.getEsAdministrador()){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("El usuario no tiene permisos de administrador.")).build();
		}
		
		r.setAdministrador(admin);
		reporteEJB.edit(r);		
		
		return Response.status(Status.OK).build();		
		
	}
	


}
 