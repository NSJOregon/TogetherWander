package com.togetherwander.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.togetherwander.web.dao.FormValidationGroup;
import com.togetherwander.web.dao.User;
import com.togetherwander.web.service.UsersService;

@Controller
public class LoginController {
	
	private UsersService usersService;
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "login";
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	

	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "newaccount";
		}
		
		user.setAuthority("user");
		user.setEnabled(true);
		
		
		
		if(usersService.exists(user.getUsername())){
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists!");
			return "newaccount";
		}
		

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		return "home";
	}
}
