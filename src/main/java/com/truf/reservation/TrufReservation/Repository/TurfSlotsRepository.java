package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurfSlotsRepository extends JpaRepository<TurfSlots,Integer> {

    @Query("SELECT ts FROM TurfSlots ts WHERE ts.turf.id = :turfId AND ts.slot_id = :slotId")
    TurfSlots findByTurfIdAndSlotId(@Param("turfId") int turfId, @Param("slotId") int slotId);

    @Query("SELECT ts FROM TurfSlots ts WHERE ts.is_available = 'yes'")
    List<TurfSlots> findByIsAvailable();

    @Query("SELECT ts FROM TurfSlots ts WHERE ts.turf.id = :turfId AND ts.is_available = 'yes'")
    List<TurfSlots> findAvailByTurfId(@Param("turfId") int turfId);

    @Query("SELECT ts FROM TurfSlots ts WHERE ts.turf.id = :turfId")
    List<TurfSlots> findByTurfId(@Param("turfId") int turfId);
}
