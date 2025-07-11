package com.seerbit.interview_test.controller;

import com.seerbit.interview_test.dto.TransactionDto;
import com.seerbit.interview_test.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        transactionService.createTransaction(transactionDto);
        return ResponseEntity.ok("success Post");
    }

    @GetMapping
    public String transaction2() {
        return "success get";
    }

    @DeleteMapping
    public String transaction3() {
        return "success delete";
    }

}
