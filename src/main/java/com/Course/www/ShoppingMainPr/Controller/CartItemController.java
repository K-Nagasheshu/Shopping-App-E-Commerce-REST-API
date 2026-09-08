package com.Course.www.ShoppingMainPr.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.CartItem;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Service.CartItemService;

@RestController
public class CartItemController {
	
	
	private CartItemService cartItemService;
	public CartItemController(CartItemService cartItemService) {
		super();
		this.cartItemService = cartItemService;
	}

	@PostMapping("/addingproduct/{productid}/customer/{customerID}/quantity/{quantity}")
	public CartItem addingintoCart(@PathVariable int productid, @PathVariable int  customerID, @PathVariable int quantity) {
		
		System.out.println("======================= CART ITEM CONTROL ==========================");
		System.out.println("productid = " + productid);
	    System.out.println("customerID = " + customerID);
	    System.out.println("quantity = " + quantity);
	    
		return cartItemService.addingproductintocart(productid, customerID, quantity);
	}
	
	@PutMapping("/updateproduct/{productid}/customer/{customerID}/size/{productsize}")
	public CartItem updateproduct(@PathVariable int productid, @PathVariable int  customerID, @PathVariable int productsize) {
		return cartItemService.updatesize(productid, customerID, productsize);
	}
	
	@DeleteMapping("/deleteproduct/{productid}/customer/{customerID}")
	public void deleteproduct(@PathVariable int productid, @PathVariable int  customerID) {
		cartItemService.deleteProduct(productid, customerID);
	}
}
