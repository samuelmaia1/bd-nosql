package com.bd.nosql.controller;

import com.bd.nosql.dto.TransactionDto;
import com.bd.nosql.dto.TransactionRequestPutDto;
import com.bd.nosql.dto.TransactionResponseDto;
import com.bd.nosql.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<?>> getAll(@RequestParam String group){
        if (group.equals("category"))
            return ResponseEntity.status(HttpStatus.OK).body(service.groupByCategory());
        if (group.equals("type"))
            return ResponseEntity.status(HttpStatus.OK).body(service.groupByTypeAndCategory());
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable UUID id){
        return ResponseEntity.ok(service.getTransactionById(id));
    }

    @GetMapping("/orderByAmount")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionsOrderByAmount(@RequestParam int sort){
        return ResponseEntity.ok(service.getTransactionsOrderByAmount(sort));
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody @Valid TransactionDto data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addTransaction(data));
    }

    @PutMapping("{id}")
    public ResponseEntity<TransactionResponseDto> putTransaction(@PathVariable UUID id ,@RequestBody @Valid TransactionRequestPutDto data){
        return ResponseEntity.ok(service.putTransactionById(id, data));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TransactionResponseDto> deleteTransaction(@PathVariable UUID id){
        service.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}