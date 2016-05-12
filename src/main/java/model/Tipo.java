package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="tipo_id", unique=true, nullable=false)
	private int tipoId;


	
	
	public Tipo() {
	}

	


	@Override
	public boolean equals(Object a){
		return ((Tipo)a).tipoId == this.tipoId;
	}

	@Override
    	public int hashCode() {
    		return this.tipoId;
    	}

}