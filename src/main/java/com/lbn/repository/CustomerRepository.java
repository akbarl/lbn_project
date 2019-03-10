package com.lbn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lbn.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	public Customer findById(int id);
	public Customer findByPhone(String phone);
	public Customer findByName(String name);
	public Customer findByCompany(String company);
}
