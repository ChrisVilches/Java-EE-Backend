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
	
	
	@Override
	public List<Actividad> actividadesOrganizadasPor(int idUsuario){
		
		String hql = "SELECT a FROM Actividad a WHERE a.organizador.usuarioId = :id";
		
		Query q = em.createQuery(hql);
		
		q.setParameter("id", idUsuario);
		
		return (List<Actividad>) q.getResultList();
	}
	
	
	@Override
	protected void obtenerParametrosURL(CriteriaQuery<Actividad> q, CriteriaBuilder cb, Root<Actividad> t, MultivaluedMap<String, String> queryParams) {
		
		if(queryParams == null) return;
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
		
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

		

}
