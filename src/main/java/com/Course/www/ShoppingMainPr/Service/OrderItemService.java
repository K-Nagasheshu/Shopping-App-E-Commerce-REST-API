package com.Course.www.ShoppingMainPr.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.OrderItemRepository;
import com.Course.www.ShoppingMainPr.Database.ProductRepository;
import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Main.OrderItem;
import com.Course.www.ShoppingMainPr.Main.Product;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class OrderItemService {
	
	private OrderItemRepository orderItemRepository;
	private ProductRepository productRepository;
	

	public OrderItemService(OrderItemRepository orderItemRepository, ProductRepository productRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
		this.productRepository = productRepository;
	}


	public List<OrderItem> seeingallorders(int orderid) {
		List<OrderItem> foundorders = orderItemRepository.findByOrderOrderId(orderid);
		if(foundorders.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID ORDERITEMS ARE NOT AVAIBLABLE IN ODERITEM");
		
		return foundorders;
	}


	public OrderItem seeingrder(int orderid, int productid) {
		
		Optional<OrderItem> foundorder = orderItemRepository.findByOrderOrderIdAndProductProductId(orderid, productid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID AND PRODUCTID IS NOT AVAIBLE IN ODERITEM");
		
		
		return foundorder.get();
		
	}

}
