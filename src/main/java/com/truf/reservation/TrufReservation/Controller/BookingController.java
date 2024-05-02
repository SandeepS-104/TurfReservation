package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.Booking;
import com.truf.reservation.TrufReservation.Service.Booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
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


}
