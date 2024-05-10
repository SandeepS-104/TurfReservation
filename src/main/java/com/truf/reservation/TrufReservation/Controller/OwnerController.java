package com.truf.reservation.TrufReservation.Controller;

import com.truf.reservation.TrufReservation.DTO.CustomerDTO;
import com.truf.reservation.TrufReservation.DTO.OwnerDTO;
import com.truf.reservation.TrufReservation.Entity.Customer;
import com.truf.reservation.TrufReservation.Entity.Owner;
import com.truf.reservation.TrufReservation.Entity.User;
import com.truf.reservation.TrufReservation.Repository.OwnerRepo;
import com.truf.reservation.TrufReservation.Service.Owner.OwnerService;
import com.truf.reservation.TrufReservation.Service.User.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerRepo ownerRepo;

    @Value("${code}")
    private String code;

    @GetMapping
    public ResponseEntity<Iterable<Owner>> getOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable int id) {
        Owner owner = ownerService.getOwner(id);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addOwner(@RequestBody Owner owner) {
        ownerService.saveOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body("Owner created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOwner(@PathVariable int id, @RequestBody Owner owner) {
        owner.setId(id);
        ownerService.updateOwner(owner);
        return ResponseEntity.ok("Owner updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable int id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok("Owner deleted successfully");
    }

    //    @PostMapping("/register")
//    public ResponseEntity<Map<String,Object>> register (@RequestBody Owner owner) {
//        Map<String,Object>  mp=new HashMap<>();
//        String msg="";
//        boolean suc=false;
//
//        // finding the user
//        Owner o= ownerRepo.findByEmail(owner.getEmail());
//        if(o==null)
//        {
//
//            o=ownerService.saveOwner(owner);
//
//
//
//            if (!o.getPassword().equals(o.getConfirmPassword())) {
//                throw new RuntimeException("Password and Confirm Password does not match!");
//            }
//            User user = new User();
//            user.setName(o.getName());
//            user.setEmail(o.getEmail());
//            user.setPassword(o.getPassword());
//
//            User savedUser = userService.save(user);
//
//
//
//
//            msg = "Registration Successful";
//            suc = true;
//            mp.put("cust",new OwnerDTO(o.getId(),o.getName(),o.getEmail(),o.getPassword(),o.getPhone(),o.getUser().getId()));
//
//        }
//        else {
//            msg="Owner already exists";
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
        model.addAttribute("owner", new Owner());
        return "OwnerRegister";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("owner") Owner owner) {
        // ...

        // finding the user
        // Customer cust = customerrepo.findByEmail(customer.getEmail());
        if (owner != null) {
            // ...

            if (!owner.getPassword().equals(owner.getConfirmPassword())) {
                throw new RuntimeException("Password and Confirm Password does not match!");
            }
            User user = new User();
            user.setName(owner.getName());
            user.setEmail(owner.getEmail());
            user.setPassword(owner.getPassword());
            user.setRole("owner");

            //User savedUser = userService.save(user);

            owner.setUser(user);
            owner.setRole("owner");
            if(code.equals(owner.getCode())){
                ownerService.saveOwner(owner);
            }
            else {
                throw new RuntimeException("Invalid Code");
            }


            // Add the customer to the model
            //model.addAttribute("cust", new CustomerDTO(cust.getId(), cust.getName(), cust.getEmail(), cust.getPassword(), cust.getAddress(), cust.getPhone(), cust.getUser().getId()));


            return "redirect:/booking/form";
        }
        else {
            return "failure";
        }
    }

//    @PostMapping("/register")
//    public String register(@ModelAttribute("owner") Owner owner) {
//        if (owner != null) {
//            if (!owner.getPassword().equals(owner.getConfirmPassword())) {
//                throw new RuntimeException("Password and Confirm Password does not match!");
//            }
//
//            // Create a new User object if the User associated with the Owner is null
//            if (owner.getUser() == null) {
//                owner.setUser(new User());
//            }
//
//            owner.getUser().setRole("owner");
//            userService.save(owner.getUser()); // Save the User object after setting the role
//
//            owner.setRole("owner");
//            if(code.equals(owner.getCode())){
//                ownerService.saveOwner(owner);
//            }
//            else {
//                throw new RuntimeException("Invalid Code");
//            }
//
//            return "redirect:/booking/form";
//        }
//        else {
//            return "failure";
//        }
//    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "OwnerLogin";
    }
    @PostMapping("/login")
    public String login (@ModelAttribute("owner") Owner owner, HttpSession session)
    {



        Owner owners= ownerRepo.findByEmail(owner.getEmail());
        int owner_id=owners.getId();
        System.out.println("owner_id"+owner_id);
        if(owners!=null)
        {
            if(owners.getPassword().equals(owner.getPassword()))
            {
                // session.setAttribute("ownerId", owners.getId());
                return "redirect:/turf/owner?ownerId="+owner_id;
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
;