package com.ticketmanor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement @JsonIgnoreProperties(ignoreUnknown=true)
@Entity @Table(name="movies")
public class Movie extends Act {

	private static final long serialVersionUID = 4996635516154938063L;

	private String director;
	
	public Movie(String director, String title, int year) {
		super(ActType.MOVIE);
		this.title = title;
		this.year = year;
		this.director = director;
	}
	
	public Movie() {
		// required by JPA
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return getTitle() + " " + getYear();
	}
}
