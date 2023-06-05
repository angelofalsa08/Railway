package com.rails.railway.service.impl;

import com.rails.railway.model.Seat;
import com.rails.railway.repository.SeatRepository;
import com.rails.railway.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public List<Seat> getSeatSelections(Long trainId) {
        return seatRepository.findSeatsByTrainId(trainId);
    }
}
