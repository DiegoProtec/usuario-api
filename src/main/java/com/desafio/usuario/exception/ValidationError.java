package com.desafio.usuario.exception;

public record ValidationError(String field, String message) {
}
