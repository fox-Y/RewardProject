package com.example.reward.service.impl;

import com.example.reward.model.Customer;
import com.example.reward.model.Transaction;
import com.example.reward.repository.CustomerRepo;
import com.example.reward.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final CustomerService customerService;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo, CustomerService customerService) {
        this.transactionRepo = transactionRepo;
        this.customerService = customerService;
    }

    @Transactional
    public boolean createTransaction(Long customerId, Transaction transaction) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) return false;

        if (transaction.getDate() == null) transaction.setDate(LocalDate.now());
        transaction.setCustomer(customer);

        customer.getTransactionSet().add(transaction);

        transactionRepo.save(transaction);

        return true;
    }
}
