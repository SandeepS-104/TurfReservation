package com.truf.reservation.TrufReservation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "turf")
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private String address;
    private String contact;
    private String description;
    private int no_of_slots;
    private int no_of_days_available;

    private int owner_id;

    @JsonIgnore
    @OneToMany(mappedBy = "turf")
    private List<TurfSlots> turfSlots;
    public Turf(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Turf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", description='" + description + '\'' +
                ", no_of_slots=" + no_of_slots +
                ", no_of_days_available=" + no_of_days_available +
                ", owner_id=" + owner_id +
                '}';
    }
}
