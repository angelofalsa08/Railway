package com.rails.railway.service.impl;

import com.rails.railway.model.Train;
import com.rails.railway.repository.TrainRepository;
import com.rails.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;
    @Override
    public void addTrain(Train train) {
        train.setTrainNumber(UUID.randomUUID().toString());
        trainRepository.save(train);
    }
}
