package com.lbn.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbn.domain.Order;
import com.lbn.repository.OrderRepository;
import com.lbn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository m_orderRepo;
	@Override
	public Order findById(int id) {
		return m_orderRepo.findById(id);
	}

	@Override
	public int saveOrder(Order order) {
		return m_orderRepo.save(order).getId();
	}

	@Override
	public void deleteById(int id) {
		m_orderRepo.deleteById(id);
	}

	@Override
	public Set<Order> findAllOrder() {
		return new HashSet<Order>((Collection<? extends Order>) m_orderRepo.findAll());
	}

}
