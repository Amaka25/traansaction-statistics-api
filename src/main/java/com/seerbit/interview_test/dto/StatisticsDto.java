package com.seerbit.interview_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor

public class StatisticsDto {
    private String sum;
    private String avg;
    private String max;
    private String min;
    private long count;
}
