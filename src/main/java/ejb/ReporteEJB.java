package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import facade.AbstractFacade;
import facade.ReporteFacade;
import model.Reporte;
import model.Usuario;

@Stateless
public class ReporteEJB extends AbstractFacade<Reporte> implements ReporteFacade {

	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;

	public ReporteEJB() {
		super(Reporte.class, "reporteId");
	}

	@Override
	protected void obtenerParametrosURL(CriteriaQuery<Reporte> q, CriteriaBuilder cb, Root<Reporte> t, MultivaluedMap<String, String> queryParams) {
		if(queryParams == null) return;
		if(queryParams.containsKey("no_revisados")){
			
			// Buscar solo las que no tienen ID de administrador (= que no han sido revisadas)
			agregarRestriccion(q, cb, t, cb.isNull(t.<Usuario>get("administrador")));

		}		
	}


	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	

}
