package model;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import util.Util;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario_id", unique=true, nullable=false) 	private Integer usuarioId;

	@Column(name="correo", nullable=false, length=45, updatable=false) 	private String correo;
	@Column(name="password", nullable=false, length=45) 	private String password;
	@Column(name="disponibilidad", nullable=false, insertable=false) private Boolean disponibilidad;
	@Column(name="created_at", nullable=false, insertable=false, updatable=false) private Date createdAt;
	@Column(name="last_update", nullable=false, insertable=false, updatable=false) private Timestamp lastUpdate;
	
	@Column(name="last_position_x", nullable=true, insertable=false) private Float lastPositionX;
	@Column(name="last_position_y", nullable=true, insertable=false) private Float lastPositionY;
	
	@Column(name="es_activo", nullable=false, insertable=false) private Boolean esActivo;
	
	@Column(name="primer_nombre", nullable=false, length=45) 	private String primerNombre;
	@Column(name="segundo_nombre", nullable=true, length=45) 	private String segundoNombre;
	@Column(name="apellido_paterno", nullable=true, length=45) 	private String apellidoPaterno;
	@Column(name="apellido_materno", nullable=true, length=45) 	private String apellidoMaterno;
	@Column(name="numero_telefono", nullable=true, length=15) 	private String numeroTelefono;
	@Column(name="fecha_nacimiento", nullable=false) private Date fechaNacimiento;
	@Column(name="sexo", nullable=false) private Boolean sexo;
	
	@Column(name="es_administrador", nullable=false) private Boolean esAdministrador;
	
	@Column(name="intereses", nullable=true) 	private String intereses;	
	@Column(name="url_facebook", nullable=true, length=128) 	private String urlFacebook;
	@Column(name="url_instagram", nullable=true, length=128) 	private String urlInstagram;
	@Column(name="url_twitter", nullable=true, length=128) 	private String urlTwitter;
		
	@ManyToOne @JoinColumn(name = "carrera_id", nullable=false) private Carrera carrera;
	
	@OneToMany(mappedBy="organizador", cascade=CascadeType.ALL, fetch = FetchType.LAZY) private List<Actividad> actividadesOrganizadas;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "participantes")
	private List<Actividad> actividades;

	
	public void normalizarStrings(){
		setCorreo(getCorreo().toLowerCase().trim());
		setPrimerNombre(Util.normalizarNombre(getPrimerNombre()));
		setSegundoNombre(Util.normalizarNombre(getSegundoNombre()));
		setApellidoMaterno(Util.normalizarNombre(getApellidoMaterno()));
		setApellidoPaterno(Util.normalizarNombre(getApellidoPaterno()));	
	}
	
	public boolean correoFormatoCorrecto(){
		return getCorreo().matches("[a-z.]+");
	}
	
	
	
	public List<Actividad> getActividades() {
		return actividades;
	}
	
	public List<Actividad> getActividadesOrganizadas(){
		return this.actividadesOrganizadas;
	}

	public Usuario() {
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Float getLastPositionX() {
		return lastPositionX;
	}

	public void setLastPositionX(Float lastPositionX) {
		this.lastPositionX = lastPositionX;
	}
	
	public Float getLastPositionY() {
		return lastPositionY;
	}

	public void setLastPositionY(Float lastPositionY) {
		this.lastPositionY = lastPositionY;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getSexo() {
		return sexo;
	}

	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}
	
	
	public Boolean getEsAdministrador() {
		return esAdministrador;
	}

	public void setEsAdministrador(Boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}
	
	

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	public String getUrlFacebook() {
		return urlFacebook;
	}

	public void setUrlFacebook(String urlFacebook) {
		this.urlFacebook = urlFacebook;
	}

	public String getUrlInstagram() {
		return urlInstagram;
	}

	public void setUrlInstagram(String urlInstagram) {
		this.urlInstagram = urlInstagram;
	}

	public String getUrlTwitter() {
		return urlTwitter;
	}

	public void setUrlTwitter(String urlTwitter) {
		this.urlTwitter = urlTwitter;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}


	@Override
	public boolean equals(Object a){
		return ((Usuario)a).usuarioId == this.usuarioId;
	}

	@Override
    	public int hashCode() {
    		return this.usuarioId;
    	}

}