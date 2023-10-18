package com.example.inventory.controller;

import com.example.inventory.entity.Transaction;
import com.example.inventory.service.ITransactionService;
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
