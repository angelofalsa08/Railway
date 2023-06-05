package com.rails.railway.service;

import com.rails.railway.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SeatService {

    List<Seat> getSeatSelections(Long trainId);
}
