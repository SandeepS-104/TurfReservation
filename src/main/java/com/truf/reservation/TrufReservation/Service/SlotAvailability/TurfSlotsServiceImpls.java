package com.truf.reservation.TrufReservation.Service.SlotAvailability;


import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Repository.TurfSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TurfSlotsServiceImpls implements TurfSlotsService {

    @Autowired
    private TurfSlotsRepository turfSlotsRepository;

    @Override
    public TurfSlots createTurfSlots(TurfSlots turfSlots) {
        Turf turf = turfSlots.getTurf();
        int slot_count= turf.getNo_of_slots();
        if(turfSlots.getSlot_id()<=slot_count) {

            return turfSlotsRepository.save(turfSlots);
        }
        else{
            throw new RuntimeException("Slots are not available for this turf. Please try another turf.");
        }

    }

    @Override
    public TurfSlots getTurfSlots(int id) {
        return turfSlotsRepository.findById(id).orElse(null);
    }

    @Override
    public TurfSlots updateTurfSlots(int id, TurfSlots turfSlotsDetails) {
        TurfSlots existingTurfSlots = turfSlotsRepository.findById(id).orElse(null);

        if (existingTurfSlots != null) {
            existingTurfSlots.setSlot_id(turfSlotsDetails.getSlot_id());
            existingTurfSlots.setFrom_time(turfSlotsDetails.getFrom_time());
            existingTurfSlots.setTo_time(turfSlotsDetails.getTo_time());
            existingTurfSlots.setIs_available(turfSlotsDetails.isIs_available());
            existingTurfSlots.setPrice(turfSlotsDetails.getPrice());
            existingTurfSlots.setTurf(turfSlotsDetails.getTurf());
            return turfSlotsRepository.save(existingTurfSlots);
        }

        return null;
    }

    @Override
    public void deleteTurfSlots(int id) {
        turfSlotsRepository.deleteById(id);
    }

    @Override
    public List<TurfSlots> getAllTurfSlots() {
        return turfSlotsRepository.findAll();
    }
}
