package com.lbn.service;

import com.lbn.domain.User;

public interface UserService {
	public User findByUsername(String username);
	public User findById(int id);
	public Iterable<User> getAllUser();
}
