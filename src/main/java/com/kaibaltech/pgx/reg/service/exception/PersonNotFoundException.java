package com.kaibaltech.pgx.reg.service.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
