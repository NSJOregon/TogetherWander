package com.togetherwander.web.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="wanders")
public class Wander {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters")
	private String name;
	
	@Column(name="notes")
	@Size(min=5, max=500, message="Notes must be between 5 and 100 characters")
	private String notes;

	public Wander() {
		
	}

	public Wander(String name, String notes, String username) {
		this.name = name;
		this.notes = notes;
	}
	
	
	public Wander(int id, String name, String notes, String username) {
		super();
		this.id = id;
		this.name = name;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}



	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", notes=" + notes+"]";
	}


}
