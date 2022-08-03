package com.alami.transaction.service;

import com.alami.transaction.dto.transaction.UserTransactionDto;
import com.alami.transaction.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction doSaveTransaction(Transaction transactionPayload);
    List<UserTransactionDto> doFindTransactionByUserId(Long userId);
    List<UserTransactionDto> doFindTransactionBetweenDates(String startDate,
                                                           String endDate);
}
