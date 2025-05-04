package com.bd.nosql.controller;

import com.bd.nosql.dto.TransactionDto;
import com.bd.nosql.dto.TransactionResponseDto;
import com.bd.nosql.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping("/add")
    public ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody @Valid TransactionDto data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addTransaction(data));
    }
}
