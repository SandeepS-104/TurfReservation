package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Service.Owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<Iterable<Owner>> getOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable int id) {
        Owner owner = ownerService.getOwner(id);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addOwner(@RequestBody Owner owner) {
        ownerService.saveOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body("Owner created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOwner(@PathVariable int id, @RequestBody Owner owner) {
        owner.setId(id);
        ownerService.updateOwner(owner);
        return ResponseEntity.ok("Owner updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable int id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok("Owner deleted successfully");
    }
}
;