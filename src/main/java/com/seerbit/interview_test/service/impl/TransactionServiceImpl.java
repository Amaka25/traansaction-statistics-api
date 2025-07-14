package com.seerbit.interview_test.service.impl;

import com.seerbit.interview_test.dto.TransactionDto;
import com.seerbit.interview_test.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void createTransaction(TransactionDto transactionDto) {
        String amount = transactionDto.getAmount();
        BigDecimal amountAsBigDecimal = getAmountAsBigDecimal(amount);
        System.out.println(amountAsBigDecimal);

        String timestamp = transactionDto.getTimestamp();
        Instant instant = getTimestampAsInstant(timestamp);
        System.out.println(instant);
    }

    public BigDecimal getAmountAsBigDecimal(String amount) {
        try {
            if (amount != null) {
                return new BigDecimal(amount);
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("error, enter a valid amount");
            return null;
        }

    }
    public Instant getTimestampAsInstant(String timestamp) {
        try{
            if (timestamp != null) {
                return Instant.parse(timestamp);
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("error, enter a valid timestamp");
            throw new RuntimeException("error, enter a valid timestamp");
        }

    }
}
