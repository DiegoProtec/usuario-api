package com.desafio.usuario.exception.email;

public class EmailUnicoException extends RuntimeException {
    public EmailUnicoException(String message) {
        super(message);
    }
}
