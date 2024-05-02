package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.User;

import java.util.List;

public interface UserService {
    User save(User theUser);
    User findById(int id);
    List<User> findAll();
    User update(int id, User updateUser);
    void deleteById(int id);

}
