package com.truf.reservation.TrufReservation.Service.SlotAvailability;


import com.truf.reservation.TrufReservation.Entity.TurfSlots;

import java.util.List;

public interface TurfSlotsService {
    TurfSlots createTurfSlots(TurfSlots turfSlots);
    TurfSlots getTurfSlots(int id);
    public TurfSlots updateTurfSlots(int id, TurfSlots turfSlotsDetails);
    void deleteTurfSlots(int id);
    List<TurfSlots> getAllTurfSlots();

    List<TurfSlots> getTurfSlotAvailability();

    List<TurfSlots> getTurfSlotsByTurfId(int id);

    List<TurfSlots> getSlotsByTurfId(int id);
}
