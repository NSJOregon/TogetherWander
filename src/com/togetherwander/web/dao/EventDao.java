package com.togetherwander.web.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Component("eventDao")
public class EventDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	

	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	
	private static NamedParameterJdbcTemplate jdbc;
	
	@SuppressWarnings("static-access")
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public void saveOrUpdate(Event event) {
		session().saveOrUpdate(event);
	}

	@SuppressWarnings("unchecked")	
	public Traveler getAdminTraveler(Wander wander, String username){
		
		Query query = session().createQuery("from Traveler where id=:id and username=:username and role='admin'");
		query.setInteger("id", wander.getId());
		query.setString("username", username);
		return (Traveler) query.uniqueResult();

    }
	
	@SuppressWarnings("unchecked")	
	public List<Event> getDates(Wander wander) {
		
		Query query = session().createQuery("from Event where id=:id");
		query.setInteger("id", wander.getId());
		return query.list();
	}
    
	@Transactional
	public void create(Wander wander, Event dateExt) {
		
		Event date = new Event();

		date.setId(wander.getId());
		date.setDate(dateExt.getDate());
		date.setNotes(dateExt.getNotes());
	  	session().save(date);
			
	}


	
	@Transactional
	public boolean exists(String notes) {

		    Event event = null;
			event=(Event)session().createQuery("from Event where notes='"+notes+"'").uniqueResult();		
				
			if(event!=null){
			  return true;
			}else{
			  return false;
			} 

	}
	
	@SuppressWarnings("unchecked")
	public List<Traveler> getTravelers(String username) {
		
		Query query = session().createQuery("from Traveler where username=:username");
		query.setString("username", username);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents(int wanderId) {
		Query query = session().createQuery("from Event where id=:id");
		query.setInteger("id", wanderId);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public void deleteEvent(int eventId) {
		Query query = session().createQuery("delete from Event where datesid=:id");
		query.setLong("id", eventId);
	    query.executeUpdate();
	
	}

	public Event getEvent(int eventId) {
	  return (Event)session().createQuery("from Event where datesid='"+eventId+"'").uniqueResult();	
	}

	public void Update(Event event) {
	    	Event singleEvent=(Event)session().createQuery("from Event where datesId='"+event.getDatesid()+"'").uniqueResult();
		    singleEvent.setNotes(event.getNotes());
		    singleEvent.setDate(event.getDate());
		    session().update(singleEvent);
		
	}

}
