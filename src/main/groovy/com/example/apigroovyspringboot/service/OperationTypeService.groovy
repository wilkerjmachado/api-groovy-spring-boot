package com.example.apigroovyspringboot.service


import com.example.apigroovyspringboot.model.OperationType
import com.example.apigroovyspringboot.repository.OperationTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OperationTypeService extends BaseService<OperationType>{

    @Autowired
    OperationTypeRepository repository

}
