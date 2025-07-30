package com.seerbit.interview_test.service.impl;

import com.seerbit.interview_test.dto.ResponseDto;
import com.seerbit.interview_test.dto.StatisticsDto;
import com.seerbit.interview_test.dto.TransactionDto;
import com.seerbit.interview_test.exceptions.AppException;
import com.seerbit.interview_test.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    List<TransactionDto> transactions = new ArrayList<>();

    @Override
    public ResponseDto createTransaction(TransactionDto transactionDto) {
        String amount = transactionDto.getAmount();
        BigDecimal amountAsBigDecimal = getAmountAsBigDecimal(amount);
        System.out.println(amountAsBigDecimal);

        String timestamp = transactionDto.getTimestamp();
        Instant instant = getTimestampAsInstant(timestamp);
        transactions.add(transactionDto);

        System.out.println(transactions);
        return null;
    }

    @Override
    public StatisticsDto createStatistics() {


         List<TransactionDto> last30secs = transactions.stream()
                 .filter(dto -> {
                     Instant parsedTimestamp = Instant.parse(dto.getTimestamp());
                     Instant time30SecondsAgo = Instant.now().minusSeconds(30);
                     return !parsedTimestamp.isBefore(time30SecondsAgo);
                 })
                 .collect(Collectors.toList());

        System.out.println("last30secs: " + last30secs);

         BigDecimal sum = last30secs.stream()
                 .map(amount -> new BigDecimal(amount.getAmount()))
                 .reduce(BigDecimal.ZERO, BigDecimal::max );

         long count = last30secs.size();

         BigDecimal avg = count == 0 ? BigDecimal.ZERO
                 : sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);

        Optional<BigDecimal> max = last30secs.stream()
                .map(amount -> new BigDecimal(amount.getAmount()))
                .max(Comparator.naturalOrder());

        Optional<BigDecimal> min = last30secs.stream()
                .map(amount -> new BigDecimal(amount.getAmount()))
                .min(Comparator.naturalOrder());

        String sumStr = sum.setScale(2, RoundingMode.HALF_UP).toString();
        String avgStr = avg.setScale(2, RoundingMode.HALF_UP).toString();
        String maxStr = max.orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP).toString();
        String minStr = min.orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP).toString();

        return new StatisticsDto(sumStr, avgStr, maxStr, minStr, count);
    }

    @Override
    public void deleteAllTransactions() {
        transactions.clear();
        System.out.println(transactions);// removes all elements
    }

    public BigDecimal getAmountAsBigDecimal(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (Exception e) {
            throw new AppException("Please enter a valid amount", 422);
        }

    }
    public Instant getTimestampAsInstant(String timestamp) {

        Instant instant;
        try {
            instant = Instant.parse(timestamp);
        } catch (DateTimeParseException e) {
            throw new AppException("Please enter a valid timestamp", 422);
        }

        // Now validate if it's too old
        validateOlderTimestamps(timestamp);
        validateFutureTimestamps(timestamp);
        return instant;

    }

    public void validateOlderTimestamps(String timestamp) {
        Instant timestamp2 = Instant.parse(timestamp); // Example timestamp
        Instant now = Instant.now();

        Duration duration = Duration.between(timestamp2, now);

        System.out.println("Older timestamp: " + timestamp2);
        System.out.println("current timestamp: " + now);
        System.out.println("duration: " + duration.getSeconds());
        if (duration.getSeconds() > 30) {
            throw new AppException("Older timestamp", 204);
        }


    }

    public void validateFutureTimestamps(String timestamp) {
        Instant timestamp2 = Instant.parse(timestamp); // Example timestamp
        Instant now = Instant.now();

        Duration duration = Duration.between(now, timestamp2);
        System.out.println("---Timestamp NOW"+now);
        System.out.println("TIMESTAMP ENTERED"+timestamp2);
        System.out.println(duration);

        if (duration.getSeconds() > 30) {
            throw new AppException("Timestamp is in the future", 422);
        }


    }

    public void createStatistics(String timestamp) {


//        while (duration.getSeconds() < 30) {
//
//        }
    }
}
