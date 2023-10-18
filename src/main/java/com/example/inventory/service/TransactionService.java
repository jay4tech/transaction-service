package com.example.inventory.service;

import com.example.inventory.client.AccountTransactionClient;
import com.example.inventory.entity.Transaction;
import com.example.inventory.entity.TransactionStatus;
import com.example.inventory.entity.TransactionType;
import com.example.inventory.repository.TransactionRepository;
import com.example.inventory.utils.MessageSender;
import com.example.inventory.utils.UtilityMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountTransactionClient accountTransactionClient;

    @Autowired
    MessageSender messageSender;

    @Transactional
    @Override
    public Transaction initiateTransaction(Transaction transaction) {
        transaction.setStatus(TransactionStatus.INITIATE);
        Transaction transactionDb = transactionRepository.save(transaction);
        try {
            transaction.setType(TransactionType.DEBIT);
            accountTransactionClient.debitAccount(UtilityMapper.transctionToTransactionEvent(transaction));
            try {
                transaction.setType(TransactionType.CREDIT);
                accountTransactionClient.creditAccount(UtilityMapper.transctionToTransactionEvent(transaction));
            } catch (Exception e) {
                transaction.setType(TransactionType.CREDIT);
                messageSender.sendToReverseDebit(UtilityMapper.getJsonString(UtilityMapper.transctionToTransactionEventReverse(transaction)));
                transaction.setStatus(TransactionStatus.FAILED);
                transactionRepository.save(transaction);
            }
        } catch (Exception e){

            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
        }
        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionRepository.save(transactionDb);
    }
}
