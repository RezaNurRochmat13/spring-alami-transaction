package com.alami.transaction.controller;

import com.alami.transaction.entity.Transaction;
import com.alami.transaction.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("transactions")
    public ResponseEntity<Object> createNewTransaction(@RequestBody Transaction payload) {
        Transaction transaction = transactionService.doSaveTransaction(payload);

        if(transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not enough balance", HttpStatus.BAD_REQUEST);
        }
    }
}
