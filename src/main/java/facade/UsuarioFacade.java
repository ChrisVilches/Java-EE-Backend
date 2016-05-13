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

	public List<Usuario> findAll();

	public List<Usuario> findRange(int[] range);

	public int count();

}