package com.truf.reservation.TrufReservation.Entity;

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

        @Column(name = "slot")
        private int slot;
        private LocalTime from_time;
        private LocalTime to_time;
        private String is_available;
        private int price;

        @ManyToOne
        @JoinColumn(name="turf_id")
        private Turf turf;

        public String isIs_available() {
                return is_available;
        }

        public void setIs_available(String is_available) {
                this.is_available = is_available;
        }

        public Turf getTurf() {
                return turf;
        }

        public void setTurf(Turf turf) {
                this.turf = turf;
        }

        public int getSlot() {
                return slot;
        }

        public void setSlot(int slot_id) {
                this.slot = slot_id;
        }
}