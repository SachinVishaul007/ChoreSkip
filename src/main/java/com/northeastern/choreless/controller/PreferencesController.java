package com.northeastern.choreless.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.northeastern.choreless.dao.GroupDAO;
import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;

@Controller
public class PreferencesController {

	@Autowired
	GroupDAO groupDao;

	@GetMapping("/adminpreferences/{id}")
	public String preferences(@PathVariable("id") int id, Model model) {
		ChoreGroup group = groupDao.getById(id);
		model.addAttribute("group", group);
		return "admin_preferences";
	}

	@PostMapping("/adminpreferences")
	public String post_preferences(@ModelAttribute("group") ChoreGroup editedGroup, Model model) {

		boolean successful = true;
		// edit check
		try {
			groupDao.update(editedGroup);
		} catch (Exception e) {
			successful = false;
		}

		// roommate delete check
		// chores delete check

		if (successful)
			model.addAttribute("msg", "Edit Successful!");
		else
			model.addAttribute("msg", "Invalid Values!");

		return "admin_preferences";
	}

}
