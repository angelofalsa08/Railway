package com.rails.railway.service;

import com.rails.railway.dto.response.PaymentResponse;

import java.util.Map;

public interface PaymentService {
    PaymentResponse pay(Map<String, Object> requestMap);
}
