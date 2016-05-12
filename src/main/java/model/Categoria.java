package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="categoria_id", unique=true, nullable=false)
	private int categoriaId;


	public Categoria() {
	}


	@Override
	public boolean equals(Object a){
		return ((Categoria)a).categoriaId == this.categoriaId;
	}

	@Override
    	public int hashCode() {
    		return this.categoriaId;
    	}

}