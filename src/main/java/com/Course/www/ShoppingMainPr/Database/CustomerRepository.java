package com.Course.www.ShoppingMainPr.Database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByUsername(String username);

}
