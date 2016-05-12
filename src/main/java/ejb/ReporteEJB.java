package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ReporteFacade;
import model.Reporte;

@Stateless
public class ReporteEJB extends AbstractFacade<Reporte> implements ReporteFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public ReporteEJB() {
		super(Reporte.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
