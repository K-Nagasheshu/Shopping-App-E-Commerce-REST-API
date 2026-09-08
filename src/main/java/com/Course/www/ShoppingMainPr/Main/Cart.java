package com.Course.www.ShoppingMainPr.Main;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private int cartId;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private int finalamount;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItem;
	
	public Cart() {
		super();
	}
    
	public Cart(int cartId, Customer customer, int finalamount, List<CartItem> cartItem) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.finalamount = finalamount;
		this.cartItem = cartItem;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getFinalamount() {
		return finalamount;
	}

	public void setFinalamount(int finalamount) {
		this.finalamount = finalamount;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", finalamount=" + finalamount + ", cartItem="
				+ cartItem + "]";
	}

}

		