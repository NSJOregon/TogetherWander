package com.togetherwander.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.togetherwander.web.dao.Event;
import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.Wander;
import com.togetherwander.web.service.EventService;
import com.togetherwander.web.service.TravelerService;
import com.togetherwander.web.service.UsersService;
import com.togetherwander.web.service.WanderService;


@Controller
public class TravelerController {

	private WanderService wandersService;
	private TravelerService travelerService;
	private EventService eventsService;  

	@Autowired
	public void setUsersService(UsersService usersService) {
	}
	
	@Autowired
	public void setEventService(EventService eventsService) {
		this.eventsService=eventsService;
	}
	
    @Autowired
	public void setWandersService(WanderService wandersService) {
		this.wandersService = wandersService;
	}

    @Autowired
	public void setTravelerService(TravelerService travelerService) {
		this.travelerService = travelerService;
	}
	
	

	@RequestMapping(value="/removetraveler", method=RequestMethod.POST)
	public String removeTraveler(Model model, Principal principal, @RequestParam("wander") int wanderId, @RequestParam("traveler") String username) {

		Wander wander = wandersService.getWanderById(wanderId);

		travelerService.removeTraveler(wander, username);

        List<Traveler> travelers= travelerService.getCurrentTravelers(wander);
  		List<Event> events = eventsService.getEvents(wanderId);

  		model.addAttribute("events", events);
    
        model.addAttribute("admintraveler", travelerService.getAdminTraveler(wander, principal.getName()));
        model.addAttribute("wander", wander);
        model.addAttribute("travelers", travelers);
        model.addAttribute("traveler", new Traveler());
        
		return "wanderhome";

	}

	
	
}
