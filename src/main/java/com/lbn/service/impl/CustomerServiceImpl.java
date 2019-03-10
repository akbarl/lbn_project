package com.lbn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbn.domain.Customer;
import com.lbn.repository.CustomerRepository;
import com.lbn.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public CustomerRepository m_customerRepo;
	
	@Override
	public Customer findById(int id) {
		return m_customerRepo.findById(id);
	}

	@Override
	public Customer findByPhone(String phone) {
		return m_customerRepo.findByPhone(phone);
	}

	@Override
	public Customer findByName(String name) {
		return m_customerRepo.findByName(name);
	}

	@Override
	public Customer findByCompany(String company) {
		return m_customerRepo.findByCompany(company);
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		return m_customerRepo.save(customer) != null;
	}

	@Override
	public void deleteById(int id) {
		m_customerRepo.deleteById(id);
	}

	@Override
	public Iterable<Customer> getAllCustomer() {
		return m_customerRepo.findAll();
	}
	
}
