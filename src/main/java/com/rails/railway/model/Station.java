package com.rails.railway.model;

import javax.persistence.*;

@Entity
@Table(name="station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="station_id")
    private Long id;

    @Column(name="station_name")
    private String name;

    @Column(name="location")
    private String location;
}
