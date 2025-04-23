package com.desafio.exception.email;

public class EmailUnicoException extends RuntimeException {
    public EmailUnicoException(String message) {
        super(message);
    }
}
