package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import facade.AbstractFacade;
import facade.UsuarioFacade;
import model.Usuario;

@Stateless
public class UsuarioEJB extends AbstractFacade<Usuario> implements UsuarioFacade {

	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;

	public UsuarioEJB() {
		super(Usuario.class, "usuarioId");
	}
	
	public boolean usuarioExiste(Usuario entity) {	
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);		
		Root<Usuario> t = query.from(Usuario.class);
		TypedQuery<Usuario> tq;		
		
		query.where(cb.equal(t.<String>get("correo"), entity.getCorreo()));
		tq = getEntityManager().createQuery(query);
		
		tq.setMaxResults(1);
		
		return tq.getResultList().size() > 0;

	}
	
		
	
	public Usuario login(String nombreUsuario, String password){		
	
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);		
		Root<Usuario> t = query.from(Usuario.class);
		TypedQuery<Usuario> tq;		
		
		query.where(
				cb.and(
							cb.equal(t.<String>get("correo"), nombreUsuario), 
							cb.equal(t.<String>get("password"), password)
						)
				);
		
		tq = getEntityManager().createQuery(query);
		tq.setMaxResults(1);
		
		if(tq.getResultList().size() == 1){
			return tq.getResultList().get(0);
		}		
		
		return null;
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	

}
