package com.rails.railway.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Booking booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seat_number")
    private Seat seat;

    @Column(name="ticket_number")
    private String ticketNumber;

    @Column(name="ticket_file_path")
    private String ticketFilePath;
}
