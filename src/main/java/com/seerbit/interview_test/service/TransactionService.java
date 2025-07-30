package com.seerbit.interview_test.service;

import com.seerbit.interview_test.dto.ResponseDto;
import com.seerbit.interview_test.dto.StatisticsDto;
import com.seerbit.interview_test.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
  ResponseDto createTransaction(TransactionDto transactionDto);
  StatisticsDto createStatistics();
  void deleteAllTransactions();
}
