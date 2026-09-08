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

import jakarta.transaction.Transactional;

@Service
public class CartItemService {
	
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	
	public CartItemService(CustomerRepository customerRepository, ProductRepository productRepository,
			CartRepository cartRepository, CartItemRepository cartItemRepository) {
		super();
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;;
		this.cartItemRepository = cartItemRepository;
	}


	@Transactional
	public CartItem addingproductintocart(int productid, int customerID, int quantity) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerID);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE"); // checking customer
		
		Optional<Product> foundproduct = productRepository.findById(productid);
		if(foundproduct.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCT ID IS NOT AVAILBLE"); // checking product
		
		Optional<Cart> foundcart = cartRepository.findByCustomerCustomerId(customerID);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("CART NOT AVAILABLE FOR GIVEN CUSTOMER"); // checking customer cart
		
		CartItem hai = new CartItem();
		hai.setCart(foundcart.get());
		hai.setProduct(foundproduct.get());
		hai.setQuantity(quantity);
		
		int finalamount = 0;
		int hello = foundproduct.get().getProductPrice();
		finalamount = finalamount+hello*quantity;
		
		hai.setPrice(finalamount);
		
		return cartItemRepository.save(hai);
	}


	public CartItem updatesize(int productid, int customerID, int productsize) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerID);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE"); // checking customer
		
		Optional<Product> foundproduct = productRepository.findById(productid);
		if(foundproduct.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCT ID IS NOT AVAILABLE"); // checking product
		
		Optional<Cart> foundcart = cartRepository.findByCustomerCustomerId(customerID);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE IN CARTS"); // checking customer cart
		
		int hai = foundcart.get().getCartId();
		
		List<CartItem> foundcartitem = cartItemRepository.findByCartCartId(hai);
		if(foundcartitem.isEmpty())
			throw new UserNotFoundException("GIVEN CART ID IS NOT AVAILABLE"); // checking customer cart
		
		for (CartItem items : foundcartitem) {
			if (items.getProduct().getProductId() == productid) {
				items.setProductSize(productsize);
				return cartItemRepository.save(items);
			}
		}
		throw new UserNotFoundException("");
	}


	@Transactional
	public void deleteProduct(int productid, int customerID) {
		
		Optional<Customer> foundcustomer = customerRepository.findById(customerID);
		if(foundcustomer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAIBALE"); // checking customer
		
		Optional<Cart> foundcart = cartRepository.findByCustomerCustomerId(customerID);
		if(foundcart.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE IN CARTS"); // checking customer cart
		
		int hai = foundcart.get().getCartId();
		
		Optional<Product> foundproduct = productRepository.findById(productid);
		if(foundproduct.isEmpty())
			throw new UserNotFoundException("GIVEN PRODUCT ID IS NOT AVAILABLE"); // checking product
		
		cartItemRepository.deleteByCartCartIdAndProductProductId(hai,productid);
	}

	

}
