package com.bd.nosql.service;

import com.bd.nosql.dto.TransactionDto;
import com.bd.nosql.dto.TransactionRequestPutDto;
import com.bd.nosql.dto.TransactionResponseDto;
import com.bd.nosql.model.Transaction;
import com.bd.nosql.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public TransactionResponseDto addTransaction(TransactionDto  data) {
        return repository.save(new Transaction(data)).toDto();
    }

    public List<TransactionResponseDto> getAllTransactions() {
        return repository
                .findAll()
                .stream()
                .map(transaction -> transaction.toDto())
                .toList();
    }

    public TransactionResponseDto getTransactionById(UUID id) {
        Optional<Transaction> transaction = repository.findById(id);
        if (transaction.isEmpty())
            throw new RuntimeException("Transação não encontrada!");
        return transaction.get().toDto();
    }

    public List<TransactionResponseDto> getTransactionsOrderByAmount(int sortDirection) {
        return repository
                .findOrderByAmount(sortDirection)
                .stream()
                .map(transaction -> transaction.toDto()).toList();
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
