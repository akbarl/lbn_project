package com.lbn.service;

import java.util.List;
import java.util.Set;

import com.lbn.domain.Order;

public interface OrderService {
	public Order findById(int id);
	public int saveOrder(Order order);
	public void deleteById(int id);
	public Set<Order> findAllOrder();
}
