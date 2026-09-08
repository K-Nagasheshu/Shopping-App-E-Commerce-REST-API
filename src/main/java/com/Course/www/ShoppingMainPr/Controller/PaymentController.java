package com.Course.www.ShoppingMainPr.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.Payment;
import com.Course.www.ShoppingMainPr.Service.PaymentService;

@RestController
public class PaymentController {
	
	
	
	public PaymentService paymentService;
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@PostMapping("/payment/{orderid}/googlepay")
	public Payment makepayment(@PathVariable int orderid, @RequestBody Payment googlepay) {
		return paymentService.makeorderpayment(orderid, googlepay);
	}
	
	@GetMapping("/viewpayment/{orderid}")
	public Payment viewpayment(@PathVariable int orderid) {
		return paymentService.seepayment(orderid);
		
	}

}
