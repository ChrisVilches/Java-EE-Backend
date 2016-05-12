package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="ban")
public class Ban implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ban_id", unique=true, nullable=false)
	private int banId;


	public Ban() {
	}


	@Override
	public boolean equals(Object a){
		return ((Ban)a).banId == this.banId;
	}

	@Override
    	public int hashCode() {
    		return this.banId;
    	}


}