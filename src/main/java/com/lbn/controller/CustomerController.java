package com.lbn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbn.domain.Customer;
import com.lbn.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService m_customerService;
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") int id) {
		return m_customerService.findById(id);
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
		if(m_customerService.saveCustomer(customer)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") int id) {
		m_customerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomer(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "page", required = false) int page) {
		return m_customerService.getAllCustomer();
	}
}
