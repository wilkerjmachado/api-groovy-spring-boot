package com.example.apigroovyspringboot.api

import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountApi extends BaseApi<Account, AccountService>{

    @Autowired
    AccountService service

}
