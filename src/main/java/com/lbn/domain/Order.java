package com.lbn.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.lbn.client.OrderVO;

@Entity
@Table(name = "orders")
@SecondaryTable(name = "payment", pkJoinColumns={@PrimaryKeyJoinColumn(name="order_id", referencedColumnName="id")})
public class Order {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "assignee_id")
	private int assigneeId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "customer_id")
	private int customerId;
	@Column
	private String name;
	@Column(name = "contract_id")
	private String contractId;
	@Column(name = "signed_at")
	private Date signedAt;
	@Column(name = "delivered_date")
	private Date deliveredDate;
	@Column
	private int status;
	@Column(name = "net_price")
	private BigDecimal netPrice;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Set<Payment> paymentList;
	@Column
	private String note;
	@Column(name = "created_at", updatable = false)
	private Date createdAt;
	@Column(name = "updated_at", updatable = false)
	private Date updatedAt;
	
	public Order(OrderVO order) {
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
	}
	
	public Order() {
		
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", assigneeId=" + assigneeId + ", createdBy=" + createdBy + ", customerId="
				+ customerId + ", name=" + name + ", contractId=" + contractId + ", signedAt=" + signedAt
				+ ", deliveredDate=" + deliveredDate + ", status=" + status + ", netPrice=" + netPrice
				+ ", paymentList=" + getPaymentList() + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	public Set<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(Set<Payment> paymentList) {
		this.paymentList = paymentList;
	}
}
