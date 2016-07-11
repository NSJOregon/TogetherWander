package com.togetherwander.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.togetherwander.web.dao.FormValidationGroup;
import com.togetherwander.web.dao.Event;
import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.Wander;
import com.togetherwander.web.service.EventService;
import com.togetherwander.web.service.TravelerService;
import com.togetherwander.web.service.UsersService;
import com.togetherwander.web.service.WanderService;


@Controller
public class WanderController {

	private WanderService wandersService;
	private TravelerService travelerService;
	private UsersService usersService;
    private EventService eventsService;
	
    @Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
    @Autowired
	public void setEventsService(EventService eventsService) {
		this.eventsService = eventsService;
	}

    @Autowired
	public void setWandersService(WanderService wandersService) {
		this.wandersService = wandersService;
	}

    @Autowired
	public void setTravelerService(TravelerService travelerService) {
		this.travelerService = travelerService;
	}

    
	@RequestMapping("/")
	public String showHome(Model model, Principal principal){

		List<Traveler> travelerWandersId = travelerService.getCurrentTravelers(principal.getName());
		List<Wander> wanders = wandersService.getTravelerCurrentWanders(travelerWandersId);
	    		
		System.out.println("Made it");
		usersService.getAllUsers();
		wandersService.wanderExists(5);
		System.out.println("Post flaw");
		
		System.out.println("Does the user exist?" + usersService.exists("nsjoregon"));
		
		model.addAttribute("wanders", wanders);
		
		return "home";
     
		
	}
		
	
	@RequestMapping("/createwander")
	public String createWander(Model model){
		
        model.addAttribute("wander", new Wander());
		
		return "createwander";
	
	}
	
	
	@RequestMapping(value="/removewander", method=RequestMethod.POST)
	public String removeWander(Model model, Principal principal, @RequestParam("wander") int wanderId) {

		travelerService.removeTraveler(wandersService.getWanderById(wanderId), principal.getName());
		List<Traveler> travelerWandersId = travelerService.getCurrentTravelers(principal.getName());
		List<Wander> wanders = wandersService.getTravelerCurrentWanders(travelerWandersId);
	    model.addAttribute("wanders", wanders);
		
		return "home";

	}


	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Wander wander, BindingResult result, Principal principal) {
		
		String username="";
		
		if(result.hasErrors()) {
			return "createwander";
		}
     
			
		if (principal != null) {
			username = principal.getName();
		}

			
		wandersService.create(wander, username);
		
		List<Traveler> travelerWandersId = travelerService.getCurrentTravelers(principal.getName());
		List<Wander> wanders = wandersService.getTravelerCurrentWanders(travelerWandersId);

        model.addAttribute("wanders", wanders);
		
		return "home";

	}
	
	
	@RequestMapping(value="/showwander", method=RequestMethod.POST)
	public String showWander(Model model, @Validated(FormValidationGroup.class) Traveler traveler, BindingResult result, Principal principal,@RequestParam("wander") int wanderId) {

		//public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		Wander wander = wandersService.getWanderById(wanderId);


		if((!usersService.exists(traveler.getUsername()))&&(traveler.getUsername()!=null)){
			System.out.println("The user does not exist!");
	        model.addAttribute("error", "The user does not exist!");			
		}		
		
		if(travelerService.travelerExistsforWander(traveler.getUsername(), wander.getId())){
			System.out.println("This traveler is already on this trip!");
		    model.addAttribute("error", "This traveler is already on this trip!");		
		}

		if(usersService.exists(traveler.getUsername()) && !travelerService.travelerExistsforWander(traveler.getUsername(), wanderId)){
			travelerService.create(wander, traveler.getUsername());

		}
        traveler=null;
		
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
