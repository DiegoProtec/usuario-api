package com.desafio.usuario.exception.negocio;

public class NegocioException extends RuntimeException {

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
