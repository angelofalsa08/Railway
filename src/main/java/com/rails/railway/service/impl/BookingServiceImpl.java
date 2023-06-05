package com.rails.railway.service.impl;

import com.rails.railway.dto.requests.BookingRequest;
import com.rails.railway.dto.response.BookingResponse;
import com.rails.railway.model.*;
import com.rails.railway.repository.*;
import com.rails.railway.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Value("${railway.uploadPath}")
    private String uploadPath;
    @Override
    @Transactional
    public BookingResponse addBooking(BookingRequest bookingRequest) {
        //Get user id by username
        User user = userRepository.findByUsername(bookingRequest.getUserName());
        //Get Train by train number
        Train train = trainRepository.findByTrainNumber(bookingRequest.getTrainNumber());
        //Get Seats availed by the user on that train and then update its availability
        List<Seat> availedSeats = seatRepository.findAvailedSeatsByTrainIdAndSeatNumber(train.getId(),
                bookingRequest.getSeatDetails().stream().map(Seat::getSeatNumber).collect(Collectors.toList()));

        for(Seat s: availedSeats){
            s.setAvailabilityStatus("Occupied");
        }
        seatRepository.saveAllAndFlush(availedSeats);

        //Create Booking entity based on booking request details
        Booking booked = mapBookingRequestDetailsToBooking(user,train,"Pending");
        bookingRepository.saveAndFlush(booked);

        //Get all Passenger List and create ticket for each passenger based on passenger List Count
        //Save Passenger Details
        List<Passenger> passengers = bookingRequest.getPassengerList();
        passengers.forEach(p -> p.setBooking(booked));
        passengerRepository.saveAllAndFlush(passengers);

        List<Ticket> tickets = new ArrayList<>(passengers.size());
        DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = yyyyMMddFormatter.format(LocalDate.now());

        for (Seat availedSeat : availedSeats) {
            Ticket ticket = new Ticket();
            ticket.setBooking(booked);
            ticket.setSeat(availedSeat);
            String ticketNum = UUID.randomUUID().toString();
            ticket.setTicketNumber(ticketNum);
            String ticketFilePath = new StringBuilder().append(user.getUsername()).append("/")
                    .append(now).append("/")
                    .append(ticketNum).toString();
            ticket.setTicketFilePath(ticketFilePath);
            //Create PDF File for Ticket
            // File dr = new File(uploadPath + ticketFilePath);
            /*if(!dr.exists()){
                dr.mkdirs();
            }
            try(FileOutputStream fileOutputStream = new FileOutputStream(new File(ticketFilePath + ticketFolder, fileName))){

            }catch (Exception error){
                log.info("Error: " + error);
            }*/
            tickets.add(ticket);
        }
        ticketRepository.saveAllAndFlush(tickets);
        List<AdditionalServices> services = null;
        if(!bookingRequest.getAdditionalServices().isEmpty()){
            services = new ArrayList<>(bookingRequest.getAdditionalServices().size());

            List<String> servicesKeys = bookingRequest.getAdditionalServices().stream()
                    .flatMap(m -> m.keySet().stream()).collect(Collectors.toList());

            for(int i = 0, listSize = servicesKeys.size(); i < listSize; i++){
                AdditionalServices additionalServices = new AdditionalServices();
                String key = servicesKeys.get(i);
                additionalServices.setBooking(booked);
                additionalServices.setServiceName(key);
                Double price = bookingRequest.getAdditionalServices().get(i).get(key);
                additionalServices.setServicePrice(BigDecimal.valueOf(price));
                services.add(additionalServices);
            }
            additionalServiceRepository.saveAllAndFlush(services);
        }
        //Create Booking Response with the Train, Ticket details, Passengers list and their seat number
        return mapBookingToBookingResponse(booked,user.getUsername(),services,passengers,tickets,train);
    }

    @Override
    public void updateBooking(Long bookingId, BookingRequest bookingRequest) {

    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    private Booking mapBookingRequestDetailsToBooking(User user,Train train,String status){
        return Booking.builder()
                .status(status)  //Add initial status of booking into Pending other status can be "Pending, Paid, Onboarded, Done"
                .user(user)
                .train(train)
                .bookingDate(LocalDate.now())
                .build();
    }

    private BookingResponse mapBookingToBookingResponse(Booking booking, String username,List<AdditionalServices> additionalServices
                            ,List<Passenger> passengers,List<Ticket> tickets,Train train){
        return BookingResponse.builder()
                .bookingDate(booking.getBookingDate())
                .additionalServices(additionalServices)
                .bookingId(booking.getId())
                .username(username)
                .status(booking.getStatus())
                .train(train)
                .passengers(passengers)
                .tickets(tickets)
                .build();
    }
}
