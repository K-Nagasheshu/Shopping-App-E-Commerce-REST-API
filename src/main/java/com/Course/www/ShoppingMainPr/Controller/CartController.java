package com.Course.www.ShoppingMainPr.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Service.CartService;

@RestController
public class CartController {
	
	
	private CartService cartService;
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@GetMapping("/seecustomer/{customerid}")
	public Cart getCustomer(@PathVariable int customerid) {
		return cartService.getcart(customerid);
	}
	
	@DeleteMapping("/deletecartitem/{cartitemid}")
	public void deltecartitem(@PathVariable int cartitemid) {
		cartService.deleteitem(cartitemid);
	}
}
