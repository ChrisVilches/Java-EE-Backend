package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.AdministradorFacade;
import model.Administrador;

@Stateless
public class AdministradorEJB extends AbstractFacade<Administrador> implements AdministradorFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public AdministradorEJB() {
		super(Administrador.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
