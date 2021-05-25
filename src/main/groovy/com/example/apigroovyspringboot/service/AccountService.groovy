package com.example.apigroovyspringboot.service

import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService extends BaseService<Account>{

    @Autowired
    AccountRepository repository

}
