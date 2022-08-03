package com.alami.transaction.repository;

import com.alami.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByUserId(Long userId);
    @Query(value = "SELECT * FROM transactions WHERE created_at >= :startDate AND transaction_date <= :endDate", nativeQuery = true)
    List<Transaction> findTransactionByCreatedAtAndTransactionDateBetween(LocalDate startDate,
                                                                          LocalDate endDate);
}
