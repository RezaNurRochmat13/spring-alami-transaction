package com.alami.transaction.service;

import com.alami.transaction.entity.Transaction;
import com.alami.transaction.entity.User;
import com.alami.transaction.repository.TransactionRepository;
import com.alami.transaction.repository.UserRepository;
import com.alami.transaction.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Transaction doSaveTransaction(Transaction transactionPayload) {
        User user = userRepository.findById(transactionPayload.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID : "
                        + transactionPayload.getUserId()));

        transactionPayload.setUserId(user.getId());

        return transactionRepository.save(transactionPayload);
    }
}
