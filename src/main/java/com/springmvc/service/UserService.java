package com.springmvc.service;

import com.springmvc.model.User;

public interface UserService {

	Integer loginUser(User user);

	String create(User user);

	Integer passAndEmailChecking(User user);
	
	

}
