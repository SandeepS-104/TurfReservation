package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.*;
import com.truf.reservation.TrufReservation.Repository.OwnerRepo;
import com.truf.reservation.TrufReservation.Service.Truf.TurfService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/turf")
public class TurfController {

    @Autowired
    private TurfService turfService;

    @Autowired
    private OwnerRepo ownerRepository;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTurf(@PathVariable int id) {
        turfService.deleteTurf(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete")
    public String deleteTurf(@RequestParam("turfId") int id, Model model) {
        int ownerId = turfService.getTurf(id).getOwner().getId();
        turfService.deleteTurf(id);
        return "redirect:/turf/owner?ownerId=" + ownerId;

    }


    @GetMapping("/all")
    public String getAllTurfs(Model model) {
        List<Turf> turf = turfService.getAllTurfs();
        model.addAttribute("turfs", turf);
        return "turflist";
    }

    @GetMapping("/slots")
    public String getSlots(@RequestParam("turfId") int id, Model model) {
        List<TurfSlots> turf = turfService.getSlots(id);
        model.addAttribute("slots", turf);
        return "turf_slots";
    }

    @GetMapping("/owner")
    public String getTurfByOwner(@RequestParam("ownerId") int id, Model model) {
        List<Turf> turf = turfService.getTurfByOwner(id);
        model.addAttribute("turfs", turf);
        model.addAttribute("owner_id", id);
        return "turflist-owner";
    }
    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam("ownerId") int id, Model model) {
        model.addAttribute("turf", new Turf());
        Owner owner = ownerRepository.findById(id).orElse(null);
        model.addAttribute("owner_id", id);

        return "turfregister";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("turf") Turf turf, @RequestParam("owner_id") int ownerId, Model model){
        Owner owner = ownerRepository.findById(ownerId).orElse(null);

        turf.setOwner(owner);
        turfService.createTurf(turf);

        return "redirect:/turf/owner?ownerId=" + ownerId;
    }
//@PostMapping("/register")
//public String register(@ModelAttribute("turf") Turf turf, @ModelAttribute("owner_id") Integer id, Model model){
//    if (id == null) {
//        // handle the error, e.g., return an error view
//        model.addAttribute("error", "Owner ID is required.");
//        return "error";
//    }
//
//    Owner owner = ownerRepository.findById(id).orElse(null);
//    if (owner == null) {
//        // handle the error, e.g., return an error view
//        model.addAttribute("error", "Owner not found.");
//        return "error";
//    }
//
//    turf.setOwner(owner);
//    turfService.createTurf(turf);
//
//    return "redirect:/turf/all";
//}
    @GetMapping("/addslots")
    public String showAddSlotsForm(@RequestParam("turfId") int id, @RequestParam("ownerId") int ownerId, Model model) {
        model.addAttribute("turfId", id);
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("turfslots", new TurfSlots());
        return "slotregister";
    }
}
