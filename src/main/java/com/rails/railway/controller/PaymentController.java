package com.rails.railway.controller;

import com.rails.railway.dto.response.PaymentResponse;
import com.rails.railway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> payBookedTicket(@RequestBody Map<String,Object> requestMap){
        PaymentResponse paid = paymentService.pay(requestMap);

        return new ResponseEntity<>(paid, HttpStatus.OK);
    }
}
