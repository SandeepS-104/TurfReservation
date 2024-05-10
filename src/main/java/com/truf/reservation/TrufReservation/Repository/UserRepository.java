package com.truf.reservation.TrufReservation.Repository;

import com.truf.reservation.TrufReservation.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String Email);
}
