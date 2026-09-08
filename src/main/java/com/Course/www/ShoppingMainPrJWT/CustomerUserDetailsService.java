package com.Course.www.ShoppingMainPrJWT;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.CustomerRepository;
import com.Course.www.ShoppingMainPr.Main.Customer;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	
	private CustomerRepository customerRepository;
	
	public CustomerUserDetailsService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("USER NAME RECIVED = " + username);
		
		Customer hai = customerRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		System.out.println("USER FOUND = " + hai.getUsername());
		System.out.println("DB PASSWORD = " + hai.getPassword());
		
		return new org.springframework.security.core.userdetails.User(
				hai.getUsername(),
				hai.getPassword(),
				List.of(new SimpleGrantedAuthority(hai.getUserRole())));
	}

}
