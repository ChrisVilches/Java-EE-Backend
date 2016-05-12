package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CarreraFacade;
import model.Carrera;

@Stateless
public class CarreraEJB extends AbstractFacade<Carrera> implements CarreraFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public CarreraEJB() {
		super(Carrera.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
