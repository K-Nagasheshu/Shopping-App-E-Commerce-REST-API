package com.Course.www.ShoppingMainPr.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Service.CustomerService;

@RestController
public class CustomerController {
	
	
	private CustomerService customerService;
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

		
		// get particular customer based on customer id
		@GetMapping("/customer/{customerid}")
		public Customer getCustomerbasedonId(@PathVariable int customerid) {
			return customerService.getCustomer(customerid);
		}
		
		@PutMapping("/customer/{customerid}")
		public Customer updateCusomerName(@PathVariable int customerid, @RequestBody Customer newcustomer) {
			return customerService.updatedname(customerid, newcustomer);
		}
}