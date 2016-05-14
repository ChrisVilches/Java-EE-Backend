package facade;

import java.util.List;

import javax.ejb.Local;

import model.Actividad;

@Local
public interface ActividadFacade {
	

	public void create(Actividad entity);

	public void edit(Actividad entity);

	public void remove(Actividad entity);

	public Actividad find(Object id);

	public List<Actividad> findAll();

	public List<Actividad> findRange(int[] range);
	
	public List<Actividad> obtenerPagina(int ultimaId, int tamanoPagina);

	public int count();

}