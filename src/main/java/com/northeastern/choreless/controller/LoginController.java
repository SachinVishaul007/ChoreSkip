package com.northeastern.choreless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.northeastern.choreless.dao.RoommateDAO;
import com.northeastern.choreless.model.ChoreGroup;
import com.northeastern.choreless.model.Roommate;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	RoommateDAO roomatedao;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
	    Cookie[] cookies = request.getCookies();
	    

	    
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("userEmail".equals(cookie.getName())) {
	                // Auto-login logic here. Redirect the user based on their roles or other criteria
//	                model.addAttribute("username", cookie.getValue());
	            	
	                

	        	    Roommate user = roomatedao.getByEmail(cookie.getValue());
	        	    int adminId = user.getChoregroup().getAdmin().getId();
	        	    
	                model.addAttribute("welcome", "Login Successful, Welcome " + user.getChoregroup().getGroupName() + "!");
	    			model.addAttribute("chores", user.getChoregroup().getChores());

	    			model.addAttribute("roommates",user.getChoregroup().getRoommates());
//	    			if (adminId == user.getId()) {
//	    				context.setAttribute("groupid", user.getChoregroup().getGroupId());
//	    				return "admin_home";
//	    			}
	    			
	    			request.getSession().setAttribute("groupId",
    					    user.getChoregroup().getGroupId());
	    			return "home";
	            }
	        }
	    }
	    return "login";
	}

	
	
	@RequestMapping("/authorize")
	public String authorize(@RequestParam(name = "username") String email, HttpServletRequest request,
			@RequestParam(name = "password") String password, @RequestParam(name = "rememberMe", required = false) String rememberMe, Model model, HttpServletResponse response) {

//			find email from roommateDAO - return Roommate obj

		Roommate user = roomatedao.getByEmail(email);
		
//			int adminId = user.getChoregroup().getAdmin().getId();

// 			get groupID from roomate object 
		
		
		
		String error = "";

		if (user!=null && user.getChoregroup().getPassword().equals(password)) {
			
			if ("on".equals(rememberMe)) {
	            // Create a cookie with a long expiry time
	            Cookie rememberMeCookie = new Cookie("userEmail", email);
	            rememberMeCookie.setMaxAge(60 * 60 * 24 * 30); // for 30 days
	            response.addCookie(rememberMeCookie);
	        }

			model.addAttribute("welcome", "Login Successful, Welcome " + user.getChoregroup().getGroupName() + "!");
			model.addAttribute("chores", user.getChoregroup().getChores());

			model.addAttribute("roommates",user.getChoregroup().getRoommates());
//			if (adminId == user.getId()) {
//				context.setAttribute("groupid", user.getChoregroup().getGroupId());
//				return "admin_home";
//			}
			request.getSession().setAttribute("groupId",
				    user.getChoregroup().getGroupId());

			model.addAttribute("groupid", user.getChoregroup().getGroupId());
			return "home";
		} else {
			System.out.println("Login Failed");
			error = "Login Failed";
		}

		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping("/loginspring")
	public String loginspring() {
		return "loginspring";
	}

//	@RequestMapping("/logout")
//	public String logout(Model model) {
//		model.addAttribute("msg", "Logged out!");
//		return "login";
//	}

}
