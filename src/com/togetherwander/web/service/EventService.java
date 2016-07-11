package com.togetherwander.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.togetherwander.web.dao.Event;
import com.togetherwander.web.dao.EventDao;
import com.togetherwander.web.dao.Traveler;
import com.togetherwander.web.dao.TravelerDao;
import com.togetherwander.web.dao.UsersDao;
import com.togetherwander.web.dao.Wander;


@Service("eventService")
public class EventService {

	private EventDao eventDao;
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	

	public List<Event> getEvents(int wanderId) {
		// TODO Auto-generated method stub
		return eventDao.getEvents(wanderId);
	}

	public boolean Exists(String notes) {
		return eventDao.exists(notes);
	}

	public void removeEvent(int eventId) {
		eventDao.deleteEvent(eventId);
		
	}

	public Object getEvent(int eventId) {
		return eventDao.getEvent(eventId);
	}

	public void saveOrUpdate(Event event) {
		eventDao.saveOrUpdate(event);
	}
	
	public void Update(Event event) {
		eventDao.Update(event);
		
	}
}
