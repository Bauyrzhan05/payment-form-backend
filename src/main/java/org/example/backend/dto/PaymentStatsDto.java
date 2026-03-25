package org.example.backend.dto;

import java.util.List;

public record PaymentStatsDto(
        Long totalCount,
        Integer totalAmount,
        List<DailyStatsDto> dailyStats
) {
    public record DailyStatsDto(
            String date,
            Long count,
            Integer amount
    ) {
    }
}
