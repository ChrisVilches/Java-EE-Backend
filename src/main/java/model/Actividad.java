package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import javax.persistence.*;



@Entity
@Table(name="actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id 
	@Column(name="actividad_id", unique=true, nullable=false) private Integer actividadId;
	
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
	
	@ManyToOne @JoinColumn(name = "organizador_id", nullable=false, updatable=true) private Usuario organizador;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_actividad", joinColumns = { @JoinColumn(name = "actividad_id") }, inverseJoinColumns = {
			@JoinColumn(name = "usuario_id") })
	private List<Usuario> participantes;

	
	/**
	 * Verdadero si la actividad ya finalizo
	 * @param timeActual (long, se puede obtener con new Date().getTime())
	 * @return verdadero si ya finalizo
	 */
	public boolean yaFinalizo(long timeActual){
		
		long inicio = getFechaInicio().getTime();
		long duraci = getDuracionEstimada().getTime();
		
		long tiempoFin = inicio + duraci;
		
		return timeActual >= tiempoFin;		
	}
	
	
	
	/**
	 * Toma una lista de actividades, y cambia la misma lista pero solo con las que no han terminado
	 * @param actividades
	 */
	public static void noFinalizadas(List<Actividad> actividades){		
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();		
		long now = new Date().getTime();
		for(Actividad act : actividades)
			if(!act.yaFinalizo(now)) 
				resultado.add(act);		
		
		actividades.clear();
		for(Actividad act : resultado)
			actividades.add(act);
			
	}
	
	
	/**
	 * Toma una lista de actividades, y cambia la misma lista solo con las que el usuario no participa, y esta dentro del rango de tiempo
	 * @param actividades
	 * @param dentroDeMinutos
	 * @param usuarioId
	 */
	public static void noParticipaDentroDe(List<Actividad> actividades, long dentroDeMinutos, int usuarioId){
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();				
		long ahora = (new Date()).getTime();
		long limite = ahora + (dentroDeMinutos * 60 * 1000);		
		for(Actividad act : actividades){			
			long tiempoActividad = act.getFechaInicio().getTime();							
			if(!act.usuarioEsOrganizadorOParticipante(usuarioId) && ahora < tiempoActividad && tiempoActividad < limite){
				resultado.add(act);
			}			
		}				
		
		actividades.clear();
		for(Actividad act : resultado)
			actividades.add(act);
	}
	
	
	
	
	public static void limitABfake(List<Actividad> actividades, int a, int b){
		
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();
		for(int i=a; i<a+b; i++){			
			resultado.add(actividades.get(i));			
		}		
		actividades.clear();
		for(Actividad act : resultado)
			actividades.add(act);		
	}
	
	
	
	public boolean usuarioEsOrganizadorOParticipante(int usuarioId){
		
		if(organizador.getUsuarioId() == usuarioId) return true;
		
		for(Usuario u : participantes){
			if(u.getUsuarioId() == usuarioId) return true;
		}
		return false;		
	}
	
	public void setParticipantesFake(List<Usuario> listaParticipantes){
		this.participantes = listaParticipantes;
	}
	
	
	public List<Usuario> getParticipantes() {
		return participantes;
	}
	public Usuario getOrganizador() {
		return organizador;
	}
	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
	}
	public Integer getActividadId() {
		return actividadId;
	}
	public void setActividadId(Integer actividadId) {
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