package com.Course.www.ShoppingMainPr.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Course.www.ShoppingMainPr.Main.ReturnRequest;
import com.Course.www.ShoppingMainPr.Service.ReturnRequestService;

@RestController
public class ReturnRequestController {
	
	private ReturnRequestService returnRequestService;

	public ReturnRequestController(ReturnRequestService returnRequestService) {
		super();
		this.returnRequestService = returnRequestService;
	}
	
	@PostMapping("/addreturn/{orderid}")
	public ReturnRequest createreturn(@PathVariable int orderid, @RequestParam String returnreason) {
		return returnRequestService.createnewreturnrequest(orderid, returnreason);
	}
	
	@GetMapping("/seereturn/{orderid}")
	public ReturnRequest seerequests(@PathVariable int orderid) {
		return returnRequestService.seereturnrequest(orderid);
	}
	
	@PutMapping("/updatereturn/{orderid}")
	public ReturnRequest updaterequest(@PathVariable int orderid, @RequestBody ReturnRequest newrequest) {
		return returnRequestService.updaterequest(orderid, newrequest);
		
	}

}
