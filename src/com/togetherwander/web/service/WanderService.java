package com.togetherwander.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.Wander;
import com.togetherwander.web.dao.WanderDao;

@Service("wanderService")
public class WanderService {

	// You could put multiple DAOs in here
	
	private WanderDao wanderDao;


	@Autowired
	public void setWanderDAO(WanderDao wanderDao) {
		this.wanderDao = wanderDao;
	}

	
	public List<Wander> getCurrent() {

		return wanderDao.getWanders();

	}

	
    public Wander getWanderById(int id) {		
		
		return wanderDao.getWander(id);
    }
	
    
    
    
	
	public void create(Wander wander, String username) {
		
		wanderDao.create(wander, username);
	}
	
	
	
	public void remove(int wanderId) {
		wanderDao.delete(wanderId);
		
	}


	public List<Wander> getTravelerCurrentWanders(List<Traveler> travelers) {
		
		List<Wander> wanders = new ArrayList<Wander>();
		
		for(Traveler traveler: travelers){
		  wanders.add(wanderDao.getWander(traveler.getId()));
		}
		
		return wanders;
	}


	public boolean wanderExists(int i) {
		return wanderDao.exists(i);
		
	}






}
