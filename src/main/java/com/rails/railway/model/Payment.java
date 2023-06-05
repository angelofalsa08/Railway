package com.rails.railway.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Booking booking;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="transaction_date")
    private LocalDateTime transactionDate;
}
