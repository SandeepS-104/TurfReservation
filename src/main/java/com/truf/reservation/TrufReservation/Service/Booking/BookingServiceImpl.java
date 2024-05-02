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
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private TurfRepository turfRepository;

    private TurfSlotsRepository turfSlotsRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking theBooking) {
        int slot= theBooking.getSlot();

        Turf truf = theBooking.getTruf_id();
        int turfId = truf.getId();


        int slot_id = turfSlotsRepository.findBySlot(slot);
       int turf_id = turfSlotsRepository.findByTurf(truf).getId();

        Optional<TurfSlots> tf = Optional.ofNullable(turfSlotsRepository.findByTurfIdAndSlotId(truf, slot));
        TurfSlots turfSlots = turfSlotsRepository.findById(tf.get().getId()).orElse(null);
        Booking book = null;
        if(slot==turfSlots.getSlot() && truf.getId()==turfSlots.getTurf().getId()){
            if(tf.get().getIs_available().equals("no"))
            {
                throw new RuntimeException("Slot is not available. Please try another slot.");
            }
            else{
                TurfSlots turfSlot = tf.get();
                turfSlot.setIs_available("no");
                turfSlotsRepository.save(turfSlot);
                book= bookingRepository.save(theBooking);
            }
        }

        return book;
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
