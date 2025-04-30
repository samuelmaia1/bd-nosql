package com.bd.nosql.repository;

import com.bd.nosql.model.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends MongoRepository<Transaction, UUID> {

    @Aggregation(pipeline = {
            "{ $sort: { amount: ?0 }}"
    })
    List<Transaction> findOrderByAmount(int sortDirection);
}
