package com.bd.nosql.service;

import com.bd.nosql.dto.*;
import com.bd.nosql.model.Transaction;
import com.bd.nosql.repository.TransactionRepository;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private MongoTemplate template;

    public TransactionResponseDto addTransaction(TransactionDto  data) {
        return repository.save(new Transaction(data)).toDto();
    }

    public List<TransactionResponseDto> getAllTransactions() {
        return repository
                .findAll()
                .stream()
                .map(Transaction::toDto)
                .toList();
    }

    public TransactionResponseDto getTransactionById(UUID id) {
        Optional<Transaction> transaction = repository.findById(id);
        if (transaction.isEmpty())
            throw new RuntimeException("Transação não encontrada!");
        return transaction.get().toDto();
    }

    public List<CategoryDto> groupByCategory() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation
                    .group("category")
                    .sum("amount").as("total")
                    .push("$$ROOT").as("transactions"),
                Aggregation
                    .project()
                    .andExpression("_id").as("category")
                    .andInclude("total", "transactions")
        );

        AggregationResults<Document> results = template.aggregate(
                aggregation,
                "transactions",
                Document.class
        );

        return results.getMappedResults()
            .stream()
            .map(document -> {
                String category = document.getString("category");
                Double total = document.getDouble("total");

                List<Document> rawTransactions = (List<Document>) document.get("transactions");

                List<TransactionResponseDto> transactionResponseDtos = rawTransactions
                    .stream()
                    .map(doc -> template.getConverter().read(Transaction.class, doc))
                    .map(Transaction::toDto)
                    .toList();

                return new CategoryDto(category, total, transactionResponseDtos);
        }).toList();

    }

    public List<TypeGroupDto> groupByTypeAndCategory() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation
                        .group("type", "category")
                        .sum("amount").as("total")
                        .push("$$ROOT").as("transactions"),

                Aggregation
                        .project()
                        .and("_id.type").as("type")
                        .and("_id.category").as("category")
                        .andInclude("total", "transactions"),

                Aggregation
                        .group("type")
                        .push(
                                new BasicDBObject("category", "$category")
                                        .append("total", "$total")
                                        .append("transactions", "$transactions")
                        ).as("categories"),

                Aggregation
                        .project()
                        .and("_id").as("type")
                        .and("categories").as("categories")
        );

        AggregationResults<Document> results = template.aggregate(
                aggregation,
                "transactions",
                Document.class
        );

        return results.getMappedResults()
                .stream()
                .map(doc -> {
                    String type = doc.getString("type");
                    List<Document> categoriesDocs = (List<Document>) doc.get("categories");

                    List<CategoryDto> categoryDtos = categoriesDocs.stream().map(catDoc -> {
                        String category = catDoc.getString("category");
                        Double total = catDoc.getDouble("total");
                        List<Document> rawTransactions = (List<Document>) catDoc.get("transactions");

                        List<TransactionResponseDto> transactions = rawTransactions.stream()
                                .map(d -> template.getConverter().read(Transaction.class, d))
                                .map(Transaction::toDto)
                                .toList();

                        return new CategoryDto(category, total, transactions);
                    }).toList();

                    return new TypeGroupDto(type, categoryDtos);
                }).toList();
    }


    public List<TransactionResponseDto> getTransactionsOrderByAmount(int sortDirection) {
        return repository
                .findOrderByAmount(sortDirection)
                .stream()
                .map(Transaction::toDto).toList();
    }

    public TransactionResponseDto putTransactionById(UUID id, TransactionRequestPutDto data){
        Optional<Transaction> transaction = repository.findById(id);
        if(transaction.isEmpty()){
            throw new RuntimeException("Transação não encontrada!");
        }
        transaction.get().updateTransaction(data);
        repository.save(transaction.get());

        return transaction.get().toDto();
    }

    public void deleteTransaction(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Transação não encontrada!");
        }
        repository.deleteById(id);
    }
}
