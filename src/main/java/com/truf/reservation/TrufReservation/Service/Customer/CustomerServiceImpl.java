package com.truf.reservation.TrufReservation.Service.Customer;

import com.truf.reservation.TrufReservation.Entity.Customer;
import com.truf.reservation.TrufReservation.Entity.User;
import com.truf.reservation.TrufReservation.Repository.CustomerRepo;
import com.truf.reservation.TrufReservation.Repository.UserRepository;
import com.truf.reservation.TrufReservation.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;

    private UserRepository userRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, UserRepository userRepository) {
        this.customerRepo = customerRepo;
        this.userRepository = userRepository;
    }

    @Override
    public Customer save(Customer theCustomer) {
        if (!theCustomer.getPassword().equals(theCustomer.getConfirmPassword())) {
            throw new RuntimeException("Password and Confirm Password does not match!");
        }
//        User user = new User();
//        user.setName(theCustomer.getName());
//        user.setEmail(theCustomer.getEmail());
//        user.setPassword(theCustomer.getPassword());
//
//        userRepository.save(user);
//        theCustomer.setUser(user);
        return customerRepo.save(theCustomer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer update(int id, Customer updateCustomer) {
        Customer existingCustomer = customerRepo.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(updateCustomer.getName());
            existingCustomer.setAddress(updateCustomer.getAddress());
            existingCustomer.setEmail(updateCustomer.getEmail());
            existingCustomer.setPhone(updateCustomer.getPhone());
            existingCustomer.setPassword(updateCustomer.getPassword());
            existingCustomer.setConfirmPassword(updateCustomer.getConfirmPassword());
            return customerRepo.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        customerRepo.deleteById(id);
    }




}
