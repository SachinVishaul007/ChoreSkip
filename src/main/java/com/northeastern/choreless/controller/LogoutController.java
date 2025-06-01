package com.northeastern.choreless.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response, Model model) {
	    Cookie rememberMeCookie = new Cookie("userEmail", null);
	    rememberMeCookie.setMaxAge(0); // Delete the cookie
	    response.addCookie(rememberMeCookie);
	    
	    model.addAttribute("msg", "Logged out!");
	    return "login";
	}
}
