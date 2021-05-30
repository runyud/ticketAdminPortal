package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.TicketService;

@RestController
public class ResourceController {
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value="/ticket/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> ticketIdList, Model model) {
		for(String id : ticketIdList) {
			String ticketId = id.substring(8);
			ticketService.removeOne(Long.parseLong(ticketId));
		}
		
		return "delete success";
	}
}
