package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.springmvc.model.User;
import com.springmvc.service.UserService;

/**
 * 
 * @author User
 *
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login() {
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login/check")
	public ModelAndView loginUser(@ModelAttribute User user, HttpServletRequest request) throws Exception {
		/*
		 * if(user.getFirstName().isBlank()) { return "redirect:/user/login"; } String
		 * url="/login"; RedirectView redirectView=new RedirectView();
		 * redirectView.setUrl(url);
		 */
		Integer userResponse = userService.loginUser(user);
		ModelAndView model = new ModelAndView();
		if (userResponse == 0) {

			model.addObject("response", "login failed");
			System.out.println("Login fail");
			model.setViewName("failedloginpage");
			return model;
		} else {
			System.out.println("Login successful");
			model.setViewName("loginsuccess");
			return model;
		}
	}
}
