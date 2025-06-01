package com.northeastern.choreless.model;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Chore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int choreId;

	private String choreName;
	
	private List<Integer> debtors_next_in_line; // List of people who have to do the chore out of order for a specific
												// chore
	@OneToMany(mappedBy = "chore", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoommateChoreDebtor> debtorAssociations;

	private String choreType;

	private String day;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private ChoreGroup choregroup;
	
	String sacrifice;
	int currIndex;
	String last_chore_sub_time;
	
	private String status;
	
	private String skipHistory;

	public boolean isSkipActive(){
        return skipHistory != null && !skipHistory.isEmpty();
    }

	public String getSkipHistory() {
	    return skipHistory;
	}

	public void setSkipHistory(String skipHistory) {
	    this.skipHistory = skipHistory;
	}
	

	public int getChoreId() {
		return choreId;
	}

	public ChoreGroup getChoregroup() {
		return choregroup;
	}

	public void setChoregroup(ChoreGroup choregroup) {
		this.choregroup = choregroup;
	}

	public void setChoreId(int choreId) {
		this.choreId = choreId;
	}

	public String getChoreName() {
		return choreName;
	}

	public void setChoreName(String choreName) {
		this.choreName = choreName;
	}

	public List<Integer> getDebtors_next_in_line() {
		return debtors_next_in_line;
	}

	public void setDebtors_next_in_line(List<Integer> debtors_next_in_line) {
		this.debtors_next_in_line = debtors_next_in_line;
	}

	public List<RoommateChoreDebtor> getDebtorAssociations() {
		return debtorAssociations;
	}

	public void setDebtorAssociations(List<RoommateChoreDebtor> debtorAssociations) {
		this.debtorAssociations = debtorAssociations;
	}

	public String getChoreType() {
		return choreType;
	}

	public void setChoreType(String choreType) {
		this.choreType = choreType;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	public String getSacrifice() {
		return sacrifice;
	}

	public void setSacrifice(String sacrifice) {
		this.sacrifice = sacrifice;
	}

	public int getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(int currIndex) {
		this.currIndex = currIndex;
	}

	public String getLast_chore_sub_time() {
		return last_chore_sub_time;
	}

	public void setLast_chore_sub_time(String last_chore_sub_time) {
		this.last_chore_sub_time = last_chore_sub_time;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	

	public Chore(int choreId, String choreName, List<Integer> debtors_next_in_line,
			List<RoommateChoreDebtor> debtorAssociations, String choreType, String day,
			String sacrifice, int currIndex, String last_chore_sub_time, String status) {
		super();
		this.choreId = choreId;
		this.choreName = choreName;
		this.debtors_next_in_line = debtors_next_in_line;
		this.debtorAssociations = debtorAssociations;
		this.choreType = choreType;
		this.day = day;
		this.sacrifice = sacrifice;
		this.currIndex = currIndex;
		this.last_chore_sub_time = last_chore_sub_time;
		this.status = status;
	}

	public Chore() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Chore [choreId=" + choreId + ", choreName=" + choreName + ", debtors_next_in_line="
				+ debtors_next_in_line + ", debtorAssociations=" + debtorAssociations + ", choreType=" + choreType
				+ ", day=" + day + ", choregroup=" + choregroup + ", sacrifice=" + sacrifice + ", currIndex="
				+ currIndex + ", last_chore_sub_time=" + last_chore_sub_time + ", status=" + status + "]";
	}

	
	
}
