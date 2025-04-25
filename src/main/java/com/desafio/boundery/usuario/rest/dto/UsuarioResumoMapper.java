package com.desafio.boundery.usuario.rest.dto;

import com.desafio.boundery.usuario.domain.UsuarioVo;
import com.desafio.boundery.usuario.rest.dto.response.UsuarioResumoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioResumoMapper {

    UsuarioResumoMapper INSTANCE = Mappers.getMapper(UsuarioResumoMapper.class);

    UsuarioResumoDto toDto(UsuarioVo vo);
}
