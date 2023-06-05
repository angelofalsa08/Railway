package com.rails.railway.repository;

import com.rails.railway.model.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalServices,Long> {
    @Query("FROM AdditionalServices ase WHERE ase.booking.id =?1")
    List<AdditionalServices> findAllByBookingId(Long bookingId);
}
