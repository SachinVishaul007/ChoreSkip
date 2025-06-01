package com.northeastern.choreless.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RoommateChoreDebtor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "roommate_id")
    private Roommate roommate;

    @ManyToOne
    @JoinColumn(name = "chore_id")
    private Chore chore;

    
    private List<Integer> debtors;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Roommate getRoommate() {
		return roommate;
	}


	public void setRoommate(Roommate roommate) {
		this.roommate = roommate;
	}


	public Chore getChore() {
		return chore;
	}


	public void setChore(Chore chore) {
		this.chore = chore;
	}


	public List<Integer> getDebtors() {
		return debtors;
	}


	public void setDebtors(List<Integer> debtors) {
		this.debtors = debtors;
	}


	public RoommateChoreDebtor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RoommateChoreDebtor(int id, Roommate roommate, Chore chore, List<Integer> debtors) {
		super();
		this.id = id;
		this.roommate = roommate;
		this.chore = chore;
		this.debtors = debtors;
	}
    
    
}
