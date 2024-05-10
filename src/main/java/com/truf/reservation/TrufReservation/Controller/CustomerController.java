package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.DTO.CustomerDTO;
import com.truf.reservation.TrufReservation.Entity.Customer;
import com.truf.reservation.TrufReservation.Entity.User;
import com.truf.reservation.TrufReservation.Repository.CustomerRepo;
import com.truf.reservation.TrufReservation.Service.Customer.CustomerService;
import com.truf.reservation.TrufReservation.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    private CustomerRepo customerrepo;

    private UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, CustomerRepo customerrepo) {
        this.customerService = customerService;
        this.userService = userService;
        this.customerrepo = customerrepo;
    }

    @PostMapping
    public Customer save(@RequestBody Customer theCustomer) {
        return customerService.save(theCustomer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable int id, @RequestBody Customer updateCustomer) {
        return customerService.update(id, updateCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        customerService.deleteById(id);
    }


//    @PostMapping("/register")
//    public ResponseEntity<Map<String,Object>> register (@RequestBody Customer customer) {
//        Map<String,Object>  mp=new HashMap<>();
//        String msg="";
//        boolean suc=false;
//
//        // finding the user
//        Customer cust= customerrepo.findByEmail(customer.getEmail());
//        if(cust==null)
//        {
//
//            cust=customerService.save(customer);
//
//
//
//            if (!cust.getPassword().equals(cust.getConfirmPassword())) {
//                throw new RuntimeException("Password and Confirm Password does not match!");
//            }
//            User user = new User();
//            user.setName(cust.getName());
//            user.setEmail(cust.getEmail());
//            user.setPassword(cust.getPassword());
//
//            User savedUser = userService.save(user);
//
//
//
//
//            msg = "Registration Successful";
//            suc = true;
//            mp.put("cust",new CustomerDTO(cust.getId(),cust.getName(),cust.getEmail(),cust.getPassword(),cust.getAddress(),cust.getPhone(),cust.getUser().getId()));
//
//        }
//        else {
//            msg="Customer already exists";
//        }
//
//        mp.put("message", msg);
//        mp.put("success", suc);
//
//
//        return new ResponseEntity<>(mp, HttpStatus.CREATED);
//    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "CustomerRegister";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("customer") Customer customer) {
        // ...

        // finding the user
       // Customer cust = customerrepo.findByEmail(customer.getEmail());
        if (customer != null) {
            // ...

            if (!customer.getPassword().equals(customer.getConfirmPassword())) {
                throw new RuntimeException("Password and Confirm Password does not match!");
            }
            User user = new User();
            user.setName(customer.getName());
            user.setEmail(customer.getEmail());
            user.setPassword(customer.getPassword());
            user.setRole("customer");

            User savedUser = userService.save(user);

            customer.setUser(user);

            customerService.save(customer);


            // Add the customer to the model
           //model.addAttribute("cust", new CustomerDTO(cust.getId(), cust.getName(), cust.getEmail(), cust.getPassword(), cust.getAddress(), cust.getPhone(), cust.getUser().getId()));


            return "redirect:/turf/all";
        }
else {
            return "failure";
        }
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "CustomerLogin";
    }
    @PostMapping("/login")
    public String login (@ModelAttribute("customer") Customer customer, Model model)
    {


        Customer cust= customerrepo.findByEmail(customer.getEmail());
        if(cust!=null)
        {
            if(cust.getPassword().equals(customer.getPassword()))
            {

                return "redirect:/turf/all";
            }
            else {
               return "hello";
            }
        }
        else {
            return "failure";
        }
    }


}

