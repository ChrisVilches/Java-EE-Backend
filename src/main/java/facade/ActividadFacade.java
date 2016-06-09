package facade;

import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.core.MultivaluedMap;

import model.Actividad;

@Local
public interface ActividadFacade {
	

	public void create(Actividad entity);

	public void edit(Actividad entity);

	public void remove(Actividad entity);

	public Actividad find(Object id);

	public List<Actividad> findAll();
	
	public List<Actividad> findAll(MultivaluedMap<String,String> queryParams);

	public Actividad encontrarMasReciente();
	
	public List<Actividad> actividadesOrganizadasPor(int idUsuario);	

	public List<Actividad> findRange(int[] range);

	public int count();

}