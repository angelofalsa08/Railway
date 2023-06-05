package com.rails.railway.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rails.railway.model.Fare;
import com.rails.railway.model.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {


    private Train train;
    private int availableSeats;
    private int occupiedSeats;
    private List<Fare> fare;

}
