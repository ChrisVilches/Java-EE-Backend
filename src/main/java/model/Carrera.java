package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="carrera")
public class Carrera implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="carrera_id", unique=true, nullable=false)
	private int carreraId;	

	@Column(name="nombre_carrera", nullable=false, length=45) 	private String nombreCarrera;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	

	public Carrera() {
	}
	
	
	public int getCarreraId() {
		return carreraId;
	}


	public void setCarreraId(int carreraId) {
		this.carreraId = carreraId;
	}


	public String getNombreCarrera() {
		return nombreCarrera;
	}


	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	@Override
	public boolean equals(Object a){
		return ((Carrera)a).carreraId == this.carreraId;
	}

	@Override
    	public int hashCode() {
    		return this.carreraId;
    	}

}