package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tipo")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="tipo_id", unique=true, nullable=false) private int tipoId;
	@ManyToOne @JoinColumn(name = "categoria_id", nullable=false) private Categoria categoria;
	@Column(name="tipo", length=50, nullable=false) private String tipo;
	
	
	public Tipo() {
	}


	public int getTipoId() {
		return tipoId;
	}
	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
		
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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