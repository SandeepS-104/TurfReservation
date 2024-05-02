package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {
}
