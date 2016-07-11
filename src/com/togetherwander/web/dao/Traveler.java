package com.togetherwander.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="travelers")
public class Traveler {

	@Id
	@Column(name="travelersid")
	private int travelerid;
	

	@Column(name="id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	@Column(name="username")
	private String username;

	public Traveler() {
		
	}

	public Traveler(String username) {
		this.username = username;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTravelerid() {
		return travelerid;
	}

	public void setTravelerid(int travelerid) {
		this.travelerid = travelerid;
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id= id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

		

	
}
