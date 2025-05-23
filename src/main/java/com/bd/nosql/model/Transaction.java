package com.bd.nosql.model;

import com.bd.nosql.dto.TransactionDto;
import com.bd.nosql.dto.TransactionRequestPutDto;
import com.bd.nosql.dto.TransactionResponseDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private UUID id = UUID.randomUUID();

    private String title;

    private String description;

    private LocalDate date;

    private Double amount;

    private String type;

    private String category;

    public Transaction (){}

    public Transaction(TransactionDto dto) {
        this.amount = dto.amount();
        this.category = dto.category();
        this.type = dto.type();
        this.description = dto.description();
        this.date = dto.date();
        this.title = dto.title();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public TransactionResponseDto toDto() {
        return new TransactionResponseDto(
                this.id.toString(),
                this.title,
                this.amount,
                this.description,
                this.date,
                this.category,
                this.type
        );
    }

    public void updateTransaction(TransactionRequestPutDto data){
        if(data.title() != null)
            title = data.title();
        if(data.amount() != null)
            amount = data.amount();
        if(data.description() != null)
            description = data.description();
        if(data.category() != null)
            category = data.category();
        if(data.type() != null)
            type = data.type();
    }
}

