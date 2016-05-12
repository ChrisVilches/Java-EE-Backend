package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="reporte")
public class Reporte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="reporte_id", unique=true, nullable=false)
	private int reporteId;


	public Reporte() {
	}


	@Override
	public boolean equals(Object a){
		return ((Reporte)a).reporteId == this.reporteId;
	}

	@Override
    	public int hashCode() {
    		return this.reporteId;
    	}

}