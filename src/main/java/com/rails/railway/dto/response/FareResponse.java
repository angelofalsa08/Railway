package com.rails.railway.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FareResponse {

    private Long bookingId;
    private String username;
    private String trainNumber;
    private BigDecimal totalFareAmount;
}
