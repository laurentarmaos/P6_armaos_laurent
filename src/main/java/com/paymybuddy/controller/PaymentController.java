package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;

import com.paymybuddy.service.PaymentService;

@Controller
public class PaymentController {

	private final PaymentService service;
	
	public PaymentController(PaymentService service) {
		this.service = service;
	}
	
	
}
