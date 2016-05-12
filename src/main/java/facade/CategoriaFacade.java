package facade;

import java.util.List;

import javax.ejb.Local;

import model.Categoria;

@Local
public interface CategoriaFacade {
	

	public void create(Categoria entity);

	public void edit(Categoria entity);

	public void remove(Categoria entity);

	public Categoria find(Object id);

	public List<Categoria> findAll();

	public List<Categoria> findRange(int[] range);

	public int count();

}