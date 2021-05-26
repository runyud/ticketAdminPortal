package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Ticket;
import com.adminportal.repository.TicketRepository;
import com.adminportal.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>)ticketRepository.findAll();
	}

	@Override
	public Ticket findOne(Long id) {
		return ticketRepository.findOne(id);
	}

}
