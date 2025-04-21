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

import com.ticketmanor.model.ActType;
import com.ticketmanor.model.Event;

@Path("/sports")
public class SportsResource {

	@PersistenceUnit(name="ticketManor") EntityManagerFactory emf; // Web tier is multi-threaded
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getSportsEvents() {
		return emf.createEntityManager().createQuery("FROM Event e WHERE e.what.type = ?1", Event.class)
				.setParameter(1, ActType.SPORTS).getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Transactional(value=TxType.REQUIRED)
	public Event getSportsEvent(@PathParam("id") long id) {
		System.out.println("ConcertResource.getConcert(" + id + ")");
		return emf.createEntityManager().find(Event.class, id);
	}
}
