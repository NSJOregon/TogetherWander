package com.togetherwander.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.TravelerDao;
import com.togetherwander.web.dao.UsersDao;
import com.togetherwander.web.dao.Wander;

@Service("travelerService")
public class TravelerService {

	// You could put multiple DAOs in here
	
	private TravelerDao travelerDao;
			
	@Autowired
	public void setTravelerDao(TravelerDao travelerDao) {
		this.travelerDao = travelerDao;
	}
	
	public Traveler getAdminTraveler(Wander wander, String username){
		return travelerDao.getAdminTraveler(wander, username);
	}
	
	
	public List<Traveler> getCurrentTravelers(Wander wander) {

		return travelerDao.getTravelers(wander);

	}

	
	public List<Traveler> getCurrentTravelers(String username) {

		return travelerDao.getTravelers(username);

	}

	
	public boolean exists(String username) {
		return travelerDao.exists(username);
	}
		
	public void create(Wander wander, String username) {
		
		travelerDao.create(wander, username);
	}

	public void removeTraveler(Wander wander, String username){
		
		travelerDao.deleteTraveler(wander, username);
	}

	public boolean travelerExistsforWander(String username, int id) {
	    return travelerDao.travelerExistsforWander(username, id);
		}

	
}
