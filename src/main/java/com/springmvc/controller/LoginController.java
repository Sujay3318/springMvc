package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public ModelAndView login() {
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/login/check")
	public ModelAndView loginUser(@ModelAttribute User user, HttpServletRequest request) throws Exception {
		Integer userResponse = userService.loginUser(user);
		if (0 == userResponse) {
			ModelAndView model = new ModelAndView();
			model.addObject("response", "login failed");
			System.out.println("Login fail");
			model.setViewName("failedloginpage");
			return model;
		} else {
			ModelAndView model = new ModelAndView();
			System.out.println("Login successful");
			model.setViewName("loginsuccess");
			return model;
		}

	}

}
