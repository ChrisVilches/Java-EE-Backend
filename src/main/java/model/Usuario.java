package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario_id", unique=true, nullable=false)
	private int usuarioId;


	
	
	public Usuario() {
	}

	


	@Override
	public boolean equals(Object a){
		if(((Usuario)a).usuarioId == this.usuarioId){
			return true;
		} else return false;
	}

	@Override
    	public int hashCode() {
    		return this.usuarioId;
    	}

}