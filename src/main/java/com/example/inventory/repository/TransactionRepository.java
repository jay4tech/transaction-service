package com.example.inventory.repository;

import com.example.inventory.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getInventoryByProductId(Long productId);
}
