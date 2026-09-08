package com.Course.www.ShoppingMainPr.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.OrderItem;
import com.Course.www.ShoppingMainPr.Service.OrderItemService;

@RestController
public class OrderItemController {
	
	public OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemSerive) {
		super();
		this.orderItemService = orderItemSerive;
	}
	
	@GetMapping("/order/{orderid}")
	public List<OrderItem> getAllOrders(@PathVariable int orderid) {
		return orderItemService.seeingallorders(orderid);
	}
	
	@GetMapping("/order/{orderid}/product/{productid}")
	public OrderItem getorder(@PathVariable int orderid, @PathVariable int productid) {
		return orderItemService.seeingrder(orderid, productid);
		
	}

}
