package com.truf.reservation.TrufReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.truf.reservation.TrufReservation.Entity.User;

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

    private String customer_name;

    @ManyToOne
    @JoinColumn(name = "truf")
    private Turf turf;

    private int slot;

    private String date;

    private String game;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TrufSLot_id")
    private TurfSlots turfSlots;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer_name='" + customer_name + '\'' +
                ", slot=" + slot +
                ", date='" + date + '\'' +
                ", game='" + game + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}