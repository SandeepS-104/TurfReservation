package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurfRepository extends JpaRepository<Turf,Integer> {
    @Query("SELECT ts FROM Turf ts WHERE ts.owner.id = :id")
    List<Turf> findByOwnerId(@Param("id") Integer id);

    List<Turf> findByOwner(Owner owner);
}
