package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name = "film")
@NamedQuery(name = "Film.findAll", query = "SELECT a FROM Film a")
public class Film implements Serializable {

	private static final long serialVersionUID = 1L;

	// Definir todas las columnas de la tabla

	@Id
	@Column(name = "film_id", unique = true, nullable = false)
	private int filmId;

	@Column(name = "title", nullable = false, length = 255)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "release_year", nullable = false)
	private int releaseYear;

	@Column(name = "language_id", nullable = false)
	private int languageId;

	@Column(name = "original_language_id", nullable = true)
	private Integer originalLanguageId;

	@Column(name = "rental_duration", nullable = false)
	private int rentalDuration;

	@Column(name = "rental_rate", nullable = false)
	private int rentalRate;

	@Column(name = "length", nullable = false)
	private int length;

	@Column(name = "replacement_cost", nullable = false)
	private int replacementCost;

	@Column(name = "rating", nullable = false)
	private String rating;

	@Column(name = "special_features", nullable = false)
	private String specialFeatures;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;


	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "film_actor", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = {
			@JoinColumn(name = "actor_id") })
	private Set<Actor> actors = new HashSet<Actor>();

	public Film() {
	}

	public Set<Actor> getActors() {
		return actors;
	}
	

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		if (originalLanguageId == 0) {

		}
		this.originalLanguageId = originalLanguageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public int getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@Override
	public boolean equals(Object f){
		if(((Film)f).filmId == this.filmId){
			return true;
		} else return false;
	}

	@Override
    	public int hashCode() {
    		return this.filmId;
    	}

}