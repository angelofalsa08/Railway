package com.rails.railway.service.impl;

import com.rails.railway.dto.response.Schedule;
import com.rails.railway.model.Fare;
import com.rails.railway.model.Seat;
import com.rails.railway.model.Train;
import com.rails.railway.repository.FareRepository;
import com.rails.railway.repository.SeatRepository;
import com.rails.railway.repository.TrainRepository;
import com.rails.railway.repository.specifications.GenericSpecification;
import com.rails.railway.repository.specifications.SearchCriteria;
import com.rails.railway.repository.specifications.SearchOperation;
import com.rails.railway.service.HomeRailwayService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeRailwayServiceImpl implements HomeRailwayService {
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private FareRepository fareRepository;
    @Autowired
    private SeatRepository seatRepository;
    public List<Schedule> getTrainSchedules(Map<String, Object> requestMap) {
        List<Train> trains = new ArrayList<>();
        if(!requestMap.isEmpty()){
            Optional<String> origin = Optional.ofNullable(MapUtils.getString(requestMap,"origin"));
            Optional<String> destination = Optional.ofNullable(MapUtils.getString(requestMap,"destination"));
            Optional<Object> time = Optional.ofNullable(requestMap.get("date"));
            GenericSpecification<Train> specification = new GenericSpecification<>();
            origin.ifPresent(o -> specification.add(new SearchCriteria("departureStation",o, SearchOperation.EQUAL)));
            destination.ifPresent(d -> specification.add(new SearchCriteria("arrivalStation",d, SearchOperation.EQUAL)));
            time.ifPresent(t -> specification.add(new SearchCriteria("departureTime",t, SearchOperation.EQUAL)));

            trains = trainRepository.findAll(specification);
        }else{
            trains = trainRepository.findAll();
        }

        return trains.stream().map(this::mapModelsToScheduleDTO).collect(Collectors.toList());
    }

    private Schedule mapModelsToScheduleDTO(Train train){

        List<Seat> seat = seatRepository.findSeatsByTrainId(train.getId());
        List<Fare> fare = fareRepository.findFaresByTrainId(train.getId());
        long occupiedSeats = seat.stream().filter(s -> s.getAvailabilityStatus().equalsIgnoreCase("Occupied")).count();
        long availableSeats = seat.stream().filter(s -> s.getAvailabilityStatus().equalsIgnoreCase("Available")).count();

        return Schedule.builder()
                .fare(fare)
                .train(train)
                .occupiedSeats((int) occupiedSeats)
                .availableSeats((int) availableSeats)
                .build();

    }

}
