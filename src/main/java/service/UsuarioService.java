package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ejb.UsuarioEJB;
import facade.UsuarioFacade;
import model.Usuario;
import util.StatusCodeExtra;
import util.Util;

@Path("/usuarios")
public class UsuarioService {
	
	@EJB 
	UsuarioFacade usuarioEJB;

	
	Logger logger = Logger.getLogger(UsuarioService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Usuario> findAll(){
		return usuarioEJB.findAll();
	}
	
		
	
	@POST
	@Produces({"application/xml", "application/json"})
	public Response registrarUsuario(Usuario nuevoUsuario){
		
		if(nuevoUsuario.getPassword().length() < 6) {
			return Response.status(Status.FORBIDDEN).entity("La contraseña es demasiado corta.").build();
		}
		
		if(nuevoUsuario.getFechaNacimiento() == null){
			return Response.status(Status.FORBIDDEN).entity("Debe indicar fecha de nacimiento.").build();
		}
		
		if(nuevoUsuario.getSexo() == null){
			return Response.status(Status.FORBIDDEN).entity("Debe indicar su sexo (masculino o femenino).").build();
		}

		// Normalizar strings (mayusculas, etc)		
		nuevoUsuario.setCorreo(nuevoUsuario.getCorreo().toLowerCase().trim());
		nuevoUsuario.setPrimerNombre(Util.normalizarNombre(nuevoUsuario.getPrimerNombre()));
		nuevoUsuario.setSegundoNombre(Util.normalizarNombre(nuevoUsuario.getSegundoNombre()));
		nuevoUsuario.setApellidoMaterno(Util.normalizarNombre(nuevoUsuario.getApellidoMaterno()));
		nuevoUsuario.setApellidoPaterno(Util.normalizarNombre(nuevoUsuario.getApellidoPaterno()));	
		
		
		// Si el correo tiene formato incorrecto
		if(!nuevoUsuario.getCorreo().matches("[a-z.]+")){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("El correo debe tener solo letras y puntos. No incluir @usach.cl.").build();			
		}
		
		// Si ya existe un usuario con el mismo correo
		if(usuarioEJB.usuarioExiste(nuevoUsuario)){
			return Response.status(Status.FORBIDDEN).entity("Ya existe un usuario con el correo "+nuevoUsuario.getCorreo()+"@usach.cl.").build();			
		}
		
		usuarioEJB.create(nuevoUsuario);
		
		return Response.status(Status.OK).build();

	}
	


}
 