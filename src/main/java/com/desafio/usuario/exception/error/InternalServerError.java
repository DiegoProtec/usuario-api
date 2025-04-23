package com.desafio.usuario.exception.error;

public class InternalServerError extends RuntimeException {

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

}
