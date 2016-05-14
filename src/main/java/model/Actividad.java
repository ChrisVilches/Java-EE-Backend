package model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

import javax.persistence.*;



@Entity
@Table(name="actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id 
	@Column(name="actividad_id", unique=true, nullable=false) private int actividadId;
	
	@Column(name="titulo_actividad", nullable=false, length=50) 	private String tituloActividad;
	@Column(name="cuerpo_actividad", nullable=true, length=1028) 	private String cuerpoActividad;
	@Column(name="requerimientos_actividad", nullable=true, length=128) 	private String requerimientosActividad;
	@Column(name="ubicacion_actividad_x", nullable=true) 	private Float ubicacionActividadX;
	@Column(name="ubicacion_actividad_y", nullable=true) 	private Float ubicacionActividadY;
	@Column(name="personas_maximas", nullable=true) 	private Integer personasMaximas;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio", nullable=false) 	private Date fechaInicio;
	@Column(name="duracion_estimada", nullable=true) 	private Time duracionEstimada;
	@Column(name="es_activo", nullable=false, insertable=false) 	private Boolean esActivo;	
		
	@ManyToOne @JoinColumn(name = "tipo_id", nullable=false) private Tipo tipo;
	
		


	public int getActividadId() {
		return actividadId;
	}
	public void setActividadId(int actividadId) {
		this.actividadId = actividadId;
	}
	public String getTituloActividad() {
		return tituloActividad;
	}
	public void setTituloActividad(String tituloActividad) {
		this.tituloActividad = tituloActividad;
	}
	public String getCuerpoActividad() {
		return cuerpoActividad;
	}
	public void setCuerpoActividad(String cuerpoActividad) {
		this.cuerpoActividad = cuerpoActividad;
	}
	public String getRequerimientosActividad() {
		return requerimientosActividad;
	}
	public void setRequerimientosActividad(String requerimientosActividad) {
		this.requerimientosActividad = requerimientosActividad;
	}
	public Float getUbicacionActividadX() {
		return ubicacionActividadX;
	}
	public void setUbicacionActividadX(Float ubicacionActividadX) {
		this.ubicacionActividadX = ubicacionActividadX;
	}
	public Float getUbicacionActividadY() {
		return ubicacionActividadY;
	}
	public void setUbicacionActividadY(Float ubicacionActividadY) {
		this.ubicacionActividadY = ubicacionActividadY;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Time getDuracionEstimada() {
		return duracionEstimada;
	}
	public void setDuracionEstimada(Time duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}
	public Boolean getEsActivo() {
		return esActivo;
	}
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Actividad() {
	}
	public Integer getPersonasMaximas() {
		return personasMaximas;
	}
	public void setPersonasMaximas(Integer personasMaximas) {
		this.personasMaximas = personasMaximas;
	}
	
	

	@Override
	public boolean equals(Object a){
		return ((Actividad)a).actividadId == this.actividadId;
	}

	@Override
    	public int hashCode() {
    		return this.actividadId;
    	}

}