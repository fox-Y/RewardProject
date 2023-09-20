package com.example.reward.controller;

import com.example.reward.exception.CustomerNotFoundException;
import com.example.reward.model.Customer;
import com.example.reward.service.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<?> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable(value = "customerId") Long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            CustomerNotFoundException e = new CustomerNotFoundException("Customer with id " + customerId + " not found!");

            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer) {
        boolean createCustomerSuccess = customerService.createCustomer(customer);

        if (createCustomerSuccess) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer can not be null!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable(value = "customerId") Long customerId) {
        boolean deleteSuccess = customerService.deleteCustomerById(customerId);

        if (!deleteSuccess) {
            return new ResponseEntity<>("Can not find customer with id " + customerId + " in database!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Delete Successful!", HttpStatus.OK);
        }
    }

    @GetMapping("/{customerId}/points/month/{month}")
    public ResponseEntity<?> getPointsByMonth(@PathVariable(value = "customerId") Long customerId, @PathVariable(value = "month") int month) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            return new ResponseEntity<>("Can not find customer!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customer.getRewardPointsByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/points")
    public ResponseEntity<?> getTotalPoints(@PathVariable(value = "customerId") Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            return new ResponseEntity<>("Can not find customer!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customer.getTotalPoints(), HttpStatus.OK);
    }

}
