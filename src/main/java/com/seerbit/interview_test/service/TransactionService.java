package com.seerbit.interview_test.service;

import com.seerbit.interview_test.dto.TransactionDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.Instant;

public interface TransactionService {
  void createTransaction(TransactionDto transactionDto);
}
