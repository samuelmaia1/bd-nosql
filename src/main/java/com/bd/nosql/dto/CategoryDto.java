package com.bd.nosql.dto;

import java.util.List;

public class CategoryDto {
    private String category;
    private Double total;
    private List<TransactionResponseDto> transactions;

    public CategoryDto() {
    }

    public CategoryDto(String category, Double total, List<TransactionResponseDto> transactions) {
        this.category = category;
        this.total = total;
        this.transactions = transactions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<TransactionResponseDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponseDto> transactions) {
        this.transactions = transactions;
    }
}
