
package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Service.SlotAvailability.TurfSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/turfslots")
    public class TurfSlotsController {

        @Autowired
        private TurfSlotsService turfSlotsService;

        @PostMapping
        public TurfSlots createTurfSlots(@RequestBody TurfSlots turfSlots) {
            return turfSlotsService.createTurfSlots(turfSlots);
        }

        @GetMapping("/{id}")
        public TurfSlots getTurfSlots(@PathVariable int id) {
            return turfSlotsService.getTurfSlots(id);
        }

        @PutMapping("/{id}")
        public TurfSlots updateTurfSlots(@PathVariable int id, @RequestBody TurfSlots turfSlotsDetails) {
            return turfSlotsService.updateTurfSlots(id, turfSlotsDetails);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteTurfSlots(@PathVariable int id) {
            turfSlotsService.deleteTurfSlots(id);
            return ResponseEntity.ok().build();
        }

        @GetMapping
        public List<TurfSlots> getAllTurfSlots() {
            return turfSlotsService.getAllTurfSlots();
        }

        @GetMapping("/availability")
        public List<TurfSlots> getAvailableTurfSlots() {
            return turfSlotsService.getTurfSlotAvailability();
        }
    }
