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


@Controller
@RequestMapping(value = "/user")
public class PasswordChangeController {

	@Autowired
	org.hibernate.SessionFactory SessionFactory;
	
	@Autowired
	UserService userService;

	@GetMapping("/password/reset")
	public ModelAndView loadPasswordResetPage() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("password");
		return model;
	}

	@PostMapping("/password/check")
	public ModelAndView loadPasswordUpdatePage(@ModelAttribute User user, HttpServletRequest request) throws Exception {
		Integer result=userService.passAndEmailChecking(user);
		ModelAndView model = new ModelAndView();
		if(result==0)
		{

			model.setViewName("invalidemailandotp");
		}else
		{
		model.addObject("name",user.getEmailId());
		model.setViewName("passwordupdated");
		}
		return model;
	}
	
//	
//	
//	@PersistenceContext
//	EntityManager em;
//	@PostMapping("/update")
//	public ModelAndView updatethepassword(HttpServletRequest request) throws Exception {
//		ModelAndView model = new ModelAndView();
//		String newpass=request.getParameter("pass");
//		//https://stackoverflow.com/questions/25817997/hibernate-execute-update-with-criteria
//		//EntityManager  em = this.entityManagerContainer().get( User.class );
//		//https://www.baeldung.com/hibernate-entitymanager
////		CriteriaBuilder cb = this.em.getCriteriaBuilder();
////		// create update
////		CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
////		// set the root class
////		Root e = update.from(User.class);
////		// set update and where clause
////		update.set("password", newpass);
////		
////		// perform update
////		this.em.createQuery(update).executeUpdate();
//		String emailId="sujay@gmail.com";
//		Session session = SessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		Query query = session.createQuery("from User where emailId = :emailId");
//		query.setParameter("password",newpass );
//		Optional<User> user = query.uniqueResultOptional();
//		t.commit();
//		session.close();
//		System.out.println("updation sucessful");
//		model.setViewName("passwordchange");
//		return model;
//		
//
//	}
//	
		
	}

