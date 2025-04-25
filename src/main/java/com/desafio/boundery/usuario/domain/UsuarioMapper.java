package com.desafio.boundery.usuario.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioVo toVo(Usuario usuario);

    Usuario toEntity(UsuarioVo vo);
}
