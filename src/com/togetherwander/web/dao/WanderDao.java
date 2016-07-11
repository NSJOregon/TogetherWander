package com.togetherwander.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("wanderDao")
public class WanderDao{

	private static NamedParameterJdbcTemplate jdbc;
	@SuppressWarnings("unused")
	private TravelerDao travelerDao;
		
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	
	@Autowired
	public void setTravelerDAO(TravelerDao travelerDao) {
		this.travelerDao = travelerDao;
	}
	
	@SuppressWarnings("static-access")
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@SuppressWarnings("unchecked")
	public List<Wander> getWanders() {


		return session().createQuery("from Wander").list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Wander> getWanders(String username) {
		
		Query query = session().createQuery("from Wander where username=:username");
		query.setString("username", username);
		return query.list();
			
			
	}


	public void update(Wander wander) {

		session().saveOrUpdate(wander);
	}
	
	@Transactional
	public void create(Wander wander, String username) {
		
		Query query = session().createSQLQuery("INSERT INTO wanders (name, notes) VALUES (:name, :notes)");
		query.setString("name", wander.getName());
		query.setString("notes", wander.getNotes());
		query.executeUpdate();  	

		query = session().createSQLQuery("INSERT INTO travelers (username, role, id) SELECT :username, 'admin', id FROM wanders WHERE name =:name");
		query.setString("username", username);
		query.setString("name", wander.getName());
		query.executeUpdate();  	

	}
	

	@Transactional
	public Wander getWander(int id) {

		
		
		Criteria crit = session().createCriteria(Wander.class);
		crit.add(Restrictions.idEq(id));
		return (Wander)crit.uniqueResult();	
		
		}

	@Transactional
	public boolean exists(int id) {

		Query query = session().createQuery("from Wander where id=:id");
		query.setLong("id", id);
		if(query.uniqueResult()!=null)
			return true;
		else
			return false;
	}
	
	public boolean delete(int wanderId) {

		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", wanderId);
		return query.executeUpdate() == 1;

	}

}
