package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="reporte")
public class Reporte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="reporte_id", unique=true, nullable=false)	private int reporteId;	
	
	@Column(name="motivo_reporte", nullable=true, length=2056, updatable=false) 	private String motivoReporte;	
	@ManyToOne @JoinColumn(name = "usuario_reportado_id", nullable=false, updatable=false) private Usuario usuarioReportado;
	@ManyToOne @JoinColumn(name = "usuario_reportador_id", nullable=false, updatable=false) private Usuario usuarioReportador;
	@ManyToOne @JoinColumn(name = "administrador_id", nullable=true, insertable=false) private Usuario administrador;	
	@ManyToOne @JoinColumn(name = "actividad_id", nullable=false, updatable=false) private Actividad actividad;
	@Column(name="fecha_reporte", nullable=false, insertable=false, updatable=false) private Date fechaReporte;

	public Reporte() {
	}

	public String getMotivoReporte() {
		return motivoReporte;
	}
	public void setMotivoReporte(String motivoReporte) {
		this.motivoReporte = motivoReporte;
	}
	public int getReporteId() {
		return reporteId;
	}
	public void setReporteId(int reporteId) {
		this.reporteId = reporteId;
	}
	public Usuario getUsuarioReportado() {
		return usuarioReportado;
	}
	public void setUsuarioReportado(Usuario usuarioReportado) {
		this.usuarioReportado = usuarioReportado;
	}
	public Usuario getUsuarioReportador() {
		return usuarioReportador;
	}
	public void setUsuarioReportador(Usuario usuarioReportador) {
		this.usuarioReportador = usuarioReportador;
	}
	public Usuario getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public Date getFechaReporte() {
		return fechaReporte;
	}
	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}
	
	@Override
	public boolean equals(Object a){
		return ((Reporte)a).reporteId == this.reporteId;
	}

	@Override
    	public int hashCode() {
    		return this.reporteId;
    	}

}