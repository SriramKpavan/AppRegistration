package com.jda.user.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jda.user.dao.UserDao;
import com.jda.user.model.Login;
import com.jda.user.model.User;
import com.jda.user.service.MailService;

@Controller
public class ForgotPasswordController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	MailService mailService;
	
	User user;
	
	@RequestMapping(value = "/forgotPassword")
	 public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	   ModelAndView mav = new ModelAndView("forgotPassword");

	    mav.addObject("forgotPassword", new Login());
	    return mav;
	  }
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	  public ModelAndView  loginProcess(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam("email")String email) {
		ModelAndView mav = new ModelAndView("forgotPassword");
		 User user = userDao.validateEmail(email);
		 if(user != null){
			 String token = UUID.randomUUID().toString();
			 userDao.saveToken(token, email);
			 user.setToken(token);
			 String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath();
			 mailService.sendMail(email, "reset password", "To reset your password, click the link below:\n" + appUrl + "/reset?resetToken=" + user.getToken());
			 System.out.println("true");
			 mav.addObject("message", "link sent");
			 return mav;
		 } 
		 mav.addObject("message", "user not found");
		 return mav;
	}
}
