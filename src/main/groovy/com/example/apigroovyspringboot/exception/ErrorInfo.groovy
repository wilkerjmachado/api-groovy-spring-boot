package com.example.apigroovyspringboot.exception

class ErrorInfo {

    String url

    String message

    Map<String, String> errors

    int errorCode

    Throwable ex
}
