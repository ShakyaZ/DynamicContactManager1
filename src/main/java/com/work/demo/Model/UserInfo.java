package com.work.demo.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="user_info")
public class UserInfo {
   @Id
   @GeneratedValue
	private int id;
	private String fname;
	  private String lname;
	  
	  private String username;
	  private String password;
	  private String email;
	  @OneToMany(mappedBy = "userinfo", fetch = FetchType.EAGER)
	  private List<UserContacts> contacts;
	  
	  
	public List<UserContacts> getContacts() {
		return contacts;
	}
	public void setContacts(List<UserContacts> contacts) {
		this.contacts = contacts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	  
	  
}
