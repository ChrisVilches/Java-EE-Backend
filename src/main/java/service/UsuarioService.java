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

import facade.UsuarioFacade;
import model.Usuario;
import util.StatusCodeExtra;

@Path("/usuarios")
public class UsuarioService {
	
	@EJB 
	UsuarioFacade usuarioEJB;

	
	Logger logger = Logger.getLogger(UsuarioService.class.getName());
	/*
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Usuario> findAll(){
		return usuarioEJB.findAll();
	}*/
	
	
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Usuario> findAll(@Context UriInfo ui){
		
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		if(queryParams.containsKey("ultima_id") && queryParams.containsKey("mostrar")){
			try{
				
				int id = Integer.parseInt(queryParams.get("ultima_id").get(0));
				int cuantas = Integer.parseInt(queryParams.get("mostrar").get(0));				
				
				return usuarioEJB.obtenerPagina(id, cuantas);
				
			} catch(NumberFormatException e){
				
			}			
		}
		
		
		return usuarioEJB.findAll();
	}
	
	
	/*
	@GET
	@Path("hola")
	@Produces({"application/xml", "application/json"})
	public String obtenerPagina(@Context UriInfo ui ,@PathParam("ultima_id") Integer ultima_id, @PathParam("mostrar") Integer tamano_pagina){
		
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		
		return "Cantidad: " + queryParams.size();
		
		//return usuarioEJB.obtenerPagina(ultima_id, tamano_pagina);
	}*/
	
	
	
	
	
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
		if(username.matches("[a-z.]+@usach.cl")){
			username = username.split("@")[0];
		}
		
		Boolean respuesta = usuarioEJB.loginCorrecto(username, password);
		
		if(respuesta){
			return Response.status(Status.OK).build();
		}
		
		return Response.status(Status.BAD_REQUEST).entity("Nombre de usuario y/o contraseña incorrectos.").build();		
	}
	
	
	
	@POST
	@Produces({"application/xml", "application/json"})
	@Consumes({ "application/xml", "application/json" })
	public Response registrarUsuario(Usuario nuevoUsuario){
		
		if(nuevoUsuario.getPassword().length() < 6) {
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("La contraseña es demasiado corta.").build();
		}
		
		if(nuevoUsuario.getFechaNacimiento() == null){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("Debe indicar fecha de nacimiento.").build();
		}
		
		if(nuevoUsuario.getSexo() == null){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("Debe indicar su sexo (masculino o femenino).").build();
		}

		// Normalizar strings (mayusculas, etc)		
		nuevoUsuario.normalizarStrings();
		
		
		// Si el correo tiene formato incorrecto
		if(!nuevoUsuario.correoFormatoCorrecto()){
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("El correo debe tener solo letras y puntos. No incluir @usach.cl.").build();			
		}
		
		// Si ya existe un usuario con el mismo correo
		if(usuarioEJB.usuarioExiste(nuevoUsuario)){
			return Response.status(Status.BAD_REQUEST).entity("Ya existe un usuario con el correo "+nuevoUsuario.getCorreo()+"@usach.cl.").build();			
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
			return Response.status(StatusCodeExtra.UNPROCESSABLE_ENTITY).entity("El correo debe tener solo letras y puntos. No incluir @usach.cl.").build();			
		}
		
		usuarioEJB.edit(usuario);		
		return Response.status(Status.OK).build();
	}
	
	
	
	@DELETE
	@Path("{usuario_id}")
	@Produces({"application/xml", "application/json"})
	public Response eliminarUsuario(@PathParam("usuario_id") Integer usuario_id){		
		
		Usuario usuarioABorrar = usuarioEJB.find(usuario_id);
		
		if(usuarioABorrar == null){
			return Response.status(Status.BAD_REQUEST).entity("El usuario que se intenta eliminar no existe.").build();
		}
		
		usuarioEJB.remove(usuarioABorrar);		
		return Response.status(Status.OK).build();
	}
	


}
 