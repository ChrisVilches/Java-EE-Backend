package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.CategoriaFacade;
import model.Categoria;

@Stateless
public class CategoriaEJB extends AbstractFacade<Categoria> implements CategoriaFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public CategoriaEJB() {
		super(Categoria.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
