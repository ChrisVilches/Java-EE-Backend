package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="categoria_id", unique=true, nullable=false) private int categoriaId;
	
	@Column(name="nombre_categoria", nullable=false, length=50) private String nombreCategoria;

	@OneToMany(mappedBy="categoria", cascade=CascadeType.ALL, fetch = FetchType.LAZY) private List<Tipo> tipos;

	public Categoria() {
	}	
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}	
	public List<Tipo> getTipos() {
		return tipos;
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