package com.truf.reservation.TrufReservation.Service;

import com.truf.reservation.TrufReservation.Entity.Customer;

import java.util.List;

public interface CustomerService{
    Customer save(Customer theCustomer);
    Customer findById(int id);
    List<Customer> findAll();
    Customer update(int id, Customer updateCustomer);
    void deleteById(int id);
}
