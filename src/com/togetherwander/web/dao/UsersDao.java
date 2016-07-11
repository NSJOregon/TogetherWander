package com.togetherwander.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	
	@Transactional
	public void create(User user) {

		  	session().save(user);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}
	
	@SuppressWarnings("unchecked")
	public boolean exists(String username) {
	
	    
        User user = null;
		user=(User)session().createQuery("from User where username='"+username+"'").uniqueResult();		
			
		if(user!=null){
		  return true;
		}else{
		  return false;
		} 
	
	}

}
