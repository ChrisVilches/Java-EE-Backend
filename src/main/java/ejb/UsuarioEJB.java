package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UsuarioFacade;
import model.Usuario;

@Stateless
public class UsuarioEJB extends AbstractFacade<Usuario> implements UsuarioFacade {

	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;

	public UsuarioEJB() {
		super(Usuario.class);
	}

	public boolean usuarioExiste(Usuario entity) {

		String hql = "SELECT u FROM Usuario u WHERE u.correo = :correo";

		// Ejecutar query, obtener lista de usuarios con el mismo correo (lista de tamano 1)
		// y retornar verdadero si existe ya el usuario
		
		return em.createQuery(hql)
				.setParameter("correo", entity.getCorreo())
				.setMaxResults(1)
				.getResultList()
				.size() > 0;

	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
