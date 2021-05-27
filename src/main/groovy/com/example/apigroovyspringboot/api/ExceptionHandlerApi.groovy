package com.example.apigroovyspringboot.api

import com.example.apigroovyspringboot.exception.AccountLimitException
import com.example.apigroovyspringboot.exception.ErrorInfo
import groovy.json.JsonOutput
import groovy.util.logging.Slf4j
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import javax.servlet.http.HttpServletRequest

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
class ExceptionHandlerApi{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {

        new ErrorInfo(url: req.requestURL.toString(), errorCode: HttpStatus.BAD_REQUEST.value(), message: ex.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorInfo handleValidationExceptions(HttpServletRequest req, MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>()

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).field

            String errorMessage = error.defaultMessage

            errors.put(fieldName, errorMessage)

        })

        new ErrorInfo(url: req.requestURL.toString(), errorCode: HttpStatus.BAD_REQUEST.value(), message: ex.message, errors: errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountLimitException.class)
    @ResponseBody
    ErrorInfo handleAccountLimitBadRequest(HttpServletRequest req, Exception ex) {

        new ErrorInfo(url: req.requestURL.toString(), errorCode: HttpStatus.BAD_REQUEST.value(), message: ex.message)
    }

}
