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
@Table(name="fare")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fare_id")
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Train train;

    @Column(name="class")
    private String trainClass;

    @Column(name="passenger_type")
    private String passengerType;

    @Column(name="fare_amount")
    private BigDecimal fareAmount;

}
