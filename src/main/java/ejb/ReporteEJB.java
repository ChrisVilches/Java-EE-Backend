package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ReporteFacade;
import model.Reporte;

@Stateless
public class ReporteEJB extends AbstractFacade<Reporte> implements ReporteFacade {

	@PersistenceContext(unitName = "recreuPU")
	private EntityManager em;

	public ReporteEJB() {
		super(Reporte.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Reporte> noRevisados() {

		String hql = "SELECT r FROM Reporte r WHERE r.administrador.usuarioId = NULL ORDER BY r.reporteId DESC";

		return em.createQuery(hql).getResultList();
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
