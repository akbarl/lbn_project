package com.lbn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbn.domain.Payment;
import com.lbn.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService m_paymentService;
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Void> createPayment(@RequestBody List<Payment> payment) {
		m_paymentService.saveBulkPayment(payment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public Iterable<Payment> getAllPayment(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "order", required = false) String orderBy,
			@RequestParam(value = "page", required = false) int page) {
		
		if (orderBy.equalsIgnoreCase("orderId")) {
			if (filter != "") {
				return m_paymentService.findAllByOrderId(Integer.parseInt(filter));
			}
		}
		return m_paymentService.findAllOrder();
	}
}
