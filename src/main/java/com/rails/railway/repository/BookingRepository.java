package com.rails.railway.repository;

import com.rails.railway.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("Update Booking b SET b.status=?1 WHERE b.id=?2")
    @Modifying
    void updateBookingStatusById(String status, Long bookingId);
}
