package com.desafio.boundery.usuario.rest;

import com.desafio.boundery.endereco.application.EnderecoService;
import com.desafio.boundery.endereco.domain.EnderecoVo;
import com.desafio.boundery.usuario.application.UsuarioService;
import com.desafio.boundery.usuario.domain.UsuarioVo;
import com.desafio.boundery.usuario.rest.dto.UsuarioResumoMapper;
import com.desafio.boundery.usuario.rest.dto.response.UsuarioResumoDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    EnderecoService enderecoService;

    @Inject
    UsuarioService usuarioService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaUsuarios(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<UsuarioVo> usuariosVo = usuarioService.buscarTodos(page, size);
        List<UsuarioResumoDto> usuarios = usuariosVo.stream()
                .map(UsuarioResumoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return Response.ok(usuarios).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registraUsuario(@Valid UsuarioVo usuarioVo) {
        EnderecoVo enderecoVo = enderecoService.registrar(usuarioVo.endereco());
        UsuarioVo voResponse = usuarioService.registrar(enderecoVo, usuarioVo);

        var location = UriBuilder.fromResource(UsuarioResource.class)
                .path(String.valueOf(voResponse.id()))
                .build();

        return Response.created(location).entity(voResponse).build();
    }

}
