package com.ticketmanor.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import com.ticketmanor.model.Act;
import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;
import com.ticketmanor.model.Location;
import com.ticketmanor.model.Venue;

/** 
 * EJB to manage list of Concerts and SportsEvents
 * Generates fake data into an in-memory List<Event> to reduce overhead;
 * IRL this would be database-intensive, use JPA, have @Transaction
 * annotations, etc.
 */
@Singleton
@Startup
@Local @Remote
@Path("eventsEjb")
public class EventsEjb {
    private static final long serialVersionUID = +531574255525675305L;

	@PersistenceContext EntityManager em;

	private static List<Event> allConcerts;
	private static List<Event> allSportsEvents;

	/**
	 * Get all Concerts
	 * @return A list of all Events
	 */
	public List<Event> getAllConcerts() {
		return allConcerts;
	}
	/**
	 * Get all SportsEvents
	 * @return A list of all Events
	 */
	public List<Event> getAllSportsEvents() {
		return allConcerts;
	}

	/*
	 * Make up new fake events; scheduled to run daily before midnight.
	 * Switches lists atomically in case one is viewing while this runs.
	 */
    @PostConstruct
    @Schedule(hour="23", minute="55", persistent=false)
    public void started() {
        System.out.println("EventsEjb started()");
		allConcerts = fakeEvents(ActType.MUSICAL);
		allSportsEvents = fakeEvents(ActType.SPORTS);
    }

	/**
	 * Uses Vector as necessary for thread safety.
	 */
    public List<Event> fakeEvents(ActType type) {
		System.out.println("EventsEjb::fakeEvents called");
		Act firstAct = em.find(Act.class, 1L); // must exist
		List<Act> actors = em.createQuery("select act from Act act where act.type = ?1", Act.class).
				setParameter(1, type).
				getResultList();
		
		List<Venue> allVenues = em.createQuery("select v from Venue v", Venue.class).getResultList();
		List<Event> newAll = new Vector<>();
		LocalDateTime start = LocalDateTime.now().withHour(19).withMinute(0);
		int nTeams = 0, nVenues=0;
		Event ev = new Event(firstAct, start, allVenues.get(0));
		newAll.add(ev);
		for (int i = 0; i < 20; i++) {
			nTeams++; nTeams %= actors.size();
			nVenues++; nVenues %= allVenues.size();
			Event sp = new Event(actors.get(nTeams), start.plusDays(i), allVenues.get(nVenues));
			newAll.add(sp);
		}
		return newAll;
	}

	public List<Event> getConcertsForDate(LocalDate selectedDate) {
		return getEventsForDate(selectedDate, allConcerts);
	}
	public List<Event> getSportsEventsForDate(LocalDate selectedDate) {
		return getEventsForDate(selectedDate, allSportsEvents);
	}
	
	/** 
	 * Get a list of Events on the given date
	 * @param selectedDate The date on which to look for events.
	 * @return a list of Events for that date
	 */
	public List<Event> getEventsForDate(LocalDate selectedDate, List<Event> list) {
		final List<Event> l = new Vector<>();
		return list
			.stream()
			.filter(evt->evt.getDate().equals(selectedDate))
			.toList();
	}

	/**
	 * Getter used by JSF and also from REST.
	 */
	@GET
	public List<Event> getConcertsNextNDays(int nDays) {
		return getEventsNextNDays(nDays, allConcerts);
	}
	/**
	 * Getter used by JSF and also from REST.
	 */
	@GET
	public List<Event> getSportsEventsNextNDays(int nDays) {
		return getEventsNextNDays(nDays, allConcerts);
	}
	
	/**
	 * Get events that will occur in the next 'n' days
	 * @param nDays The range of days (now to 'N' days hence) to look for
	 * @return A list of Events over the next n days
	 */
	public List<Event> getEventsNextNDays(int nDays, List<Event> list) {
		return list
			.stream()
			.filter(evt -> dateInRange(evt, nDays))
			.toList();
	}

	private final boolean dateInRange(Event evt, int nDays) {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.from(start).plusDays(nDays);
		long evtDay = evt.getDate().toLocalDate().toEpochDay();

		return evtDay >= start.toEpochDay() && evtDay <= end.toEpochDay();
	}

	/**
	 * Add a Concert to the database
	 * @param event The Event object to be added
	 */
	public void addConcert(Event event) {
		allConcerts.add(event);
	}

	/**
	 * Remove a Concert from the database.
	 * @param event The Event object to remove
	 */
	public void deleteConcert(Event event) {
		allConcerts.remove(event);
	}

	// Should replicate the above 2 for SportsEvents
}
