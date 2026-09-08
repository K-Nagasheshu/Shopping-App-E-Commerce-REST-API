package com.Course.www.ShoppingMainPr.Service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.CartRepository;
import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Main.Cart;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;


@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;
	private CartRepository cartRepository;
	private BCryptPasswordEncoder passwordencoder;
	public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository, BCryptPasswordEncoder passwordencoder) {
		super();
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
		this.passwordencoder = passwordencoder;
	}

	public Customer registercustomer(Customer customer) {
		customer.setPassword(passwordencoder.encode(customer.getPassword()));
		
		Customer hai = customerRepository.save(customer);
		
		Cart updatecart = new Cart();
		updatecart.setCustomer(hai);
		updatecart.setFinalamount(0);
	    cartRepository.save(updatecart);
		return hai;
	}
	

	public Customer getCustomer(int customerid) {
		
		Optional<Customer> foundcostmer = customerRepository.findById(customerid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE"); // checking customer
		
		return foundcostmer.get();
	}


	public Customer updatedname(int customerid, Customer newcustomer) {
		
		Optional<Customer> foundcostmer = customerRepository.findById(customerid);
		if(foundcostmer.isEmpty())
			throw new UserNotFoundException("GIVEN CUSTOMER ID IS NOT AVAILABLE"); // checking customer
		
		Customer hai = foundcostmer.get();
		hai.setName(newcustomer.getName());
		return customerRepository.save(hai);
	}

}
