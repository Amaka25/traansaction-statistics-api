package com.seerbit.interview_test.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;


@Data
public class TransactionDto {
    @NotBlank(message = "Amount is required")
    private String amount;

    @NotBlank(message = "Timestamp must be in ISO 8601 format")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private String timestamp;



}
