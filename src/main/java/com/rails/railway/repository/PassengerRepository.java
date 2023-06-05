package com.rails.railway.repository;

import com.rails.railway.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    @Query("FROM Passenger p WHERE p.booking.id =?1")
    List<Passenger> findAllByBookingId(Long id);
}
