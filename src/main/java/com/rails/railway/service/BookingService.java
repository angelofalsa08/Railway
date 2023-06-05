package com.rails.railway.service;

import com.rails.railway.dto.requests.BookingRequest;
import com.rails.railway.dto.response.BookingResponse;

public interface BookingService {
    BookingResponse addBooking(BookingRequest bookingRequest);

    void updateBooking(Long bookingId, BookingRequest bookingRequest);

    void deleteBooking(Long bookingId);
}
