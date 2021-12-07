package com.springmvc.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.service.UserService;

/**
 * 
 * @author User
 *
 */
@Controller
@RequestMapping(value = "/user")
public class PasswordChangeController {

	@Autowired
	org.hibernate.SessionFactory SessionFactory;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/password/reset")
	public ModelAndView loadPasswordResetPage() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("password");
		return model;
	}

	/**
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/password/check")
	public ModelAndView loadPasswordUpdatePage(@ModelAttribute User user, HttpServletRequest request) throws Exception {
		Integer result = userService.passAndEmailChecking(user);
		ModelAndView model = new ModelAndView();
		if (result == 0) {

			model.setViewName("invalidemailandotp");
		} else {
			model.addObject("name", user.getEmailId());
			model.setViewName("passwordupdated");
		}
		return model;
	}
}
