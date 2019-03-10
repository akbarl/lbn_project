package com.lbn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lbn.domain.User;
import com.lbn.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService m_userService;
	
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	public User getCurrentUser(Authentication authen) {
		return m_userService.findByUsername(authen.getName());
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Iterable<User> getAllUser() {
		return m_userService.getAllUser();
	}
}
