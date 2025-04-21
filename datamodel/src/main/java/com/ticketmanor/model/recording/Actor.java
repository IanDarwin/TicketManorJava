package com.ticketmanor.model.recording;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import com.ticketmanor.model.Person;

@Entity
public class Actor extends Person {

	private static final long serialVersionUID = -2639453168023158316L;

	@OneToMany
	Set<VideoRecording> videos = new HashSet<VideoRecording>();
	
	public Actor() {
		// javabean constructor
	}
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@ManyToMany
	public Set<VideoRecording> getVideos() {
		return videos;
	}
	public void setVideos(Set<VideoRecording> films) {
		this.videos = films;
	}
}
