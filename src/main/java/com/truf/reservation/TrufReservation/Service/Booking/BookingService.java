package com.truf.reservation.TrufReservation.Service.Booking;

import com.truf.reservation.TrufReservation.Entity.Booking;

import java.util.List;

public interface BookingService {
    Booking save(Booking theBooking);
    Booking findById(int id);
    List<Booking> findAll();
    Booking update(int id, Booking updateBooking);
    void deleteById(int id);
}
