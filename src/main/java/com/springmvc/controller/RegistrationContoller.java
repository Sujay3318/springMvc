package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.service.UserService;
import com.springmvc.utility.PasswordGenerator;

/*
 * 
 * Most of the times, we use HQL for querying the database and getting the results. HQL is not preferred way for updating or deleting values because then we need to take care of any associations between tables.

Hibernate Criteria API provides object oriented approach for querying the database and getting results. We can’t use Criteria in Hibernate to run update or delete queries or any DDL statements. Hibernate Criteria query is only used to fetch the results from the database using object oriented approach.

For my Hibernate criteria example, I will use the same setup as in my HQL Example and show you how to use Criteria in Hibernate for querying databases.

Some of the common usage of Hibernate Criteria API are;
 * 
 * 
 * 
 * 
 */
@RestController
@RequestMapping(value = "/user")
public class RegistrationContoller {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public ModelAndView loadLoginPage() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("register");
		return model;
	}

	@PostMapping("/add")
	public ModelAndView createUser(@ModelAttribute User user) {
		String response = userService.create(user);
		if (response == null) {
			System.out.println(" register page again");
			ModelAndView model = new ModelAndView();
			User user2 = new User();
			model.addObject("user", user2);
			model.setViewName("alreadyExistemail");
			return model;
		} else {
			System.out.println("New page as data get stored in db");
			ModelAndView model = new ModelAndView();
			model.addObject("user", user);
			model.setViewName("registrationsuccessful");
			return model;
		}

	}

}
