package com.springmvc.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;

@Service
public class ServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public Integer loginUser(User user) {
		
		return userDao.getUserDetail(user);

	}

	@Transactional
	public String create(User user) {
		return userDao.saveUser(user);
	}

	public Integer passAndEmailChecking(User user) {
		return userDao.validatingPassMail(user);
	}

}