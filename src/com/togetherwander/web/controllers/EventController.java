package com.togetherwander.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.togetherwander.web.dao.Event;
import com.togetherwander.web.dao.FormValidationGroup;
import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.Wander;
import com.togetherwander.web.service.EventService;
import com.togetherwander.web.service.TravelerService;
import com.togetherwander.web.service.UsersService;
import com.togetherwander.web.service.WanderService;

@Controller
public class EventController {

    private WanderService wandersService;
    private TravelerService travelerService;
    private EventService eventsService;

	@Autowired
	public void setWandersService(WanderService wandersService) {
		this.wandersService = wandersService;
	}

	@Autowired
	public void setTravelerService(TravelerService travelerService) {
		this.travelerService = travelerService;
	}

	
	@Autowired
	public void setEventService(EventService eventsService) {
		this.eventsService = eventsService;
	}
	
    @RequestMapping("/createevent")
	public String createWander(Model model, Principal principal, @RequestParam("wander") int wanderId){
		
		
		model.addAttribute("wander",  wandersService.getWanderById(wanderId));
		    	
    	model.addAttribute("event", new Event());
		
		return "createevent";
	
	}

    
    @RequestMapping("/editevent")
	public String editEvent(Model model, Principal principal, @RequestParam("event") int eventId){
    	model.addAttribute("event", eventsService.getEvent(eventId));
		return "editevent";
	
	}

    
    
  	@RequestMapping(value = "/doeditevent", method = RequestMethod.POST)
  	public String doEditEvent(Model model, @Validated(value=FormValidationGroup.class) Event event, BindingResult result, Principal principal, @RequestParam("id") int wanderId) {

  		if (result.hasErrors()) {
  			return "editevent";
  		}

          List<Traveler> travelers= travelerService.getCurrentTravelers(wandersService.getWanderById(wanderId));
          model.addAttribute("admintraveler", travelerService.getAdminTraveler(wandersService.getWanderById(wanderId), principal.getName()));
          model.addAttribute("wander", wandersService.getWanderById(wanderId));
          model.addAttribute("travelers", travelers);
          model.addAttribute("traveler", new Traveler());
         
          eventsService.Update(event);
  		
  		
  		List<Event> events = eventsService.getEvents(wanderId);
  	    model.addAttribute("events", events);
  		
  		
  		return "wanderhome";
  		
  	}

    
    
    
    @RequestMapping(value = "/docreateevent", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Event event, BindingResult result, Principal principal, @RequestParam("id") int wanderId) {

		if (result.hasErrors()) {
			return "createevent";
		}
		

        List<Traveler> travelers= travelerService.getCurrentTravelers(wandersService.getWanderById(wanderId));
        model.addAttribute("admintraveler", travelerService.getAdminTraveler(wandersService.getWanderById(wanderId), principal.getName()));
        model.addAttribute("wander", wandersService.getWanderById(wanderId));
        model.addAttribute("travelers", travelers);
        model.addAttribute("traveler", new Traveler());
       
		
		if(!eventsService.Exists(event.getNotes()))
        eventsService.saveOrUpdate(event);
		
		
		List<Event> events = eventsService.getEvents(wanderId);
	    model.addAttribute("events", events);
		
		
		return "wanderhome";
		
	}
	
	@RequestMapping(value="/removeevent", method=RequestMethod.POST)
	public String removeEvent(Model model, Principal principal, @RequestParam("wander") int wanderId, @RequestParam("event") int eventId) {

		Wander wander = wandersService.getWanderById(wanderId);

		eventsService.removeEvent(eventId);


        List<Traveler> travelers= travelerService.getCurrentTravelers(wander);
		List<Event> events = eventsService.getEvents(wanderId);
		
        model.addAttribute("admintraveler", travelerService.getAdminTraveler(wander, principal.getName()));
        model.addAttribute("wander", wander);
        model.addAttribute("travelers", travelers);
        model.addAttribute("traveler", new Traveler());
        model.addAttribute("events", events);

        
		return "wanderhome";

	}
	
}
