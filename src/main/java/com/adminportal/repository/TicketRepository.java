package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long>{

}
