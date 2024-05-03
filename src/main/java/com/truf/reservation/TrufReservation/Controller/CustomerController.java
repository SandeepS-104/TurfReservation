package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.Entity.Customer;
import com.truf.reservation.TrufReservation.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public Customer save(@RequestBody Customer theCustomer) {
        return customerService.save(theCustomer);
    }
    @GetMapping("/{id}")
    public Customer findById(@PathVariable int  id) {
        return customerService.findById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable int  id, @RequestBody Customer updateCustomer) {
        return customerService.update(id, updateCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable  int id) {
        customerService.deleteById(id);
    }
}
