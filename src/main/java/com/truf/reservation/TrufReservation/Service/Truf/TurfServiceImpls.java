package com.truf.reservation.TrufReservation.Service.Truf;

import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Entity.TurfSlots;
import com.truf.reservation.TrufReservation.Repository.OwnerRepo;
import com.truf.reservation.TrufReservation.Repository.TurfRepository;
import com.truf.reservation.TrufReservation.Repository.TurfSlotsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TurfServiceImpls implements TurfService {

    @Autowired
    private TurfRepository turfRepository;

    @Autowired
    private TurfSlotsRepository turfSlotsRepository;

    @Autowired
    private OwnerRepo ownerRepository;

    @Override
    public Turf createTurf(Turf turf) {
        return turfRepository.save(turf);
    }

    @Override
    public Turf getTurf(int id) {
        return turfRepository.findById(id).orElse(null);
    }

    @Override
    public Turf updateTurf(int id, Turf turfDetails) {
        Turf turf = turfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turf not found with id: " + id));
        turf.setName(turfDetails.getName());
        turf.setLocation(turfDetails.getLocation());
        turf.setAddress(turfDetails.getAddress());
        turf.setContact(turfDetails.getContact());
        turf.setDescription(turfDetails.getDescription());
        turf.setNo_of_slots(turfDetails.getNo_of_slots());
        turf.setNo_of_days_available(turfDetails.getNo_of_days_available());
        return turfRepository.save(turf);
    }

    @Override
    public void deleteTurf(int id) {
        turfRepository.deleteById(id);
    }

    @Override
    public List<Turf> getAllTurfs() {
        return turfRepository.findAll();
    }

    @Override
    public List<TurfSlots> getSlots(int id) {
        return turfSlotsRepository.findByTurfId(id);
    }

    @Override
    public List<Turf> getTurfByOwner(int id) {
        Owner owner = ownerRepository.findById(id).orElse(null);
        return turfRepository.findByOwner(owner);
    }

}
