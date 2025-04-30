package com.bd.nosql.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TransactionResponseDto(
        @NotNull
        String id,
        @NotNull
        String title,
        @NotNull @Positive
        Double amount,
        @NotBlank @NotNull
        String description,
        @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        @NotNull
        String category,
        @NotNull
        String type
) {
}
