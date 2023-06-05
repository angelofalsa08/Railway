package com.rails.railway.service.impl;

import com.rails.railway.dto.response.PaymentResponse;
import com.rails.railway.model.Booking;
import com.rails.railway.model.Payment;
import com.rails.railway.model.Train;
import com.rails.railway.repository.BookingRepository;
import com.rails.railway.repository.PaymentRepository;
import com.rails.railway.repository.TrainRepository;
import com.rails.railway.repository.UserRepository;
import com.rails.railway.service.PaymentService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;


    @Override
    @Transactional
    public PaymentResponse pay(Map<String, Object> requestMap) {
        String username = MapUtils.getString(requestMap, "username","Guest");
        Long bookingId = MapUtils.getLong(requestMap, "bookingId");
        String paymentMethod = MapUtils.getString(requestMap, "paymentMethod");
        String trainNumber = MapUtils.getString(requestMap, "trainNumber");
        Double fareAmount = MapUtils.getDouble(requestMap, "totalFareAmount");

        Train train = trainRepository.findByTrainNumber(trainNumber);
        bookingRepository.updateBookingStatusById("Paid",bookingId);
        Optional<Booking> book = bookingRepository.findById(bookingId);
        if(book.isPresent()){
            Payment payment = Payment.builder()
                    .paymentMethod(paymentMethod)
                    .booking(book.get())
                    .amount(BigDecimal.valueOf(fareAmount))
                    .transactionDate(LocalDateTime.now())
                    .build();
            paymentRepository.save(payment);
        }

        return PaymentResponse.builder()
                .referenceNumber(UUID.randomUUID().toString())
                .train(train)
                .bookingId(bookingId)
                .fareAmountPaid(fareAmount)
                .status("Paid")
                .username(username)
                .paymentMethod(paymentMethod)
                .build();
    }
}
