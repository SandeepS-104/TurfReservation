package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT b FROM Booking b WHERE b.user.Id = :userId")
    List<Booking> findByUserId(@Param("userId") int userId);


    @Query("SELECT b FROM Booking b WHERE b.turf.Id = :turfId")
    List<Booking> findByTurfId(@Param("turfId") int turfId);

    @Query("SELECT b FROM Booking b WHERE b.turfSlots.Id = :turfSlotsId")
    List<Booking> findByTurfSlotsId(@Param("turfSlotsId") int turfSlotsId);
}
