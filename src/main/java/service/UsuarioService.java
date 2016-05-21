package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import facade.ActividadFacade;
import facade.UsuarioFacade;
import model.Actividad;
import model.Usuario;
import util.MensajeRespuesta;
import util.StatusCodeExtra;

@Path("/usuarios")
public class UsuarioService {
	
	@EJB 
	UsuarioFacade usuarioEJB;
	
	@EJB 
	ActividadFacade actividadEJB;
	

	
	Logger logger = Logger.getLogger(UsuarioService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Usuario> findAll(@Context UriInfo ui){		
		
		return usuarioEJB.findAll(ui.getQueryParameters());

	}
	
	
	
	@GET
	@Path("{usuario_id: [0-9]+}")
	@Produces({"application/xml", "application/json"})
	public Response find(@PathParam("usuario_id") Integer usuario_id){				
		Usuario u = usuarioEJB.find(usuario_id);
		if(u == null){			
			
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Usuario id="+usuario_id+" no encontrado.")).build();		
		}		
		return Response.status(Status.OK).entity(u).build();		
	}
	
	
	@GET
	@Path("{usuario_id}/actividades")
	@Produces({"application/xml", "application/json"})
	public List<Actividad> obtenerActividades(@PathParam("usuario_id") Integer usuario_id, @Context UriInfo ui){		

		Usuario u = usuarioEJB.find(usuario_id);
		
		if(ui.getQueryParameters().containsKey("organizador")){
			// Si tiene el parametro "organizador" significa que se busca las actividades que organizo este usuario			
			return u.getActividadesOrganizadas();			
		}	
	
		return u.getActividades();
	}
	
	
	@GET
	@Path("buscar")
	@Produces({"application/xml", "application/json"})
	public Response buscarUsuarioCorreo(@Context UriInfo ui){
		
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		
		
		if(queryParams.containsKey("correo")){
				
				String correo = queryParams.get("correo").get(0);			
				
				// Si tiene @usach.cl, obtener solo la primera parte del correo
				if(Usuario.correoTieneArrobaUsach(correo)){
					correo = correo.split("@")[0];
				}
				
				correo = correo.toLowerCase();
				
				return Response.status(Status.OK).entity(usuarioEJB.buscarUsuarioCorreo(correo)).build();

		}	
		
		return Response.status(Status.OK).build();
		
	}
	

	
	@POST
	@Path("{usuario_id}/actividades/{actividad_id}")
	@Produces({"application/xml", "application/json"})
	public Response agregarParticipacion(@PathParam("usuario_id") Integer usuario_id, @PathParam("actividad_id") Integer actividad_id){
		
		Usuario u = usuarioEJB.find(usuario_id);
		Actividad a = actividadEJB.find(actividad_id);
		
		if(u==null) return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Error al agregar participacion de usuario en actividad. Usuario no existe.")).build();	
		if(a==null) return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Error al agregar participacion de usuario en actividad. Actividad no existe.")).build();	
		
		u.getActividades().add(a);
		a.getParticipantes().add(u);
		usuarioEJB.edit(u);
		actividadEJB.edit(a);
		
		return Response.status(Status.OK).build();		
	}
	
	@DELETE
	@Path("{usuario_id}/actividades/{actividad_id}")
	public Response eliminarParticipacion(@PathParam("usuario_id") Integer usuario_id, @PathParam("actividad_id") Integer actividad_id){
		
		Usuario u = usuarioEJB.find(usuario_id);
		Actividad a = actividadEJB.find(actividad_id);
		
		if(u==null) return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Error al eliminar participacion de usuario en actividad. Usuario no existe.")).build();	
		if(a==null) return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Error al eliminar participacion de usuario en actividad. Actividad no existe.")).build();	
				
		u.getActividades().remove(a);
		a.getParticipantes().remove(u);
		
		usuarioEJB.edit(u);
		actividadEJB.edit(a);
			
		return Response.status(Status.OK).build();	
		
	}
	
	
	@POST
	@Path("/login")
	@Produces({"application/xml", "application/json"})
	@Consumes({ "application/xml", "application/json" })
	public Response login(Usuario login){
		
		String username = login.getCorreo();
		String password = login.getPassword();
		
		// Pasar a minusculas y eliminar espacios extra
		username = username.toLowerCase().trim();
		
		// Si cumple con el patron xxx.xx@usach.cl
		// entonces cortar la string en la arroba
		if(Usuario.correoTieneArrobaUsach(username)){
			username = username.split("@")[0];
		}
		
		Usuario respuesta = usuarioEJB.login(username, password);
		
		if(respuesta != null){
			return Response.status(Status.OK).entity(respuesta).build();
		}
				
		
		return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Nombre de usuario y/o contraseña incorrectos.")).build();		
	}
	
	
	
	@POST
	@Produces({"application/xml", "application/json"})
	@Consumes({ "application/xml", "application/json" })
	public Response registrarUsuario(Usuario nuevoUsuario){
		
		if(nuevoUsuario.getPassword().length() < 6) {
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity(MensajeRespuesta.crear("La contraseña es demasiado corta.")).build();
		}
		
		if(nuevoUsuario.getFechaNacimiento() == null){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity(MensajeRespuesta.crear("Debe indicar fecha de nacimiento.")).build();
		}
		
		if(nuevoUsuario.getSexo() == null){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity(MensajeRespuesta.crear("Debe indicar su sexo (masculino o femenino).")).build();
		}

		// Normalizar strings (mayusculas, etc)		
		nuevoUsuario.normalizarStrings();
		
		
		// Si el correo tiene formato incorrecto
		if(!nuevoUsuario.correoFormatoCorrecto()){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity(MensajeRespuesta.crear("El correo debe tener solo letras y puntos. No incluir @usach.cl.")).build();			
		}
		
		// Si ya existe un usuario con el mismo correo
		if(usuarioEJB.usuarioExiste(nuevoUsuario)){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("Ya existe un usuario con el correo "+nuevoUsuario.getCorreo()+"@usach.cl.")).build();			
		}
		
		usuarioEJB.create(nuevoUsuario);
		
		return Response.status(Status.OK).build();

	}
	
	
	@PUT
	@Produces({"application/xml", "application/json"})
	@Consumes({ "application/xml", "application/json" })
	public Response actualizarUsuario(Usuario usuario){		
		
		// Normalizar strings (mayusculas, etc)		
		usuario.normalizarStrings();
				

		// Si el correo tiene formato incorrecto
		if(!usuario.correoFormatoCorrecto()){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity(MensajeRespuesta.crear("El correo debe tener solo letras y puntos. No incluir @usach.cl.")).build();			
		}
		
		usuarioEJB.edit(usuario);		
		return Response.status(Status.OK).build();
	}
	
	
	
	@DELETE
	@Path("{usuario_id: [0-9]+}")
	@Produces({"application/xml", "application/json"})
	public Response eliminarUsuario(@PathParam("usuario_id") Integer usuario_id){		
		
		Usuario usuarioABorrar = usuarioEJB.find(usuario_id);
		
		if(usuarioABorrar == null){
			return Response.status(Status.FORBIDDEN).entity(MensajeRespuesta.crear("El usuario que se intenta eliminar no existe.")).build();
		}
		
		usuarioEJB.remove(usuarioABorrar);		
		return Response.status(Status.OK).build();
	}
	
	
	
	@GET
	@Path("count")
	@Produces("text/html")
	public String count(){		
		return "Cantidad de usuarios: "+usuarioEJB.count();
	}
	
	
	


}
 