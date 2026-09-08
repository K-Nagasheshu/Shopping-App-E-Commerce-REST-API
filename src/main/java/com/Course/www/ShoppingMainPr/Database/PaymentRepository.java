package com.Course.www.ShoppingMainPr.Database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	
	Optional<Payment> findByOrderOrderId(int orderId);

}
