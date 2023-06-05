package com.rails.railway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="additionalservices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="service_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Booking booking;

    @Column(name="service_name")
    private String serviceName;

    @Column(name="service_price")
    private BigDecimal servicePrice;
}
