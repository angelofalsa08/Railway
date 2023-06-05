package com.rails.railway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="passenger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="passenger_id")
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Booking booking;

    private String name;
    private int age;
    private String gender;
    private String specialRequests;

}
