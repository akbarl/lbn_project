package com.lbn.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lbn.domain.User;
import com.lbn.domain.UserType;
import com.lbn.repository.UserRepository;
import com.lbn.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	private UserRepository m_userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = m_userRepo.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getUsertype()));
	}
	
	private List<SimpleGrantedAuthority> getAuthority(int userTypeId) {
		
		return Arrays.asList(new SimpleGrantedAuthority(getUserType(userTypeId).getRole()));
	}

	private UserType getUserType(int userTypeId) {
		switch (userTypeId) {
			case 1: return UserType.USER;
			case 2: return UserType.ADMIN;
			case 3: return UserType.SUPER_ADMIN;
			default: return UserType.USER;
		}
	}
	@Override
	public User findByUsername(String username) {
		return m_userRepo.findByUsername(username);
	}

	@Override
	public User findById(int id) {
		return m_userRepo.findById(id);
	}

	@Override
	public Iterable<User> getAllUser() {
		return m_userRepo.findAll();
	}
}
