package com.Course.www.ShoppingMainPr.Database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByCustomerCustomerId(int customerId);

}