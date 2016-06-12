package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import facade.AbstractFacade;
import facade.ActividadFacade;
import model.Actividad;
import model.Tipo;


@Stateless
public class ActividadEJB extends AbstractFacade<Actividad> implements ActividadFacade {
	
	
	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;
	
	public ActividadEJB() {
		super(Actividad.class, "actividadId");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Actividad> actividadesOrganizadasPor(int idUsuario){
		
		String hql = "SELECT a FROM Actividad a WHERE a.organizador.usuarioId = :id";
		
		Query q = em.createQuery(hql);
		
		q.setParameter("id", idUsuario);
		
		return (List<Actividad>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Actividad> actividadesCategoriaNombre(String nombreCategoria, MultivaluedMap<String, String> queryParams){
		
		String hql = "SELECT a FROM Actividad a INNER JOIN a.tipo t INNER JOIN t.categoria c WHERE c.nombreCategoria = :nombreCategoria ORDER BY a DESC";
		
		Query q = em.createQuery(hql);		
		
		q.setParameter("nombreCategoria", nombreCategoria);	
		
		
		/**
		 * Paginacion
		 */
		
		if(queryParams.containsKey("limit_a") && queryParams.containsKey("limit_b")){				
			
			int a = Integer.parseInt(queryParams.getFirst("limit_a"));
			int b = Integer.parseInt(queryParams.getFirst("limit_b"));

			q.setFirstResult(a);
			q.setMaxResults(b);						
				
		}
		
		List<Actividad> resultado = q.getResultList();
		
		if(queryParams.containsKey("nofinalizadas")) {			
			Actividad.noFinalizadas(resultado);			
		}
		
		
		return resultado;
	}
	
	
		
	/**
	 * Este filtro es porque en Java EE es dificil hacer operaciones con fechas
	 * asi que si el parametro "nofinalizadas" esta puesto, filtra removiendo las que ya terminaron
	 */
	@Override
	protected void filter(List<Actividad> resultado, MultivaluedMap<String,String> queryParams) {			
		
		/**
		 * 
		 * Solo agregar actividades no finalizadas
		 * 
		 */
		
		if(queryParams.containsKey("nofinalizadas")) {
				
			Actividad.noFinalizadas(resultado);	
			
		} else if(queryParams.containsKey("usuario_no_participa") && queryParams.containsKey("minutos")){			
			
			/**
			 * 
			 * Solo agregar actividades en las que el usuario no participa, y ademas que esten dentro de un rango de tiempo
			 * 
			 */
			long minutos = Long.parseLong(queryParams.getFirst("minutos"));
			int usuarioId = Integer.parseInt(queryParams.getFirst("usuario_no_participa"));
			Actividad.noParticipaDentroDe(resultado, minutos, usuarioId);					
		}
	}
	
	
	
	
	@Override
	protected void obtenerParametrosURL(CriteriaQuery<Actividad> q, CriteriaBuilder cb, Root<Actividad> t, MultivaluedMap<String, String> queryParams) {
		
		if(queryParams == null) return;
		
		/**
		 * 
		 * Filtrar por tipos
		 */
		
		if(queryParams.containsKey("tipos")){
			
				// Agregar una condicion para mostrar actividades filtradas por tipos (lista de tipos)
			
				String[] tipos = queryParams.get("tipos").get(0).split("-");
				ArrayList<Integer> tiposIds = new ArrayList<Integer>();
				
				for(int i=0; i<tipos.length; i++){ // Crear el arreglo de IDs
					tiposIds.add(Integer.parseInt(tipos[i]));					
				}
				
				agregarRestriccion(q, cb, t, t.<Tipo> get("tipo").<Integer> get("tipoId").in(tiposIds));				
				q.orderBy(cb.desc(t.<Integer> get("actividadId")));					
		}
		
		/**
		 * 
		 *  Filtrar por es_activo
		 */
		
		if(queryParams.containsKey("activas")){			
			agregarRestriccion(q, cb, t, cb.isTrue(t.<Boolean> get("esActivo")));							
		}
				
		/**
		 * Filtrar geograficamente (solo mostrar las que estan dentro del cuadrado)
		 * 
		 */
		
		
		
		if(queryParams.containsKey("latitud") && queryParams.containsKey("longitud") && queryParams.containsKey("ladocuadrado")){			
	
			float ladoCuadrado = Float.parseFloat(queryParams.getFirst("ladocuadrado"));
			
			ladoCuadrado /= 2;

			float latitud = Float.parseFloat(queryParams.getFirst("latitud"));
			float longitud = Float.parseFloat(queryParams.getFirst("longitud"));
						
			float minX = latitud - ladoCuadrado;
			float maxX = latitud + ladoCuadrado;
			
			float minY = longitud - ladoCuadrado;
			float maxY = longitud + ladoCuadrado;
			
			agregarRestriccion(q, cb, t, 
					cb.and(
							cb.greaterThanOrEqualTo(t.<Float>get("ubicacionActividadX"), minX), 
							cb.lessThanOrEqualTo(t.<Float>get("ubicacionActividadX"), maxX), 
							
							cb.greaterThanOrEqualTo(t.<Float>get("ubicacionActividadY"), minY), 
							cb.lessThanOrEqualTo(t.<Float>get("ubicacionActividadY"), maxY)
							)
					);				
			
			q.orderBy(cb.desc(t.<Integer> get("actividadId")));			
			
		}				
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

		

}
