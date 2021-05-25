package com.example.apigroovyspringboot.exception

class NotFoundException extends ServiceException{

    private static final String MSG_ERROR_ENTITY_NOT_FOUND = "Nenhum registro encontrado!"

    NotFoundException(String message = MSG_ERROR_ENTITY_NOT_FOUND) {
        super(message)
    }
}
