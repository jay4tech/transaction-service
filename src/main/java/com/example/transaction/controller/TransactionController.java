package com.example.transaction.controller;

import com.example.transaction.entity.Transaction;
import com.example.transaction.service.ITransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    ITransactionService transactionService;

    @PostMapping("/initiateTransaction")
    public Transaction initiateTransaction(@RequestBody Transaction transaction){
        return transactionService.initiateTransaction(transaction);
    }
}
