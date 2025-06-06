package com.northeastern.choreless.model;


import java.util.List;
import java.util.Map;

import jakarta.persistence.*;

@Entity
public class Roommate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
    private String email;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="group_id",referencedColumnName="groupId")
    private ChoreGroup choregroup;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public Roommate(int id, String name, String email, ChoreGroup choregroup) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.choregroup = choregroup;
	}
	public ChoreGroup getChoregroup() {
		return choregroup;
	}
	public void setChoregroup(ChoreGroup choregroup) {
		this.choregroup = choregroup;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Roommate [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	public Roommate(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Roommate() {
		super();
		// TODO Auto-generated constructor stub
	}

   

	}
