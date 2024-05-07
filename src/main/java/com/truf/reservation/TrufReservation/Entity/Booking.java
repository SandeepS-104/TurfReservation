package com.truf.reservation.TrufReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "Booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user_name;

    @ManyToOne
    @JoinColumn(name = "truf")
    private Turf turf;

    private int slot;

    private String date;

    private String game;

    @ManyToOne
    @JoinColumn(name = "TrufSLot_id")
    private TurfSlots turfSlots;


}