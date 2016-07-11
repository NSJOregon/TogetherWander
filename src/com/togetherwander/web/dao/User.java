package com.togetherwander.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@NotBlank(message="Username cannot be blank")
	@Column(name="username")
	private String username;

	@Column(name="password")
	@NotBlank(message="Password")
	private String password;

	@Column(name="name")
	@NotBlank(message="Name cannot be blank")
	private String name;

	@Column(name="email")
	@NotBlank
	@Email
	private String email;

	private boolean enabled = false;
	private String authority;
	
	
	public User() {
		
	}

	public User(String name, String username, String password, String email, boolean enabled,
			String authority) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
