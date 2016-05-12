package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="administrador")
public class Administrador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="administrador_id", unique=true, nullable=false)
	private int administradorId;


	public Administrador() {
	}


	@Override
	public boolean equals(Object a){
		return ((Administrador)a).administradorId == this.administradorId;
	}

	@Override
    	public int hashCode() {
    		return this.administradorId;
    	}

}