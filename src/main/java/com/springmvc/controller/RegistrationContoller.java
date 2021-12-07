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

/**
 * 
 * @author User
 *
 */
@RestController
@RequestMapping(value = "/user")
public class RegistrationContoller {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/register")
	public ModelAndView loadLoginPage() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("register");
		return model;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public ModelAndView createUser(@ModelAttribute User user) {
		String response = userService.create(user);
		ModelAndView model = new ModelAndView();
		if (response == null) {
			System.out.println("mail id alreday present due to sql constarint of unique  "
					+ "register the page with new email id ");

			model.setViewName("alreadyExistemail");
			return model;
		} else {
			System.out.println(" Data get stored in db ");
			model.setViewName("registrationsuccessful");
			return model;
		}

	}

}
