package com.lbn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lbn.domain.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{
	public Payment findById(int id);
	public List<Payment> findAllByOrderId(int orderId);
}
