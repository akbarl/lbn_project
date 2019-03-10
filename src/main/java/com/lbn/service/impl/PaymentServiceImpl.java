package com.lbn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbn.domain.Payment;
import com.lbn.repository.PaymentRepository;
import com.lbn.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository m_paymentRepo;
	
	@Override
	public boolean savePayment(Payment payment) {
		return m_paymentRepo.save(payment) != null;
	}

	@Override
	public boolean saveBulkPayment(List<Payment> payments) {
		for (Payment payment : payments) {
			savePayment(payment);
		}
		return true;
	}

	@Override
	public Payment findById(int id) {
		return null;
	}

	@Override
	public List<Payment> findAllByOrderId(int orderId) {
		return m_paymentRepo.findAllByOrderId(orderId);
	}

	@Override
	public void deleteByOrderId(int orderId) {
		m_paymentRepo.deleteByOrderId(orderId);
	}
}
