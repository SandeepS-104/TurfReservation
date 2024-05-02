package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.Booking;
import com.truf.reservation.TrufReservation.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{
    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking theBooking) {
        return bookingRepository.save(theBooking);
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
            existingBooking.setTruf_name(updateBooking.getTruf_name());
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
