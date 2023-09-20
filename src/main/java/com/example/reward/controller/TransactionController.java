package com.example.reward.controller;

import com.example.reward.model.Transaction;
import com.example.reward.service.impl.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestParam(value = "customerId") Long customerId, @RequestBody Transaction transaction) {

        boolean createTransactionSuccess = transactionService.createTransaction(customerId, transaction);

        if (createTransactionSuccess) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to create transaction", HttpStatus.BAD_REQUEST);
        }
    }
}
