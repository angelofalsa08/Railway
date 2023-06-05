package com.rails.railway.repository;

import com.rails.railway.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FareRepository extends JpaRepository<Fare, Long> {

    @Query("FROM Fare f WHERE f.train.id = ?1")
    List<Fare> findFaresByTrainId(Long trainId);

    @Query("FROM Fare f WHERE f.train.id =?1 AND f.trainClass IN(?2)")
    List<Fare> findFaresByTrainIdAndSeatClass(Long trainId,List<String> seatClass);
}
