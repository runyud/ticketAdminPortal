package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Ticket;

public interface TicketService {
	
	Ticket save(Ticket ticket);
	
	List<Ticket> findAll();

	Ticket findOne(Long id);

	void removeOne(long id);
}
