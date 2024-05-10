package com.truf.reservation.TrufReservation.Service.Truf;

import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;

import java.util.List;

public interface TurfService {
    Turf createTurf(Turf turf);
    Turf getTurf(int id);
    Turf updateTurf(int id,Turf turf);
    void deleteTurf(int id);
    List<Turf> getAllTurfs();

    List<TurfSlots> getSlots(int id);

    List<Turf> getTurfByOwner(int id);
}
