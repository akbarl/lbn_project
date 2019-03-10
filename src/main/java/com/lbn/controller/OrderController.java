package com.lbn.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbn.client.OrderVO;
import com.lbn.domain.Order;
import com.lbn.domain.Payment;
import com.lbn.service.CustomerService;
import com.lbn.service.OrderService;
import com.lbn.service.PaymentService;
import com.lbn.service.UserService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService m_orderService;
	
	@Autowired
	private PaymentService m_paymentService;
	
	@Autowired
	private CustomerService m_customerService;
	
	@Autowired
	private UserService m_userService;
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable("id") int id) {
		System.out.println(m_orderService.findById(id));
		return m_orderService.findById(id);
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> createOrder(@RequestBody OrderVO orderVO) {
		Order order = new Order(orderVO);
		order.setPaymentList(new HashSet<Payment>(orderVO.getPaymentList()));
		m_orderService.saveOrder(order);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Iterable<OrderVO> getAllOrder(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "order", required = false) String orderBy,
			@RequestParam(value = "page", required = false) int page) {
		
		List<OrderVO> orderVoList = new ArrayList<OrderVO>();
		Set<Order> orderList = new HashSet<Order>(m_orderService.findAllOrder());
		for(Order order : orderList) {
			OrderVO orderVo = new OrderVO(order, m_paymentService.findAllByOrderId(order.getId()));
			orderVo.setCustomerName(m_customerService.findById(orderVo.getCustomerId()).getName());
			orderVo.setAssigneeName(m_userService.findById(orderVo.getAssigneeId()).getFirstname());
			orderVo.setCreatedByName(m_userService.findById(orderVo.getCreatedBy()).getFirstname());
			orderVoList.add(orderVo);
		}
		return orderVoList;
	}
}
