package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ActividadFacade;
import model.Actividad;

@Stateless
public class ActividadEJB extends AbstractFacade<Actividad> implements ActividadFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public ActividadEJB() {
		super(Actividad.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Actividad> actividadesSegunTipos(List<Integer> tiposIds){
		
		String hql = "SELECT a FROM Actividad a WHERE a.tipo.tipoId in :tipos ORDER BY a.actividadId DESC";		
	
		return em.createQuery(hql)
				.setParameter("tipos", tiposIds)
				.getResultList();	
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public List<Actividad> obtenerPagina(int ultimaId, int tamanoPagina) {
		return obtenerPagina(ultimaId, tamanoPagina, "actividadId");
	}
		

}
