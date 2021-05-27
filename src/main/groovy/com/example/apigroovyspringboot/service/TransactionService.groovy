package com.example.apigroovyspringboot.service

import com.example.apigroovyspringboot.exception.NotFoundException
import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.model.OperationType
import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.repository.TransactionRepository
import com.example.apigroovyspringboot.util.ThrowUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService extends BaseService<Transaction>{

    @Autowired
    TransactionRepository repository

    @Autowired
    OperationTypeService operationTypeService

    @Autowired
    AccountService accountService

    @Override
    void validate(Transaction entity) {

        ThrowUtil.serviceExceptionThrow(!entity.operationType.asBoolean(), "Operation type is mandatory")

        ThrowUtil.serviceExceptionThrow(!entity.account.asBoolean(), "Account is mandatory")

        super.validate(entity)
    }

    @Transactional
    Transaction processTransaction(Transaction entity) {

        Account account = this.accountService.findById(entity.account.id)

        entity.operationType = this.operationTypeService.findById(entity.operationType.id)

        this.accountService.sensitizesLimit(account, entity)

        entity.amount = entity.amount * entity.operationType.value

        super.save(entity)

    }
}
