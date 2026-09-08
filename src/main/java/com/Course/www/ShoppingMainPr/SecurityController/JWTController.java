package com.Course.www.ShoppingMainPr.SecurityController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Main.Customer;
import com.Course.www.ShoppingMainPr.Main.LoginRequest;
import com.Course.www.ShoppingMainPr.Service.CustomerService;
import com.Course.www.ShoppingMainPrJWT.CreateToken;
import com.Course.www.ShoppingMainPrJWT.JWTResponse;


@RestController
public class JWTController {
	
	    public AuthenticationManager authenticationManager;
	    public CreateToken createToken;
	    private BCryptPasswordEncoder passwordEncoder;
	    public CustomerService customerService;
	    public CustomerRepository customerRepository;
	    
		public JWTController(AuthenticationManager authenticationManager, CreateToken createToken,
				BCryptPasswordEncoder passwordEncoder, CustomerService customerService,
				CustomerRepository customerRepository) {
			super();
			this.authenticationManager = authenticationManager;
			this.createToken = createToken;
			this.passwordEncoder = passwordEncoder;
			this.customerService = customerService;
			this.customerRepository = customerRepository;
			
		}
	    
		@PostMapping("/register")
		public Customer register(@RequestBody Customer customer) {
			return customerService.registercustomer(customer);
			
		}
		
		@PostMapping("/auth")
		public JWTResponse login(@RequestBody LoginRequest loginRequest) {
			
			Customer customer = customerRepository.findByUsername(
	    	        loginRequest.getName()
	    	).orElseThrow();
	       
			
			System.out.println("GET USER NAME : " + customer.getUsername());
			System.out.println("GET PASSWORD IS : " + passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword()));
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getName(),
							loginRequest.getPassword()
					)
					);
			
			return createToken.authenticate(authentication);
			
		}
}
