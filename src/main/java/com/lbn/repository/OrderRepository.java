package com.lbn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lbn.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	public Order findById(int id);
}
