package com.lbn.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lbn.domain.Order;
import com.lbn.domain.Payment;

public class OrderVO {
	
	private int id;
	private int assigneeId;
	private String assigneeName;
	private int createdBy;
	private String createdByName;
	private int customerId;
	private String customerName;
	private String name;
	private String contractId;
	private Date signedAt;
	private Date deliveredDate;
	private int status;
	private BigDecimal netPrice;
	private String note;
	private Date createdAt;
	private Date updatedAt;
	private List<Payment> paymentList;
	
	public OrderVO() {
		
	}
	
	public OrderVO(Order order, List<Payment> paymentList) {
		this.id = order.getId();
		this.assigneeId = order.getAssigneeId();
		this.createdBy = order.getCreatedBy();
		this.customerId = order.getCustomerId();
		this.name = order.getName();
		this.contractId = order.getContractId();
		this.signedAt = order.getSignedAt();
		this.deliveredDate = order.getDeliveredDate();
		this.status = order.getStatus();
		this.netPrice = order.getNetPrice();
		this.note = order.getNote();
		this.createdAt = order.getCreatedAt();
		this.updatedAt = order.getUpdatedAt();
		this.paymentList = paymentList;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Date getSignedAt() {
		return signedAt;
	}
	public void setSignedAt(Date signedAt) {
		this.signedAt = signedAt;
	}
	public Date getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Payment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", assigneeId=" + assigneeId + ", assigneeName=" + assigneeName + ", createdBy="
				+ createdBy + ", createdByName=" + createdByName + ", customerId=" + customerId + ", customerName="
				+ customerName + ", name=" + name + ", contractId=" + contractId + ", signedAt=" + signedAt
				+ ", deliveredDate=" + deliveredDate + ", status=" + status + ", netPrice=" + netPrice + ", note="
				+ note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", paymentList=" + paymentList + "]";
	}
	
}
