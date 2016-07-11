package com.togetherwander.web.dao;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.util.Date;

import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.NotBlank;

import com.togetherwander.web.dao.FormValidationGroup;
import com.togetherwander.web.dao.PersistenceValidationGroup;

@Entity
@Table(name="dates")
public class Event {

	@Id
	@Column(name="datesid")
	@GeneratedValue
	private int datesid;
	
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;

	
	@Column(name="notes")
	@Size(min=5, max=255, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String notes;

	public Event() {
		
	}

	public Event(Date date) {
		this.date = date;
    }

	public int getDatesid() {
		return datesid;
	}

	public void setDatesid(int datesid) {
		this.datesid = datesid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
		
}
