package com.alami.transaction.service;

import com.alami.transaction.entity.Transaction;

public interface TransactionService {
    Transaction doSaveTransaction(Transaction transactionPayload);
}
