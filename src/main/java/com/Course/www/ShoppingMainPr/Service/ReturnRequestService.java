package com.Course.www.ShoppingMainPr.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.OrderRepository;
import com.Course.www.ShoppingMainPr.Database.ReturnRequestRepository;
import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Main.ReturnRequest;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class ReturnRequestService {
	
	private OrderRepository orderRepository;
	private ReturnRequestRepository returnRequestRepository;

	public ReturnRequestService(OrderRepository orderRepository, ReturnRequestRepository returnRequestRepository) {
		super();
		this.orderRepository = orderRepository;
		this.returnRequestRepository = returnRequestRepository;
	}

	public ReturnRequest createnewreturnrequest(int orderid, String returnreason) {
		
		Optional<Order> foundorder = orderRepository.findById(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE"); // findout order
		
		ReturnRequest returnrequest = new ReturnRequest();
		returnrequest.setReturnReason(returnreason);
		returnrequest.setReturnStatus("PENDING");  
		returnrequest.setReturnDate(System.currentTimeMillis());
		
		Order hello = foundorder.get();
		returnrequest.setOrder(hello);
		
		return returnRequestRepository.save(returnrequest);
		
	}

	public ReturnRequest seereturnrequest(int orderid) {
		
		Optional<ReturnRequest> foundorder = returnRequestRepository.findByOrderOrderId(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE IN RETURNREQUEST"); // findout order
		
		return foundorder.get();
	}

	public ReturnRequest updaterequest(int orderid, ReturnRequest newrequest) {
		
		Optional<ReturnRequest> foundorder = returnRequestRepository.findByOrderOrderId(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE IN RETURNREQUEST"); // findout order
		
		ReturnRequest returnrequest = foundorder.get();
		returnrequest.setReturnStatus(newrequest.getReturnStatus());
		
		String hello = newrequest.getReturnReason();
		returnrequest.setReturnReason(hello);
	
		return returnRequestRepository.save(returnrequest);
	}
}
