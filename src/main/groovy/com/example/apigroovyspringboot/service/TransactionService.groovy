package com.example.apigroovyspringboot.service

import com.example.apigroovyspringboot.model.OperationType
import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService extends BaseService<Transaction>{

    @Autowired
    TransactionRepository repository

    @Autowired
    OperationTypeService operationTypeService

    @Override
    @Transactional
    Transaction save(Transaction entity) {

        OperationType operationType = operationTypeService.findById(entity.operationType.id)

        entity.amount = entity.amount * operationType.value

        return super.save(entity)
    }
}
