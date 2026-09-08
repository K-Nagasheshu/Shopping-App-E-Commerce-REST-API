package com.Course.www.ShoppingMainPr.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Service.OrderService;

@RestController
public class OrderController {
	
	public OrderService orderService;
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

    
	@PostMapping("createneworder/{customerid}")
	public Order createneworder(@PathVariable int customerid) {
		return orderService.addingnewone(customerid);
	}
	
	@GetMapping("showorders/{customerid}")
	public List<Order> showorders(@PathVariable int customerid) {
		return orderService.showmyorders(customerid);
	}
	
	@PutMapping("/updateorder/{customerid}/orders/{orderid}/updatemessage")
	public Order updateorder(@PathVariable int customerid, @PathVariable int orderid, @RequestBody Order updateorder) {
		return orderService.updatestatus(customerid, orderid, updateorder);
		
	}
	

}
