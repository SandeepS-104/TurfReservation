package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.Customer;
import com.truf.reservation.TrufReservation.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;
    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer theCustomer) {
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
            return customerRepo.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        customerRepo.deleteById(id);
    }




}
