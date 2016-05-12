package facade;

import java.util.List;

import javax.ejb.Local;

import model.Participacion;

@Local
public interface ParticipacionFacade {
	

	public void create(Participacion entity);

	public void edit(Participacion entity);

	public void remove(Participacion entity);

	public Participacion find(Object id);

	public List<Participacion> findAll();

	public List<Participacion> findRange(int[] range);

	public int count();

}