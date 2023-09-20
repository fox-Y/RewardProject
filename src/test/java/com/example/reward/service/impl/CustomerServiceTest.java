package com.example.reward.service.impl;

import com.example.reward.model.Customer;
import com.example.reward.model.Transaction;
import com.example.reward.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    private CustomerService customerService;

    private static final Transaction transaction = new Transaction();
    private static final Customer customer = new Customer();

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepo);

        transaction.setDate(LocalDate.now());
        transaction.setTransactionId(1L);
        transaction.setAmount(120D);
        transaction.setCustomer(customer);

        customer.setCustomerId(1L);
        customer.setCustomerName("Eric");
        customer.setTransactionSet(Set.of(transaction));
    }

    @Test
    void createCustomer() {
        // when
        customerService.createCustomer(customer);

        //verify
        verify(customerRepo).save(customer);
    }

    @Test
    void getCustomers() {
        customerService.getCustomers();

        verify(customerRepo).findAll();
    }

    @Test
    void getCustomer() {
        customerService.getCustomer(1L);

        verify(customerRepo).findById(1L);
    }
}