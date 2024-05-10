package com.truf.reservation.TrufReservation.Service.Booking;

import com.truf.reservation.TrufReservation.Entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {
    Booking save(Booking theBooking);
    Booking findById(int id);
    List<Booking> findAll();
    Booking update(int id, Booking updateBooking);
    void deleteById(int id);
    List<Booking> findBookingByUserId(int id);

    List<Booking> findBookingByTurfId(int id);
}
