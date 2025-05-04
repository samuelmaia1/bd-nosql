package com.bd.nosql.dto;

import jakarta.validation.constraints.Positive;

public record TransactionRequestPutDto(
        String title,
        @Positive
        Double amount,
        String description,
        String category,
        String type
) {
}
