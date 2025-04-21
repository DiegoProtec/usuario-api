package com.desafio.endereco.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoVo toVo(Endereco usuario);

    Endereco toEntity(EnderecoVo vo);

}
