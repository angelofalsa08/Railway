package com.rails.railway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name ="train")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="train_id")
    @JsonIgnore
    private Long id;

    @Column(name="train_number",nullable = false)
    private String trainNumber;

    @Column(name="train_name",nullable = false)
    private String name;

    @Column(name="departure_station",nullable = false)
    private String departureStation;

    @Column(name="arrival_station",nullable = false)
    private String arrivalStation;

    @Column(name="departure_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    @Column(name="arrival_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

}
