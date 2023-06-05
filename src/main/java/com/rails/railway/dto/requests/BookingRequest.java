package com.rails.railway.dto.requests;

import com.rails.railway.model.Passenger;
import com.rails.railway.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    //Booking Request
    private Long bookingId;
    private String userName;
    private String trainNumber;
    private LocalDate bookingDate;
    private String status;

    //Passenger Details
    private List<Passenger> passengerList;
    /*private String passengerName;
    private int age;
    private String gender;
    private String specialRequests;*/

    //Seat details
    private List<Seat> seatDetails;
   /* private String seatNumber;
    private String trainClass;*/

    //Services
    private List<Map<String,Double>> additionalServices;

}
