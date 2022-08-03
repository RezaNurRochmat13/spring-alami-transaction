package com.alami.transaction.controller;

import com.alami.transaction.dto.BaseResponseDto;
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
            BaseResponseDto baseResponse = BaseResponseDto.builder()
                    .data(transaction)
                    .build();

            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not enough balance", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("transactions/history")
    public ResponseEntity<Object> findUserTransaction(
            @RequestParam(value = "user_id", required = false) Long user_id,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date) {
        BaseResponseDto baseResponse = null;

        if(user_id != null) {
            baseResponse = BaseResponseDto.builder()
                    .data(transactionService.doFindTransactionByUserId(user_id))
                    .build();
        } else {
            baseResponse = BaseResponseDto.builder()
                    .data(transactionService.doFindTransactionBetweenDates(start_date, end_date))
                    .build();
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
