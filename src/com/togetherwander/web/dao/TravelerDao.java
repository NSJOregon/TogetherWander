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
@Component("travelerDao")
public class TravelerDao {

	
	
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


	@SuppressWarnings("unchecked")	
	public Traveler getAdminTraveler(Wander wander, String username){
		
		Query query = session().createQuery("from Traveler where id=:id and username=:username and role='admin'");
		query.setInteger("id", wander.getId());
		query.setString("username", username);
		return (Traveler) query.uniqueResult();

		
				
		
	}
	
	@SuppressWarnings("unchecked")	
	public List<Traveler> getTravelers(Wander wander) {

		
		Query query = session().createQuery("from Traveler where id=:id");
		query.setInteger("id", wander.getId());
		return query.list();
	}
	

	@Transactional
	public void create(Wander wander, String travelerName) {

		
		Traveler traveler = new Traveler();
		traveler.setRole("user");
		traveler.setId(wander.getId());
		traveler.setUsername(travelerName);
	  	session().save(traveler);
			
	}
	
	@Transactional
	public boolean exists(String username) {

		    Traveler traveler = null;
			traveler=(Traveler)session().createQuery("from Traveler where username='"+username+"'").uniqueResult();		
				
			if(traveler!=null){
			  return true;
			}else{
			  return false;
			} 

	
	}
	
	@Transactional
	public boolean deleteTraveler(Wander wander, String username) {
			
		Query query = session().createQuery("delete from Traveler where id=:id and username=:username");
		query.setLong("id", wander.getId());
		query.setString("username", username);
		return query.executeUpdate() == 1;
		
		}

	@SuppressWarnings("unchecked")
	public List<Traveler> getTravelers(String username) {
		
		Query query = session().createQuery("from Traveler where username=:username");
		query.setString("username", username);
		return query.list();
		
		}



	public boolean travelerExistsforWander(String username, int id) {


		
		Query query = session().createQuery("from Traveler where id=:id and username=:username");
		query.setLong("id", id);
		query.setString("username", username);
		if(query.uniqueResult()!=null)
			return true;
		else
			return false;

	}
	}
