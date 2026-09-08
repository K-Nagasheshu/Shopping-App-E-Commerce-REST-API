package com.Course.www.ShoppingMainPr.Main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ReturnRequest {
	
	@Id
	@GeneratedValue
	private int returnRequestId;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	private String returnReason;
	private String returnStatus;
	private long returnDate;
	
	public ReturnRequest() {
		super();
	}
	public ReturnRequest(int returnRequestId, Order order, String returnReason, String returnStatus, long returnDate) {
		super();
		this.returnRequestId = returnRequestId;
		this.order = order;
		this.returnReason = returnReason;
		this.returnStatus = returnStatus;
		this.returnDate = returnDate;
	}
	public int getReturnRequestId() {
		return returnRequestId;
	}
	public void setReturnRequestId(int returnRequestId) {
		this.returnRequestId = returnRequestId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public long getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(long returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "ReturnRequest [returnRequestId=" + returnRequestId + ", order=" + order + ", returnReason="
				+ returnReason + ", returnStatus=" + returnStatus + ", returnDate=" + returnDate + "]";
	}
	
	

}
