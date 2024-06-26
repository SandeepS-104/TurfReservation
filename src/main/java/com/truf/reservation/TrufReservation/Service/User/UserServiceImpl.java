package com.truf.reservation.TrufReservation.Service.User;

import com.truf.reservation.TrufReservation.Entity.User;
import com.truf.reservation.TrufReservation.Repository.UserRepository;
import com.truf.reservation.TrufReservation.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }


    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User update(int id, User updateUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updateUser.getName());
            existingUser.setEmail(updateUser.getEmail());
            existingUser.setPassword(updateUser.getPassword());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
