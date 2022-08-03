package com.alami.transaction.service;

import com.alami.transaction.dto.transaction.UserTransactionDto;
import com.alami.transaction.entity.Transaction;
import com.alami.transaction.entity.User;
import com.alami.transaction.repository.TransactionRepository;
import com.alami.transaction.repository.UserRepository;
import com.alami.transaction.util.NotEnoughBalanceException;
import com.alami.transaction.util.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Transaction doSaveTransaction(Transaction transactionPayload) throws NotEnoughBalanceException {
        User user = userRepository.findById(transactionPayload.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID : "
                        + transactionPayload.getUserId()));
        Transaction transaction;

        try {
            if(user.getBalance() == 0) {
                throw new NotEnoughBalanceException("Not enough balance");
            } else if(transactionPayload.getStatus().equals("withdraw")) {
                logger.info("Transaction status : " + transactionPayload.getStatus());
                logger.info("Payload transaction : " + transactionPayload);

                transactionPayload.setUserId(user.getId());
                user.setBalance(user.getBalance() - transactionPayload.getTransactionAmount());
                transaction = transactionRepository.save(transactionPayload);

                logger.info("Transaction record : " + transaction);
                return transaction;
            } else if(transactionPayload.getStatus().equals("topup")) {
                logger.info("Transaction status : " + transactionPayload.getStatus());
                logger.info("Payload transaction : " + transactionPayload);

                transactionPayload.setUserId(user.getId());
                user.setBalance(user.getBalance() + transactionPayload.getTransactionAmount());
                transaction = transactionRepository.save(transactionPayload);

                logger.info("Transaction record : " + transaction);
                return transaction;
            }

        } catch (NotEnoughBalanceException e) {
            logger.error(e.getMessage());
            return null;
        }

        return null;
    }

    @Override
    public List<UserTransactionDto> doFindTransactionByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID : "
                        + userId));
        List<Transaction> transactions = transactionRepository.findTransactionByUserId(user.getId());
        List<UserTransactionDto> userTransactions = new ArrayList<>();


        for(Transaction userTransaction: transactions) {
            UserTransactionDto userTransactionDto = UserTransactionDto.builder()
                    .id(userTransaction.getId())
                    .name(user.getName())
                    .currentBalance(user.getBalance())
                    .transactionAmount(userTransaction.getTransactionAmount())
                    .transactionDate(userTransaction.getTransactionDate())
                    .build();
            userTransactions.add(userTransactionDto);
        }

        return userTransactions;
    }

    @Override
    public List<UserTransactionDto> doFindTransactionBetweenDates(String startDate,
                                                                  String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);

        List<Transaction> transactions = transactionRepository
                .findTransactionByCreatedAtAndTransactionDateBetween(startLocalDate, endLocalDate);
        List<UserTransactionDto> userTransactions = new ArrayList<>();


        for(Transaction userTransaction: transactions) {
            User user = userRepository.findById(userTransaction.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID : "
                            + userTransaction.getUserId()));

            UserTransactionDto userTransactionDto = UserTransactionDto.builder()
                    .id(userTransaction.getId())
                    .name(user.getName())
                    .currentBalance(user.getBalance())
                    .transactionAmount(userTransaction.getTransactionAmount())
                    .transactionDate(userTransaction.getTransactionDate())
                    .build();
            userTransactions.add(userTransactionDto);
        }

        return userTransactions;
    }
}
