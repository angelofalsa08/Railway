package com.rails.railway.repository;

import com.rails.railway.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query("FROM Seat s WHERE s.train.id=?1")
    List<Seat> findSeatsByTrainId(Long trainId);

    @Query("FROM Seat s WHERE s.train.id=?1 AND s.seatNumber IN(?2)")
    List<Seat> findAvailedSeatsByTrainIdAndSeatNumber(Long trainId, List<String> seatNumber);
}
