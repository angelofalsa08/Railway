package com.rails.railway.repository;

import com.rails.railway.model.Train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, Long>, JpaSpecificationExecutor<Train> {
    @Query("FROM Train t WHERE t.trainNumber=?1")
    Train findByTrainNumber(String trainNumber);
}
