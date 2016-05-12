package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="participacion")
public class Participacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="participacion_id", unique=true, nullable=false)
	private int participacionId;


	public Participacion() {
	}


	@Override
	public boolean equals(Object a){
		return ((Participacion)a).participacionId == this.participacionId;
	}

	@Override
    	public int hashCode() {
    		return this.participacionId;
    	}

}