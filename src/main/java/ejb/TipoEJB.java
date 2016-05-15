package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.TipoFacade;
import model.Tipo;

@Stateless
public class TipoEJB extends AbstractFacade<Tipo> implements TipoFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public TipoEJB() {
		super(Tipo.class, "tipoId");
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
