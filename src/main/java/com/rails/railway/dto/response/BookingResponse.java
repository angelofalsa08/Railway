package com.rails.railway.dto.response;

import com.rails.railway.model.AdditionalServices;
import com.rails.railway.model.Passenger;
import com.rails.railway.model.Ticket;
import com.rails.railway.model.Train;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Long bookingId;
    private String username;
    private List<Passenger> passengers;
    private List<Ticket> tickets;
    private LocalDate bookingDate;
    private String status;
    private List<AdditionalServices> additionalServices;
    private Train train;
}
