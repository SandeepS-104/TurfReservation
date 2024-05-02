package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.Owner;

import java.util.List;


public interface OwnerService {

    public Owner saveOwner(Owner owner);

    public Owner getOwner(int id);

    public void deleteOwner(int id);

    public Owner updateOwner(Owner owner);

    public List<Owner> getAllOwners();
}
