package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ParticipacionFacade;
import model.Participacion;

@Stateless
public class ParticipacionEJB extends AbstractFacade<Participacion> implements ParticipacionFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public ParticipacionEJB() {
		super(Participacion.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
