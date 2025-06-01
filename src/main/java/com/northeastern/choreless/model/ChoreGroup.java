package com.northeastern.choreless.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class ChoreGroup {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="choregroup")
    private List<Roommate> roommates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="choregroup")
    List<Chore> chores;

    private String groupName;
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Roommate admin;
    
    private String password;
    
	public ChoreGroup(int groupId, List<Roommate> roommates, List<Chore> chores, String groupName, Roommate admin,
			String password) {
		super();
		this.groupId = groupId;
		this.roommates = roommates;
		this.chores = chores;
		this.groupName = groupName;
		this.admin = admin;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<Roommate> getRoommates() {
		return roommates;
	}

	public void setRoommates(List<Roommate> roommates) {
		this.roommates = roommates;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Roommate getAdmin() {
		return admin;
	}

	public void setAdmin(Roommate admin) {
		this.admin = admin;
	}
	
	public List<Chore> getChores() {
		return chores;
	}

	public void setChores(List<Chore> chores) {
		this.chores = chores;
	}


	public ChoreGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChoreGroup [groupId=" + groupId + ", roommates=" + roommates + ", chores=" + chores + ", groupName="
				+ groupName + ", admin=" + admin + ", password=" + password + "]";
	}

    
    
    
    
	
	
	
	
	
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	int groupId;
//	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name="fk_groupId", referencedColumnName="groupId")
//	List<Roommate> roommates;
//	
//	String groupName;
//	int currIndex;
//	String last_chore_sub_time;
//	String sacrifice;
//	
//	List<Integer> debtors_next_in_line;
//	
//	public ChoreGroup() {
//		super();
//	}
//	public ChoreGroup(List<Roommate> roommates, String groupName, int currIndex,
//			String last_chore_sub_time, String sacrifice, List<Integer> debtors_next_in_line ) {
//		super();
//		this.roommates = roommates;
//		this.groupName = groupName;
//		this.currIndex = currIndex;
//		this.last_chore_sub_time = last_chore_sub_time;
//		this.sacrifice = sacrifice;
//		this.debtors_next_in_line = debtors_next_in_line;
//	}
//	public int getGroupId() {
//		return groupId;
//	}
//	public void setGroupId(int groupId) {
//		this.groupId = groupId;
//	}
//	public List<Roommate> getRoommates() {
//		return roommates;
//	}
//	public void setRoommates(List<Roommate> roommates) {
//		this.roommates = roommates;
//	}
//	public String getGroupName() {
//		return groupName;
//	}
//	public void setGroupName(String groupName) {
//		this.groupName = groupName;
//	}
//	public int getCurrIndex() {
//		return currIndex;
//	}
//	public void setCurrIndex(int currIndex) {
//		this.currIndex = currIndex;
//	}
//	public String getLast_chore_sub_time() {
//		return last_chore_sub_time;
//	}
//	public void setLast_chore_sub_time(String last_chore_sub_time) {
//		this.last_chore_sub_time = last_chore_sub_time;
//	}
//	public String getSacrifice() {
//		return sacrifice;
//	}
//	public void setSacrifice(String sacrifice) {
//		this.sacrifice = sacrifice;
//	}
//	public List<Integer> getDebtors_next_in_line() {
//		return debtors_next_in_line;
//	}
//	public void setDebtors_next_in_line(List<Integer> debtors_next_in_line) {
//		this.debtors_next_in_line = debtors_next_in_line;
//	}
//	
//	@Override
//	public String toString() {
//		return "ChoreGroup [groupId=" + groupId + ", roommates=" + roommates + ", groupName=" + groupName
//				+ ", currIndex=" + currIndex + ", last_chore_sub_time=" + last_chore_sub_time + ", sacrifice="
//				+ sacrifice + ", debtors_next_in_line=" + debtors_next_in_line + "]";
//	}
	
	
		
}
