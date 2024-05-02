package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TurfSlotsRepository extends JpaRepository<TurfSlots,Integer> {
    int findBySlot(int slot);
    TurfSlots findByTurf(Turf turf);

    @Query("SELECT ts FROM TurfSlots ts WHERE ts.turf.id = :turfId AND ts.slot = :slotId")
    TurfSlots findByTurfIdAndSlotId(@Param("turfId") Turf turfId, @Param("slotId") int slotId);

}
