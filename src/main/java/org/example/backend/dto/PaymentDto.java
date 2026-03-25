package org.example.backend.dto;

import org.example.backend.entity.Status;

import java.math.BigDecimal;

public record PaymentDto(
        Long id,
        String walletNumber,
        Long accountId,
        String email,
        String phoneNumber,
        BigDecimal amount,
        CurrencyDto currencyDto,
        String comment,
        Status status
) { }
