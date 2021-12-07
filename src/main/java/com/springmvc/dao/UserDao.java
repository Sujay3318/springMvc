package com.springmvc.dao;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//hibernate criteria checking 
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

//javax persistence criteria for update
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.utility.PasswordGenerator;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private EntityManagerFactory entityManager;

	/**
	 * 
	 * @param user
	 * @return
	 */
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

	/**
	 * 
	 * link
	 * ->https://www.tutorialspoint.com/hibernate/hibernate_criteria_queries.htm
	 * 
	 * @param user
	 * @return
	 */
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

	/*
	 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-update.html
	 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-update.html
	 * this link used
	 * 
	 * 
	 */

	/**
	 * 
	 * @param user
	 * @return
	 */
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
			System.out.println("dont Match the data");
			return 0;

		} else {
			System.out.println(" macth the data");
			/*
			 * update code by hibernate Session session = sessionFactory.openSession();
			 * Transaction t = session.beginTransaction(); Query query = session.
			 * createQuery("update User set password=:password where emailId = :emailId");
			 * query.setParameter("password", user.getPassword());
			 * query.setParameter("emailId", user.getEmailId()); int i =
			 * query.executeUpdate(); System.out.println(i); t.commit(); session.close();
			 */
			EntityManager em = entityManager.createEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
			Root<User> employeeRoot = criteriaUpdate.from(User.class);
			criteriaUpdate.set(employeeRoot.get("password"), user.getPassword());
			criteriaUpdate.where(criteriaBuilder.equal(employeeRoot.get("emailId"), user.getEmailId()));
			em.getTransaction().begin();
			int i = em.createQuery(criteriaUpdate).executeUpdate();
			System.out.println("Entities updated: " + i);
			em.getTransaction().commit();
			em.close();
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
