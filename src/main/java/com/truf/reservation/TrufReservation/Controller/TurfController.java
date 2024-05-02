package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.Turf;
import com.truf.reservation.TrufReservation.Service.Truf.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turf")
public class TurfController {

    @Autowired
    private TurfService turfService;

    @PostMapping
    public Turf createTurf(@RequestBody Turf turf) {
        return turfService.createTurf(turf);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turf> getTurf(@PathVariable int id) {
        Turf turf = turfService.getTurf(id);
        if (turf == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turf> updateTurf(@PathVariable int id, @RequestBody Turf turfDetails) {
        Turf updatedTurf = turfService.updateTurf(id, turfDetails);
        if (updatedTurf == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTurf);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurf(@PathVariable int id) {
        turfService.deleteTurf(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Turf> getAllTurfs() {
        return turfService.getAllTurfs();
    }
}
