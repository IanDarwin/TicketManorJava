package com.ticketmanor.rest;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.ticketmanor.model.Event;

/**
 * A JAX-RS interface to the Events data. This version uses JPA, not the EJB.
 */
@Path("/events")
public class EventResource {

	@PersistenceUnit(name="ticketManor") EntityManagerFactory emf; // Web tier is multi-threaded
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getConcerts() {
		return emf.createEntityManager().createQuery("FROM Event", Event.class).getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Transactional(value=TxType.REQUIRED)
	public Event getConcert(@PathParam("id") long id) {
		System.out.println("ConcertResource.getConcert(" + id + ")");
		return emf.createEntityManager().find(Event.class, id);
	}
}
