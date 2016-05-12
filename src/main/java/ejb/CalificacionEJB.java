package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CalificacionFacade;
import model.Calificacion;

@Stateless
public class CalificacionEJB extends AbstractFacade<Calificacion> implements CalificacionFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public CalificacionEJB() {
		super(Calificacion.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
