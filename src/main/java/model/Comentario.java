package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="comentario")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="comentario_id", unique=true, nullable=false)
	private int comentarioId;


	public Comentario() {
	}


	@Override
	public boolean equals(Object a){
		return ((Comentario)a).comentarioId == this.comentarioId;
	}

	@Override
    	public int hashCode() {
    		return this.comentarioId;
    	}

}