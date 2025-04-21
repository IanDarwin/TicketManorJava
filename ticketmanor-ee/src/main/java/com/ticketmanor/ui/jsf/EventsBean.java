package com.ticketmanor.ui.jsf;

import java.util.Collections;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import com.ticketmanor.model.Event;
import com.ticketmanor.service.EventsEjb;

@Named @RequestScoped
public class EventsBean {

	@EJB
	EventsEjb ejb;

	List<Event> results;

	static final int DEFAULT_NDAYS = 5;
	
	private int nDays = DEFAULT_NDAYS;
	
	/**
	 * Get the next few days' worth of concerts.
	 * A JSF Action handler should return the next page path as a String.
	 * @return The Outcome, a string representing the next View page
	 */
	public String getConcertsNextNDays() {
		results = ejb.getConcertsNextNDays(nDays);
		return "eventsList.xhtml";
	}

	/**
	 * Get the next few days' worth of matches.
	 * A JSF Action handler should return the next page path as a String.
	 * @return The Outcome, a string representing the next View page
	 */
	public String getSportsEventsNextNDays() {
		results = ejb.getSportsEventsNextNDays(nDays);
		return "eventsList.xhtml";
	}

	/** 
	 * This method will be called to retrieve the results
	 * @return The list of Events.
	 */
	public List<Event> getResultList() {
		return results == null ? Collections.emptyList() : results;
	}

	public int getNumDays() {
		return nDays;
	}

	public void setNumDays(int nDays) {
		this.nDays = nDays;
	}
}
