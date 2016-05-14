package facade;

import java.util.List;

import javax.ejb.Local;

import model.Usuario;

@Local
public interface UsuarioFacade {
	

	public void create(Usuario entity);

	public void edit(Usuario entity);

	public void remove(Usuario entity);

	public Usuario find(Object id);
	
	public boolean usuarioExiste(Usuario entity);
	
	
	/**
	 * Verifica si el login esta correcto
	 *
	 * @param  String con el nombre de usuario, es decir abc@usach.cl, en este caso seria pasar el "abc", el cual es case-insensitive y debe venir trimeado()
	 * @param  String con la password (case-sensitive)
	 * @return	Booleano si el login fue correcto o no
	 */
	public boolean loginCorrecto(String nombreUsuario, String password);
	
	
	/**
	 * Entrega una pagina de usuarios.
	 * @param Numero con la ultima ID, para que asi la pagina contenga los tamanoPagina usuarios que vienen despues de esa ultima ID. Si el numero es 0, entonces entrega la primera pagina.
	 * @param Numero con el tamano de la pagina
	 * @return Lista de usuarios
	 */
	public List<Usuario> obtenerPagina(int ultimaId, int tamanoPagina);

	public List<Usuario> findAll();

	public List<Usuario> findRange(int[] range);

	public int count();

}