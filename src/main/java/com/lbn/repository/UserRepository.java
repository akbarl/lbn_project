package com.lbn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lbn.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
	User findById(int id);
}
