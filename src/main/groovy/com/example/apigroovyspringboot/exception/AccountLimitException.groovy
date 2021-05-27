package com.example.apigroovyspringboot.exception

class AccountLimitException extends ServiceException{

    AccountLimitException(String message) {

        super(message)
    }
}
