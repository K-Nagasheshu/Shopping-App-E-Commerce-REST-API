package com.Course.www.ShoppingMainPr.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.Course.www.ShoppingMainPr.Database.CartItemRepository;
import com.Course.www.ShoppingMainPr.Database.CartRepository;
import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Database.ProductRepository;
import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Main.CartItem;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class CartService {
	
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;

	public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
			ProductRepository productRepository, CartItemRepository cartItemRepository) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
	}


	public Cart getcart(int customerid) {
		
		Optional<Cart> foundcart = cartRepository.findByCustomerCustomerId(customerid);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMERID CART IS NOT AVAILBALE"); // checking customer
		
		int cartId = foundcart.get().getCartId();
		
		List<CartItem> foundcartitems = cartItemRepository.findByCartCartId(cartId);
		if(foundcartitems.isEmpty())
			throw new UserNotFoundException("GIVEN CARTID CARTITEMS ARE NOT FOUND"); // checking customer
		
		foundcart.get().setCartItem(foundcartitems);
		
		int finalamount = 0;
		for (CartItem item : foundcartitems) {
			int bye = item.getPrice();
			finalamount = finalamount + bye;
		}
		foundcart.get().setFinalamount(finalamount);
		Cart hello = foundcart.get();
				
		return cartRepository.save(hello);
		
	} 


	public void deleteitem(int cartitemid) {
		
		Optional<CartItem> foundcart = cartItemRepository.findById(cartitemid);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("GIVEN CARTITEM ID IS NOT AVAILBLE"); 
		
		
		cartItemRepository.deleteById(cartitemid);
	}



	

	

}
