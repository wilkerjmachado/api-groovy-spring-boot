package com.example.apigroovyspringboot.api


import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionApi extends BaseApi<Transaction, TransactionService>{

    @Autowired
    TransactionService service

}
