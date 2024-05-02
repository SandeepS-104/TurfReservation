package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.User;
import com.truf.reservation.TrufReservation.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User save( @RequestBody User theUser) {
        return userService.save(theUser);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable int  id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
    @PutMapping("/{id}")
    public User update( @PathVariable int  id,  @RequestBody User updateUser) {
        return userService.update(id, updateUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable  int id) {
        userService.deleteById(id);
    }
}

