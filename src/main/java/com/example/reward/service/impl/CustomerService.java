package com.example.reward.service.impl;


import com.example.reward.exception.CustomerNotFoundException;
import com.example.reward.model.Customer;
import com.example.reward.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public boolean createCustomer(Customer customer) {
        if (customer == null) return false;

        customerRepo.save(customer);

        return true;
    }

    public List<Customer> getCustomers() {
        log.info("Invoke getCustomers() method in [CustomerService]!");

        return customerRepo.findAll();
    }

    public Customer getCustomer(Long id) {
        log.info("Invoke getCustomer() method in [CustomerService]!");

        return customerRepo.findById(id)
                .orElse(null);
    }

    public boolean deleteCustomerById(Long id) {
        log.info("Invoke deleteCustomerById() method in [CustomerService]!");

        Customer customer = customerRepo.findById(id).orElse(null);

        if (customer == null) {
            return false;
        }

        customerRepo.delete(customer);

        return true;
    }
}
