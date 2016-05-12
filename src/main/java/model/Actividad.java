package model;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name="actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="actividad_id", unique=true, nullable=false)
	private int actividadId;


	public Actividad() {
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