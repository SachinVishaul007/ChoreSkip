package com.northeastern.choreless.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.northeastern.choreless.dao.ChoreDAO;
import com.northeastern.choreless.dao.GroupDAO;
import com.northeastern.choreless.dao.RoommateDAO;
import com.northeastern.choreless.model.Chore;
import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;
import com.northeastern.choreless.model.RoommateChoreDebtor;

@Controller
public class RegistrationController {

	@Autowired
	GroupDAO group_dao;

	@Autowired
	RoommateDAO roommateDao;

	@Autowired
	ChoreDAO choredao;

	// SQL injection keywords to block
	private static final String[] SQL_INJECTION_KEYWORDS = {
			"select", "insert", "update", "delete", "drop", "create", "alter",
			"union", "or", "and", "where", "1=1", "1=0", "--", "/*", "*/",
			"script", "javascript", "vbscript", "onload", "onerror", "exec"
	};

	private boolean containsSQLInjection(String input) {
		if (input == null) return false;
		String lowerInput = input.toLowerCase().trim();
		for (String keyword : SQL_INJECTION_KEYWORDS) {
			if (lowerInput.contains(keyword)) {
				return true;
			}
		}
		return false;
	}



	@PostMapping("/createGroup")
	public String createGroup(ChoreGroup group, Model model, HttpServletRequest request) {
		System.out.println("Creating group");
		System.out.println(group.toString());

		// Validate group name for SQL injection
		if (containsSQLInjection(group.getGroupName())) {
			String clientIP = request.getRemoteAddr();
			System.out.println("SECURITY ALERT: SQL injection attempt in group name from IP " + clientIP + ": " + group.getGroupName());
			model.addAttribute("error", "Invalid characters detected in group name!");
			return "group";
		}


		Set<String> set = new HashSet<>();

		for (Roommate mate : group.getRoommates()) {

			// Validate roommate name for SQL injection
			if (containsSQLInjection(mate.getName())) {
				String clientIP = request.getRemoteAddr();
				System.out.println("SECURITY ALERT: SQL injection attempt in roommate name from IP " + clientIP + ": " + mate.getName());
				model.addAttribute("error", "Invalid characters detected in roommate name!");
				return "group";
			}

			// Validate email for SQL injection
			if (containsSQLInjection(mate.getEmail())) {
				String clientIP = request.getRemoteAddr();
				System.out.println("SECURITY ALERT: SQL injection attempt in email from IP " + clientIP + ": " + mate.getEmail());
				model.addAttribute("error", "Invalid characters detected in email!");
				return "group";
			}


			if (!set.add(mate.getEmail())) {
				// If add returns false, it means the element is already present in the set
				model.addAttribute("error", "Duplicate email entries! Email entries must be unique!");
				return "group";
			}

			if (roommateDao.checkByEmail(mate.getEmail())) {
				model.addAttribute("error", "Email(" + mate.getEmail() + ") already registered in database!");
				return "group";
			}

		}
		for (Roommate mate : group.getRoommates()) {

			if (mate.getName().equals(group.getAdmin().getName())) {
				group.setAdmin(mate);
				break;
			}
		}

		int groupId = group_dao.save(group);

		model.addAttribute("groupId", groupId);

		return "add-chore";
	}

	@PostMapping("/addChores/{groupId}")
	public ResponseEntity<String> addChores(@RequestBody List<Chore> chores, @PathVariable int groupId) {

		int index = 0;
		System.out.println(chores);
		System.out.println("The Group Id fetched is: " + groupId);
		ChoreGroup group = group_dao.getById(Integer.valueOf(groupId));

		List<Roommate> mate_list = roommateDao.getByGroupId(groupId);

		int numberOfRoommates = mate_list.size();

		for (Chore chore : chores) {
			chore.setChoregroup(group); // Set the group of the chore

			chore.setSacrifice(generateZerosCsv(numberOfRoommates));

//			if (chore.getChoreType().equals("week-based")) {
				chore.setCurrIndex(index);
//			}
			index = (index + 1) % (numberOfRoommates);

			choredao.save(chore);
		}

		List<Chore> chore_list = choredao.getByGroupId(groupId);

		for (Chore chore : chore_list) {
			List<RoommateChoreDebtor> rcd_list = new ArrayList<>();

			for (Roommate r : group.getRoommates()) {
				RoommateChoreDebtor rcd = new RoommateChoreDebtor();
				rcd.setRoommate(r);
				System.out.print(r.getId() + " ");
				rcd.setChore(chore);

				System.out.println(chore.getChoreId());
				rcd_list.add(rcd);
			}
			System.out.println("\n");

			chore.setDebtorAssociations(rcd_list);
			choredao.save(chore);

		}

		return ResponseEntity.ok("Registration successful! click Ok to redirect to login page");
	}

	public static String generateZerosCsv(int x) {
		if (x <= 0) {
			return ""; // Return an empty string for non-positive input
		}

		StringBuilder csvBuilder = new StringBuilder();
		for (int i = 0; i < x - 1; i++) {
			csvBuilder.append("0,");
		}
		csvBuilder.append("0");

		return csvBuilder.toString();
	}
	
	
//	public String group() {
//		return "group";
//	}

}
