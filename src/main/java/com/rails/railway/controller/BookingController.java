package com.rails.railway.controller;

import com.rails.railway.dto.requests.BookingRequest;
import com.rails.railway.dto.response.BookingResponse;
import com.rails.railway.model.Booking;
import com.rails.railway.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> addBooking(@RequestBody BookingRequest bookingRequest){

        BookingResponse response = bookingService.addBooking(bookingRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/book/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody BookingRequest bookingRequest){
        bookingService.updateBooking(bookingId, bookingRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable("bookingId") Long bookingId){
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
