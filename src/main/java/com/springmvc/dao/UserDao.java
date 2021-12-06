package com.springmvc.dao;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.utility.PasswordGenerator;

import org.hibernate.Transaction;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	public String saveUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		String otpgenerator = PasswordGenerator.passwordGenerator();
		user.setOtp(otpgenerator);
		try {
			session.save(user);
			// whule saving giving unique otp to user
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return "sucessful";
	}

	public int getUserDetail(User user) {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		Criteria cr = s.createCriteria(User.class);
		Criterion salary = Restrictions.eq("emailId", user.getEmailId());
		Criterion name = Restrictions.eq("password", user.getPassword());
		LogicalExpression andExp = Restrictions.and(salary, name);
		cr.add(andExp);
		List results = cr.list();
		if (results.size() == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public Integer validatingPassMail(User user) {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Criteria cr = s.createCriteria(User.class);
		Criterion emailid = Restrictions.eq("emailId", user.getEmailId());
		Criterion otpv = Restrictions.eq("otp", user.getOtp());
		LogicalExpression andExp = Restrictions.and(emailid, otpv);

		cr.add(andExp);
		System.out.println(cr);
		List<User> results = cr.list();
		System.out.println(results);
		ModelAndView model = new ModelAndView();
		if (results.size() == 0) {
			System.out.println("do Match the data");
			return 0;

		} else {
			System.out.println(" macth the data");
			// update code
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("update User set password=:password where emailId = :emailId");
			query.setParameter("password", user.getPassword());
			query.setParameter("emailId", user.getEmailId());
			int i = query.executeUpdate();
			t.commit();
			session.close();
			return i;
		}

	}

	/*
	 * package com.springmvc.dao;
	 * 
	 * import java.util.List;
	 * 
	 * import org.hibernate.Criteria; import org.hibernate.Session; import
	 * org.hibernate.cfg.Configuration;
	 * 
	 * import com.springmvc.model.User;
	 * 
	 * public class hibernatecriteriaTosave {
	 * 
	 * public static void main(String[] args) {
	 * 
	 * Session s=new
	 * Configuration().configure().buildSessionFactory().openSession();
	 * 
	 * Criteria c=s.createCriteria(User.class);
	 * 
	 * List<User>u=c.list();
	 * 
	 * for(User ut: u) { System.out.println(ut);
	 * 
	 * } s.close();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 */

}
