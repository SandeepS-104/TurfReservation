package com.truf.reservation.TrufReservation.Service.Booking;

import com.truf.reservation.TrufReservation.Entity.Booking;
import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Repository.BookingRepository;
import com.truf.reservation.TrufReservation.Repository.TurfRepository;
import com.truf.reservation.TrufReservation.Repository.TurfSlotsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private TurfRepository turfRepository;
    private TurfSlotsRepository turfSlotsRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, TurfRepository turfRepository, TurfSlotsRepository turfSlotsRepository) {
        this.bookingRepository = bookingRepository;
        this.turfRepository = turfRepository;
        this.turfSlotsRepository = turfSlotsRepository;
    }


    public void init() {
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            TurfSlots turfSlots = booking.getTurfSlots();
            LocalDate date = LocalDate.parse(booking.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalTime time = turfSlots.getTo_time();
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            if (dateTime.isBefore(LocalDateTime.now())) {
                turfSlots.setIs_available("yes");
                turfSlotsRepository.save(turfSlots);
            }
        }
    }

    @Override
    public Booking save(Booking theBooking) {
        int slot_id = theBooking.getSlot();
        Turf turf = theBooking.getTurf();
        if(turf == null) {
            throw new RuntimeException("Turf is null");
        }
        int turf_id = turf.getId();
        TurfSlots ts = turfSlotsRepository.findByTurfIdAndSlotId(turf_id, slot_id);
        int ts_id = ts.getId();

        theBooking.setTurfSlots(ts);

        if(ts.getIs_available().equals("yes"))
        {
            ts.setIs_available("no");
            turfSlotsRepository.save(ts);
           return bookingRepository.save(theBooking);
        }
        else
        {
            throw new RuntimeException("Slot is already booked");
        }

    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking update(int id, Booking updateBooking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            existingBooking.setTurf(updateBooking.getTurf());
            existingBooking.setUser_name(updateBooking.getUser_name());
            existingBooking.setSlot(updateBooking.getSlot());
            existingBooking.setDate(updateBooking.getDate());
            existingBooking.setGame(updateBooking.getGame());
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }


}