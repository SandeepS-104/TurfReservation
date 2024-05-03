package com.truf.reservation.TrufReservation.Service.Booking;

import com.truf.reservation.TrufReservation.Entity.Booking;
import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Repository.BookingRepository;
import com.truf.reservation.TrufReservation.Repository.TurfRepository;
import com.truf.reservation.TrufReservation.Repository.TurfSlotsRepository;
import com.truf.reservation.TrufReservation.Service.Booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Booking save(Booking theBooking) {
        int slot_id = theBooking.getSlot();
        int turf_id = theBooking.getTruf_id();

        Turf turf = turfRepository.findById(turf_id).orElse(null);
        TurfSlots ts = turfSlotsRepository.findByTurfIdAndSlotId(turf, slot_id);
        int ts_id = ts.getId();

        TurfSlots turfSlots = ts;

        if(ts.getIs_available()=="yes")
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
            existingBooking.setTruf_id(updateBooking.getTruf_id());
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
