
package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.Booking;
import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Repository.BookingRepository;
import com.truf.reservation.TrufReservation.Service.Booking.BookingService;
import com.truf.reservation.TrufReservation.Service.SlotAvailability.TurfSlotsService;
import com.truf.reservation.TrufReservation.Service.Truf.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Controller
    @RequestMapping("/turfslots")
    public class TurfSlotsController {

        @Autowired
        private TurfSlotsService turfSlotsService;

        @Autowired
        private TurfService turfService;

        @Autowired
        private BookingRepository bookingRepository;

        @Autowired
        private BookingService bookingService;

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

        @GetMapping("/availslots")
        public String getSlots(@RequestParam("turfId") int id, Model model) {
            List<TurfSlots> turfslot =turfSlotsService.getSlotsByTurfId(id);
            model.addAttribute("slots", turfslot);
            model.addAttribute("turfId", id);

            Turf turf= turfService.getTurf(id);
            model.addAttribute("turf", turf);
            return "turf_slots";
        }

        @GetMapping("/availslots/owner")
        public String getSlotsOwner(@RequestParam("turfId") int id,@RequestParam("ownerId") int ownerId, Model model) {
            List<TurfSlots> turf =turfSlotsService.getSlotsByTurfId(id);
            model.addAttribute("slots", turf);
            model.addAttribute("turfId", id);
            model.addAttribute("ownerId", ownerId);
            return "turf_slots-owner";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute("turfslots") TurfSlots turfslots,@RequestParam("turfId") int id, @RequestParam("ownerId") int ownerId, Model model){

            turfslots.setTurf(turfService.getTurf(id));
            turfslots.setIs_available("yes");
            turfSlotsService.createTurfSlots(turfslots);

            return "redirect:/turfslots/availslots?turfId=" + id;
        }

        @GetMapping("/delete")
        public String deleteTurfSlots(@RequestParam("turfSlotId") int id, Model model) {
            int ownerId = turfSlotsService.getTurfSlots(id).getTurf().getOwner().getId();

            List<Booking> bookings = bookingRepository.findByTurfSlotsId(id);
            for (Booking booking : bookings) {
                bookingRepository.delete(booking);
            }
            turfSlotsService.deleteTurfSlots(id);
            return "redirect:/turf/owner?ownerId=" + ownerId;

        }

    }
