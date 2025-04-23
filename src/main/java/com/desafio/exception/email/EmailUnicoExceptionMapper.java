package com.desafio.exception.email;

import com.desafio.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailUnicoExceptionMapper implements ExceptionMapper<EmailUnicoException> {

    @Override
    public Response toResponse(EmailUnicoException exception) {
        return Response
                .status(Response.Status.CONFLICT)
                .entity(new ErrorResponse("Conflito", exception.getMessage()))
                .build();
    }
}

