package com.example.transaction.client;

import com.example.transaction.model.TransactionEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-management", url = "http://localhost:8888/accounts")
public interface AccountTransactionClient {

    @GetMapping("/accounts/credit")
    public void creditAccount(@RequestBody TransactionEvent transactionEvent);

    @GetMapping("/accounts/debit")
    public void debitAccount(@RequestBody TransactionEvent transactionEvent);
  
}