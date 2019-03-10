package com.lbn.domain;

public enum OrderStatus {
	OPEN(1, "OPEN"),
	IN_PROGRESS(2, "IN_PROGRESS"),
	IN_DELIVERY(3, "IN_DELIVERY"),
	DELIVERED(4, "DELIVERED"),
	UNPAID(5, "UNPAID"),
	WAITING_FOR_PAYMENT(6, "WAITING_FOR_PAYMENT"),
	PAID(7, "PAID"),
	COMPLETED(8, "COMPLETED");
	
	
	private int orderStatusId;
	private String role;

	private OrderStatus(int orderStatusId, String role) {
		this.orderStatusId = orderStatusId;
		this.role = role;
	}
	
	public int getOrderStatus() {
		return orderStatusId;
	}
	
	public String getRole() {
		return role;
	}
}
