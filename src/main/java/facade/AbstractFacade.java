package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


/*
 * Implementa CRUD basico de un recurso T
 * 
 * 
 */

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	/*
	 * Entrega la pagina de tamano "tamanoPagina" que viene despues de la "ultimaId"
	 * Si la ultima ID es 0, entonces entrega la primera pagina.
	 */
	protected List<T> obtenerPagina(int ultimaId, int tamanoPagina, String idAttributeName){

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);		
		Root<T> t = query.from(entityClass);
		TypedQuery<T> tq;
					
		if(tamanoPagina < 1) tamanoPagina = 1;		
		
		if(ultimaId > 0){			
			query.where(cb.lessThan(t.<Integer> get(idAttributeName), ultimaId));			
		}		
		
		query.orderBy(cb.desc(t.<Integer> get(idAttributeName)));	
		
		tq = getEntityManager().createQuery(query);
		
		tq.setMaxResults(tamanoPagina);
		return tq.getResultList();
		
	}
	
	

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		CriteriaQuery<T> cq = (CriteriaQuery<T>)getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findRange(int[] range) {
		CriteriaQuery<T> cq = (CriteriaQuery<T>)getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public int count() {
		CriteriaQuery<T> cq = (CriteriaQuery<T>)getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select((Selection<T>)getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
