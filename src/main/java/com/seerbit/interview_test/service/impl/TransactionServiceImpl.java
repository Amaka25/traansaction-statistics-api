package com.seerbit.interview_test.service.impl;

import com.seerbit.interview_test.dto.ResponseDto;
import com.seerbit.interview_test.dto.TransactionDto;
import com.seerbit.interview_test.exceptions.AppException;
import com.seerbit.interview_test.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public ResponseDto createTransaction(TransactionDto transactionDto) {
        String amount = transactionDto.getAmount();
        BigDecimal amountAsBigDecimal = getAmountAsBigDecimal(amount);
        System.out.println(amountAsBigDecimal);

        String timestamp = transactionDto.getTimestamp();
        Instant instant = getTimestampAsInstant(timestamp);
        System.out.println(instant);
        return null;
    }

    public BigDecimal getAmountAsBigDecimal(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (Exception e) {
            throw new AppException("Please enter a valid amount");
        }

    }
    public Instant getTimestampAsInstant(String timestamp) {
        try{
            return Instant.parse(timestamp);
        } catch (Exception e) {
            throw new AppException("Please enter a valid timestamp");
        }

    }

    public void validateOlderTimestamps(String timestamp, Instant instant) {
        Instant timestamp2 = Instant.parse(timestamp); // Example timestamp
        Instant now = Instant.now();

        Duration duration = Duration.between(timestamp2, now);
        long seconds = duration.getSeconds();
        if (seconds > 30) {
            throw new AppException("Please enter a valid timestamp");
        }
    }
}
