package com.desafio.endereco.application;

import com.desafio.endereco.domain.Endereco;
import com.desafio.endereco.domain.EnderecoMapper;
import com.desafio.endereco.domain.EnderecoVo;
import com.desafio.endereco.repository.EnderecoRepository;
import com.desafio.exception.negocio.NegocioException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoVo registrar(EnderecoVo vo) {
        try {
            Endereco endereco = EnderecoMapper.INSTANCE.toEntity(vo);
            enderecoRepository.save(endereco);
            return EnderecoMapper.INSTANCE.toVo(endereco);
        } catch (Exception e) {
            throw new NegocioException("Erro ao criar usuário", e);
        }
    }
}
