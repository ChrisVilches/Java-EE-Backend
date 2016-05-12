package facade;

import java.util.List;

import javax.ejb.Local;

import model.Calificacion;

@Local
public interface CalificacionFacade {
	

	public void create(Calificacion entity);

	public void edit(Calificacion entity);

	public void remove(Calificacion entity);

	public Calificacion find(Object id);

	public List<Calificacion> findAll();

	public List<Calificacion> findRange(int[] range);

	public int count();

}