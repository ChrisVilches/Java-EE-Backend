package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.BanFacade;
import model.Ban;

@Stateless
public class BanEJB extends AbstractFacade<Ban> implements BanFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public BanEJB() {
		super(Ban.class, "banId");
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
