package com.Course.www.ShoppingMainPr.Database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.ReturnRequest;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Integer>{

	
	Optional<ReturnRequest> findByOrderOrderId(int orderId);

}
