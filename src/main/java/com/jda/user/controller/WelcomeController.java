package com.jda.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class WelcomeController {

	@RequestMapping(value = "/welcome")
	 public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String firstname=(String) session.getAttribute("name");
		System.out.println(firstname);
		return new ModelAndView("welcome","name",firstname);
	}
}
