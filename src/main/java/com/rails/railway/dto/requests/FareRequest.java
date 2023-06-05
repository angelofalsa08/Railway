package com.rails.railway.dto.requests;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FareRequest {

    private Long bookingId;
    private String trainNumber;
}
