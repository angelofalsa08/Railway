package com.rails.railway.dto.response;

import com.rails.railway.model.Train;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {

    private String status;
    private String referenceNumber;
    private Train train;
    private String paymentMethod;
    private Long bookingId;
    private String username;
    private Double fareAmountPaid;
}
