package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="calificacion")
public class Calificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="calificacion_id", unique=true, nullable=false)
	private int calificacionId;


	public Calificacion() {
	}


	@Override
	public boolean equals(Object a){
		return ((Calificacion)a).calificacionId == this.calificacionId;
	}

	@Override
    	public int hashCode() {
    		return this.calificacionId;
    	}

}