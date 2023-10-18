package com.example.inventory.utils;

import com.example.inventory.entity.Transaction;
import com.example.inventory.entity.TransactionType;
import com.example.inventory.model.TransactionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class UtilityMapper {

    public static <T> Object responseToModel(String json, Class<T> tClass) {

        try {
            return getMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String getJsonString(Object order) {
        try {
            return getMapper().writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static TransactionEvent transctionToTransactionEvent(Transaction transaction) {
        TransactionEvent transactionEvent = new TransactionEvent();
        if (transaction.getType().equals(TransactionType.DEBIT))
            transactionEvent.setAccountNo(transaction.getFromAccount());
        if (transaction.getType().equals(TransactionType.CREDIT))
            transactionEvent.setAccountNo(transaction.getToAccount());
        transactionEvent.setType(transaction.getType());
        transactionEvent.setAmount(transaction.getAmount());
        transactionEvent.setStatus(transaction.getStatus());
        return transactionEvent;
    }
    public static TransactionEvent transctionToTransactionEventReverse(Transaction transaction) {
        TransactionEvent transactionEvent = new TransactionEvent();
        if (transaction.getType().equals(TransactionType.CREDIT))
            transactionEvent.setAccountNo(transaction.getFromAccount());
        if (transaction.getType().equals(TransactionType.DEBIT))
            transactionEvent.setAccountNo(transaction.getToAccount());
        transactionEvent.setType(transaction.getType());
        transactionEvent.setAmount(transaction.getAmount());
        transactionEvent.setStatus(transaction.getStatus());
        return transactionEvent;
    }
}
