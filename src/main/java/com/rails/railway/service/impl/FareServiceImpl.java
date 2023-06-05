package com.rails.railway.service.impl;

import com.rails.railway.dto.requests.FareRequest;
import com.rails.railway.dto.response.FareResponse;
import com.rails.railway.exceptions.InvalidFareRequestException;
import com.rails.railway.model.*;
import com.rails.railway.repository.*;
import com.rails.railway.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FareServiceImpl implements FareService {

    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public FareResponse computeFareAndDiscounts(FareRequest fareRequest) throws InvalidFareRequestException {
        if(fareRequest.getBookingId() == null){
            throw new InvalidFareRequestException("Booking id is empty");
        }
        Booking booking = bookingRepository.findById(fareRequest.getBookingId()).orElse(null);
        Optional<User> user = userRepository.findById(booking.getUser().getId());
        Train train = trainRepository.findByTrainNumber(fareRequest.getTrainNumber());
        //Sum up all of additional services
        Double totalPremiumServicePrice = additionalServiceRepository.findAllByBookingId(booking.getId())
                .stream().map(AdditionalServices::getServicePrice).collect(Collectors.summingDouble(BigDecimal::doubleValue));

       /* List<String> seatClassList = seatRepository.findSeatsByTrainId(train.getId()).stream()
                .map(Seat::getSeatClass).collect(Collectors.toList());*/

        List<Passenger> passengers = passengerRepository.findAllByBookingId(booking.getId());
        List<Ticket> tickets = ticketRepository.findByBookingId(booking.getId());
        List<String> seatClassOfPassengers = tickets.stream().map(t -> t.getSeat().getSeatClass()).collect(Collectors.toList());

        //Get Base Fare of train
        List<Fare> fare = fareRepository.findFaresByTrainIdAndSeatClass(train.getId(),seatClassOfPassengers);
        //Calculate and check based on passenger type or age
        return mapFareBookDetailsToFareResponse(fare,passengers,booking.getId(),train.getTrainNumber(),user,totalPremiumServicePrice);
    }

    private FareResponse mapFareBookDetailsToFareResponse(List<Fare> fare, List<Passenger> passengers, Long bookingId,String trainNumber,
                                                          Optional<User> user,Double totalPremiumServicesPrice){
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        for(int i = 0, passengerSize = passengers.size(); i < passengerSize; i++){

            int age = passengers.get(i).getAge();
            if(age < 10){
                BigDecimal subFare = fare.stream().filter(f -> f.getPassengerType().equalsIgnoreCase("Child"))
                        .map(Fare::getFareAmount).findFirst().orElse(BigDecimal.valueOf(0.0));
                BigDecimal discountAmount = BigDecimal.valueOf(subFare.doubleValue() * 0.10);
                totalAmount = totalAmount.add(fare.get(i).getFareAmount().subtract(discountAmount));
            }else if(age>55){
                BigDecimal subFare = fare.stream().filter(f -> f.getPassengerType().equalsIgnoreCase("Senior"))
                        .map(Fare::getFareAmount).findFirst().orElse(BigDecimal.valueOf(0.0));
                BigDecimal discountAmount = BigDecimal.valueOf(subFare.doubleValue() * 0.20);
                totalAmount = totalAmount.add(fare.get(i).getFareAmount().subtract(discountAmount));
            }else if(age>18){
                BigDecimal subFare = fare.stream().filter(f -> f.getPassengerType().equalsIgnoreCase("Adult"))
                        .map(Fare::getFareAmount).findFirst().orElse(BigDecimal.valueOf(0.0));
                totalAmount = totalAmount.add(subFare);
            }else if(passengers.get(i).getSpecialRequests().contains("Special Needs Assistance")){
                BigDecimal subFare = fare.stream().filter(f -> f.getPassengerType().equalsIgnoreCase("PWD"))
                        .map(Fare::getFareAmount).findFirst().orElse(BigDecimal.valueOf(0.0));
                BigDecimal discountAmount = BigDecimal.valueOf(subFare.doubleValue() * 0.15);
                totalAmount = totalAmount.add(fare.get(i).getFareAmount().subtract(discountAmount));
            }
        }

        return FareResponse.builder()
                .bookingId(bookingId)
                .trainNumber(trainNumber)
                .username(user.get().getUsername())
                .totalFareAmount(totalAmount.add(BigDecimal.valueOf(totalPremiumServicesPrice)))
                .build();
    }
}
