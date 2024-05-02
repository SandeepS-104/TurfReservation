package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OwnerServiceImpls implements OwnerService {

    @Autowired
    private OwnerRepo ownerRepo;

    @Override
    public Owner saveOwner(@RequestBody  Owner owner) {

        if(owner.getPassword().equals(owner.getConfirmPassword())){
            return ownerRepo.save(owner);
        }
        else {
            throw new RuntimeException("Password and Confirm Password does not match!");
        }
        //return ownerRepo.save(owner);
    }

    @Override
    public Owner getOwner(@PathVariable int id) {
       return ownerRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteOwner(@PathVariable int id) {
        ownerRepo.deleteById(id);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner existOwner = ownerRepo.findById(owner.getId()).orElse(null);
        if(existOwner!=null){
            existOwner.setName(owner.getName());
            existOwner.setEmail(owner.getEmail());
            existOwner.setPhone(owner.getPhone());
            existOwner.setPassword(owner.getPassword());
            existOwner.setConfirmPassword(owner.getConfirmPassword());
            return ownerRepo.save(existOwner);
        }
        else {
            throw new RuntimeException("Owner not found!");
        }

    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }
}
