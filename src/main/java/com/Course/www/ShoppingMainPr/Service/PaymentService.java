package com.Course.www.ShoppingMainPr.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Course.www.ShoppingMainPr.Database.OrderRepository;
import com.Course.www.ShoppingMainPr.Database.PaymentRepository;
import com.Course.www.ShoppingMainPr.Main.Order;
import com.Course.www.ShoppingMainPr.Main.Payment;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@Service
public class PaymentService {

	private PaymentRepository paymentRepository;
	private OrderRepository orderRepository;
	public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
		super();
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;
	}

	public Payment makeorderpayment(int orderid, Payment googlepay) {
		
		Optional<Order> foundorder = orderRepository.findById(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE");
		
		Order order = foundorder.get();
		
		Optional<Payment> foundorders = paymentRepository.findByOrderOrderId(orderid);
		if(foundorders.isPresent())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE IN PAYMENTS");
		
		
		order.setOrderStatus("PLACED");
		
		Payment pay = new Payment();
		pay.setPaymentMethod(googlepay.getPaymentMethod());
		pay.setPaymentStatus("success");
		pay.setPaymentDate(System.currentTimeMillis());
		pay.setOrder(order);

		orderRepository.save(order);
		
		return paymentRepository.save(pay);
	}

	public Payment seepayment(int orderid) {
		
		Optional<Payment> foundorder = paymentRepository.findByOrderOrderId(orderid);
		if(foundorder.isEmpty())
			throw new UserNotFoundException("GIVEN ORDERID IS NOT AVAILABLE IN PAYMENTS");
		
		
		return foundorder.get();
	}

}
