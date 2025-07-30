package com.seerbit.interview_test.controller;

import com.seerbit.interview_test.dto.ResponseDto;
import com.seerbit.interview_test.dto.StatisticsDto;
import com.seerbit.interview_test.dto.TransactionDto;
import com.seerbit.interview_test.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public ResponseEntity<?> transaction(@Valid @RequestBody TransactionDto transactionDto) {
        ResponseDto transaction = transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping
    public ResponseEntity<StatisticsDto> transaction2() {
        StatisticsDto response = transactionService.createStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> transaction3() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
