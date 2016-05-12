package facade;

import java.util.List;

import javax.ejb.Local;

import model.Administrador;

@Local
public interface AdministradorFacade {
	

	public void create(Administrador entity);

	public void edit(Administrador entity);

	public void remove(Administrador entity);

	public Administrador find(Object id);

	public List<Administrador> findAll();

	public List<Administrador> findRange(int[] range);

	public int count();

}