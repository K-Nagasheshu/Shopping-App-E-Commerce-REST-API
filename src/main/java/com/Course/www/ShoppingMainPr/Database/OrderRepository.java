package com.Course.www.ShoppingMainPr.Database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByCustomerCustomerId(int customerId);

}
