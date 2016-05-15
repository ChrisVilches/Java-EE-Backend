package facade;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.core.MultivaluedMap;

import model.Usuario;

@Local
public interface UsuarioFacade {
	

	public void create(Usuario entity);

	public void edit(Usuario entity);

	public void remove(Usuario entity);

	public Usuario find(Object id);
	
	public boolean usuarioExiste(Usuario entity);
	
	
	/**
	 * Verifica si el login esta correcto, y entrega el usuario
	 *
	 * @param  String con el nombre de usuario, es decir abc@usach.cl, en este caso seria pasar el "abc", el cual es case-insensitive y debe venir trimeado()
	 * @param  String con la password (case-sensitive)
	 * @return	Booleano si el login fue correcto o no
	 */
	public Usuario login(String nombreUsuario, String password);
	
	public List<Usuario> findAll(MultivaluedMap<String,String> queryParams);

	public List<Usuario> findAll();

	public List<Usuario> findRange(int[] range);

	public int count();

}