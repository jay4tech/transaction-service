package com.example.transaction.service;

import com.example.transaction.entity.Transaction;

public interface ITransactionService {

    Transaction initiateTransaction(Transaction transaction);
}
