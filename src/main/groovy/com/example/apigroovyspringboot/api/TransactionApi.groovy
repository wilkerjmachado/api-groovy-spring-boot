package com.example.apigroovyspringboot.api


import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/transactions")
class TransactionApi extends BaseApi<Transaction, TransactionService>{

    @Autowired
    TransactionService service

    @Override
    ResponseEntity<Transaction> save(@Valid @RequestBody Transaction entidade) {

        return ResponseEntity.ok(service.processTransaction(entidade))
    }
}
