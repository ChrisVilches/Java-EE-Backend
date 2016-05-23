package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.ws.rs.core.MultivaluedMap;


/*
 * Implementa CRUD basico de un recurso T
 * 
 * 
 */

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;
	protected String idAttributeName;

	public AbstractFacade(Class<T> entityClass, String idAttributeName) {
		this.entityClass = entityClass;
		this.idAttributeName = idAttributeName;
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
	
	
	/**
	 * Retorna la entidad mas recientemente agregada
	 * @return
	 */
	public T encontrarMasReciente(){
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);		
		Root<T> t = query.from(entityClass);
		TypedQuery<T> tq;		
		
		query.orderBy(cb.desc(t.<Integer>get(idAttributeName)));		
		
		tq = getEntityManager().createQuery(query);
		
		tq.setMaxResults(1);
		
		
		return tq.getResultList().get(0);		
		
	}
	
	
	
	
	public List<T> findAll(MultivaluedMap<String,String> queryParams){
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);		
		Root<T> t = query.from(entityClass);
		TypedQuery<T> tq;		
		
		paginarDesde(query, cb, t, queryParams);
		obtenerParametrosURL(query, cb, t, queryParams);
		
		tq = getEntityManager().createQuery(query);		
		setMaximo(tq, queryParams);
		
		return tq.getResultList();		
	}
	
	/**
	 * Procesa los parametros de la URL (?id=3&name=felo&etc). Todas las entidades pueden sobreescribir este metodo. 
	 * La funcion findAll(uriParams) llama este metodo para pasar opciones extra (aparte de las de paginado).
	 * @param q
	 * @param cb
	 * @param t
	 * @param queryParams
	 */
	protected void obtenerParametrosURL(CriteriaQuery<T> q, CriteriaBuilder cb, Root<T> t, MultivaluedMap<String, String> queryParams){
		
		// Debe ser implementado por cada subclase
	}
	
	
	
	/**
	 * Pagina desde la ultima id, y hace que el orden sea id descendente.
	 * @param q
	 * @param cb
	 * @param t
	 * @param queryParams
	 * @param idAttributeName el nombre del atributo de la id, por ejemplo "usuarioId"
	 */
	private void paginarDesde(CriteriaQuery<T> q, CriteriaBuilder cb, Root<T> t, MultivaluedMap<String, String> queryParams){		
		
		// Agrega la restriccion WHERE id < ultimaId
		
		if(queryParams == null) return;
		if(queryParams.containsKey("ultima_id")){				
			
			q.orderBy(cb.desc(t.<Integer>get(idAttributeName)));
			
			int ultimaId = Integer.parseInt(queryParams.get("ultima_id").get(0));
			
			if(ultimaId > 0){				
				agregarRestriccion(q, cb, t, cb.lessThan(t.<Integer> get(idAttributeName), ultimaId));				
			}

		}						
	}
	
	/**
	 * Agrega una restriccion where a una consulta
	 * @param q
	 * @param cb
	 * @param t
	 * @param nuevaWhereClause
	 */
	protected void agregarRestriccion(CriteriaQuery<T> q, CriteriaBuilder cb, Root<T> t, Predicate nuevaWhereClause){
		Predicate whereClause = q.getRestriction();
		if(whereClause == null){			
			q.where(nuevaWhereClause);			
		} else {			
			q.where(cb.and(whereClause, nuevaWhereClause));			
		}
	}
	
	
	/**
	 * Pone el maximo (LIMIT) a un query
	 * @param tq
	 * @param queryParams
	 */
	protected void setMaximo(TypedQuery<T> tq, MultivaluedMap<String, String> queryParams){		
		if(queryParams == null) return;
		if(queryParams.containsKey("mostrar")){					
			tq.setMaxResults(Integer.parseInt(queryParams.get("mostrar").get(0)));
		}		
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
