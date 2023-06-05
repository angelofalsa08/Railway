package com.rails.railway.repository;

import com.rails.railway.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("FROM Ticket t WHERE t.booking.id =?1")
    List<Ticket> findByBookingId(Long id);
}
