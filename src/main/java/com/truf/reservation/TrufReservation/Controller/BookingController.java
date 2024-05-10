package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.*;
import com.truf.reservation.TrufReservation.Repository.UserRepository;
import com.truf.reservation.TrufReservation.Service.Booking.BookingService;
import com.truf.reservation.TrufReservation.Service.SlotAvailability.TurfSlotsService;
import com.truf.reservation.TrufReservation.Service.Truf.TurfService;
import com.truf.reservation.TrufReservation.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;
    private TurfService turfService;

    private UserRepository userRepo;

    @Autowired
    private TurfSlotsService turfSlotsService;

    @Autowired
    public BookingController(BookingService bookingService, TurfService turfService, UserRepository userRepo) {
        this.bookingService = bookingService;
        this.turfService = turfService;
        this.userRepo = userRepo;
    }
    @PostMapping
    public Booking save(@RequestBody Booking theBooking) {
        return bookingService.save(theBooking);
    }
    @GetMapping("/{id}")
    public Booking findById(@PathVariable int id) {
        return bookingService.findById(id);
    }

    @GetMapping
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable int id, @RequestBody Booking updateBooking) {
        return bookingService.update(id, updateBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        bookingService.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public List<Booking> findBookingByUserId(@PathVariable int id) {
        return bookingService.findBookingByUserId(id);
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute("booking") Booking theBooking, @RequestParam("turfId") int turfId, @RequestParam("slotId") int slotId, Model model) {
        theBooking.setSlot(slotId);
        theBooking.setTurf(turfService.getTurf(turfId));
        String email = theBooking.getEmail();

        User user = userRepo.findByEmail(email);

        if (user != null) {
            theBooking.setUser(user);
        } else {
            throw new RuntimeException("User with email " + email + " does not exist");
        }

        bookingService.save(theBooking);
        return"redirect:/booking/success?bookingId=" + theBooking.getId();
    }
    @GetMapping("/form")
    public String showForm(@RequestParam("turfId") int turfId, @RequestParam("slotId") int slotId, Model model) {
        Turf turf = turfService.getTurf(turfId);
        if (turf == null) {
            throw new RuntimeException("Turf with id " + turfId + " does not exist");
        }
        model.addAttribute("turf", turf);
        model.addAttribute("slotId", slotId);
        model.addAttribute("booking", new Booking());
        return "BookingForm";
    }

    @GetMapping("/history")
    public String showBookingHistory(@RequestParam("turfId") int id, Model model) {
        List<Booking> bookings = bookingService.findBookingByTurfId(id);
        Owner owner = turfService.getTurf(id).getOwner();
        int ownerId = owner.getId();
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("bookings", bookings);
        return "BookingHistory";
    }

    @GetMapping("/success")
    public String showSuccess(@RequestParam("bookingId") int bookingId, Model model) {
        Booking booking = bookingService.findById(bookingId);
        model.addAttribute("booking", booking);
        TurfSlots ts= booking.getTurfSlots();
        model.addAttribute("turfSlots", ts);
        Turf turf = booking.getTurf();
        model.addAttribute("turf", turf);
        return "successBooking";
    }
}
