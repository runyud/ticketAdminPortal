package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Ticket;
import com.adminportal.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addTicket(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "addTicket";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postTicket(@ModelAttribute("ticket") Ticket ticket, HttpServletRequest request) {
		ticketService.save(ticket);

		MultipartFile ticketImage = ticket.getTicketImage();

		saveImage(ticket, ticketImage, false);
		return "redirect:ticketList";
	}

	@RequestMapping("/ticketInfo")
	public String ticketInfo(@RequestParam("id") Long id, Model model) {
		Ticket ticket = ticketService.findOne(id);
		model.addAttribute("ticket", ticket);
		
		return "ticketInfo";
	}
	
	@RequestMapping("/updateTicket")
	public String updateTicket(@RequestParam("id") Long id, Model model) {
		Ticket ticket = ticketService.findOne(id);
		model.addAttribute("ticket", ticket);
		return "updateTicket";
	}
	
	@RequestMapping(value="/updateTicket", method=RequestMethod.POST) 
	public String updateTicketPost(@ModelAttribute("ticket") Ticket ticket, HttpServletRequest request){
		ticketService.save(ticket);
		
		MultipartFile ticketImage = ticket.getTicketImage();
		
		if(!ticketImage.isEmpty()) {
			saveImage(ticket, ticketImage, true);
		}
		return "redirect:/ticket/ticketInfo?id="+ticket.getId();
	}
	
	@RequestMapping("/ticketList")
	public String ticketList(Model model) {
		List<Ticket> ticketList = ticketService.findAll();
		model.addAttribute("ticketList", ticketList);
		return "ticketList";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model) {
		ticketService.removeOne(Long.parseLong(id.substring(10)));
		List<Ticket> ticketList = ticketService.findAll();
		model.addAttribute("ticketList", ticketList);
		
		return "redirect:/ticket/ticketList";
	}
	
	private void saveImage(Ticket ticket, MultipartFile ticketImage, boolean ifUpdate) {
		try {
			byte[] bytes = ticketImage.getBytes();
			String name = ticket.getId() + ".png";
			if(ifUpdate) Files.delete(Paths.get("src/main/resources/static/img/ticket/"+name));
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/img/ticket/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
