package com.Course.www.ShoppingMainPr.Main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private int totalAmount;
	private String orderStatus;
	private long orderDate;
	
	public Order() {
		super();
	}
	public Order(int orderId, Customer customer, int totalAmount, String orderStatus, long orderDate) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", totalAmount=" + totalAmount
				+ ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + "]";
	}

	
}
