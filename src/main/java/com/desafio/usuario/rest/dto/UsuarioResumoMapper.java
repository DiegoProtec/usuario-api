package com.desafio.usuario.rest.dto;

import com.desafio.usuario.domain.UsuarioVo;
import com.desafio.usuario.rest.dto.response.UsuarioResumoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioResumoMapper {

    UsuarioResumoMapper INSTANCE = Mappers.getMapper(UsuarioResumoMapper.class);

    UsuarioResumoDto toDto(UsuarioVo vo);
}
