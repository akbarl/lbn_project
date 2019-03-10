package com.lbn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lbn.domain.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{
	public Payment findById(int id);
	public List<Payment> findAllByOrderId(int orderId);
	@Modifying
	@Query(value = "DELETE FROM payment WHERE order_id = :orderId", nativeQuery = true)
	@Transactional
	public void deleteByOrderId(@Param(value = "orderId") int orderId);
}
