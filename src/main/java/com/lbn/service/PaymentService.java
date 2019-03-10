package com.lbn.service;

import java.util.List;

import com.lbn.domain.Payment;

public interface PaymentService {
	public boolean savePayment(Payment payment);
	public boolean saveBulkPayment(List<Payment> payments);
	public Payment findById(int id);
	public List<Payment> findAllByOrderId(int orderId);
	public void deleteByOrderId(int orderId);
}
