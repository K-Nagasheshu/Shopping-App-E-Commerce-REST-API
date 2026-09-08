package com.Course.www.ShoppingMainPr.Database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Course.www.ShoppingMainPr.Main.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{


	Optional<OrderItem> findByOrderOrderIdAndProductProductId(
	        int orderId,
	        int productId
	);

	
	List<OrderItem> findByOrderOrderId(int orderId);

}
