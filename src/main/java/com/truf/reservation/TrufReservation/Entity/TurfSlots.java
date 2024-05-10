package com.truf.reservation.TrufReservation.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "turfslots")
public class TurfSlots {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;


        private int slot_id;
        private LocalTime from_time;
        private LocalTime to_time;
        private String is_available;
        private int price;

        @ManyToOne
        @JoinColumn(name = "turf_id")
        private Turf turf;

        public String isIs_available() {
                return is_available;
        }

        public void setIs_available(String is_available) {
                this.is_available = is_available;
        }

        @Override
        public String toString() {
                return "TurfSlots{" +
                        "id=" + id +
                        ", slot_id=" + slot_id +
                        ", from_time=" + from_time +
                        ", to_time=" + to_time +
                        ", is_available='" + is_available + '\'' +
                        ", price=" + price +
                        ", turf=" + turf +
                        '}';
        }
}
