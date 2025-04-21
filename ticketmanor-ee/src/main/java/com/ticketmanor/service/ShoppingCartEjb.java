package com.ticketmanor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jakarta.ejb.Stateful;

import com.ticketmanor.model.OrderItem;
import com.ticketmanor.model.Sellable;

/** A Shopping Cart implemented as a Stateful Session Bean */
@Stateful
public class ShoppingCartEjb {
	
	private List<OrderItem> cart = new ArrayList<>(5);
	
	/** Add the item to the cart; if the card already has an OrderItem
	 * for this Sellable we increment its quantity, else, we make
	 * a new OrderItem for it, and stash that in the card.
	 * @param s The Sellable item (Ticket, Recording, etc.)
	 */
	public void addToCart(Sellable s) {
		for (OrderItem item : cart) {
			if (item.getSellable().equals(s)) {
				item.setQuantity(item.getQuantity() + 1);
				return;
			}
		}
		cart.add(new OrderItem(s, 1));
	}
	
	/**
	 * Remove the given Sellable from the Cart.
	 * @param s The Sellable to be removed from the Cart.
	 * @return True if the remove succeeded, false otherwise.
	 */
	public boolean removeFromCart(Sellable s) {
		Iterator<OrderItem> it = cart.iterator();
		while (it.hasNext()) {
			OrderItem item = it.next();
			if (item.getSellable().equals(s)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get a list of items in the cart.
	 * @return a list of items in the cart.
	 */
	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(cart);
	}
	
	/**
	 * Get the total price of objects in the cart.
	 * @return The price in $ of all items.
	 */
	public double getTotalPrice() {
		double total = 0;
		for (OrderItem item : cart) {
			total += item.getQuantity() * item.getSellable().getPrice();
		}
		return total;
	}
}
