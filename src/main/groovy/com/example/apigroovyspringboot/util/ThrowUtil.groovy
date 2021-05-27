package com.example.apigroovyspringboot.util

import com.example.apigroovyspringboot.exception.AccountLimitException
import com.example.apigroovyspringboot.exception.ServiceException

class ThrowUtil {

    static void serviceExceptionThrow(boolean test, String message) {

        if (test) {

            throw ServiceException.newInstance(message)
        }
    }

    static void accountLimitExceptionThrow(boolean test, String message) {

        if (test) {

            throw AccountLimitException.newInstance(message)
        }
    }

}
