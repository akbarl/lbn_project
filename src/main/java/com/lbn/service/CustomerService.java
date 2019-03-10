package com.lbn.service;

import com.lbn.domain.Customer;

public interface CustomerService {
	public Customer findById(int id);
	public Customer findByPhone(String phone);
	public Customer findByName(String name);
	public Customer findByCompany(String company);
	public boolean saveCustomer(Customer customer);
	public void deleteById(int id);
	public Iterable<Customer> getAllCustomer();
}
