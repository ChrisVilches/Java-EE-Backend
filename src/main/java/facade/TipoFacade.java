package facade;

import java.util.List;

import javax.ejb.Local;

import model.Tipo;

@Local
public interface TipoFacade {
	

	public void create(Tipo entity);

	public void edit(Tipo entity);

	public void remove(Tipo entity);

	public Tipo find(Object id);

	public List<Tipo> findAll();

	public List<Tipo> findRange(int[] range);

	public int count();

}