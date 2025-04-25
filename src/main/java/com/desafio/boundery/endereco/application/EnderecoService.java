package com.desafio.boundery.endereco.application;

import com.desafio.boundery.endereco.domain.Endereco;
import com.desafio.boundery.endereco.domain.EnderecoMapper;
import com.desafio.boundery.endereco.domain.EnderecoVo;
import com.desafio.boundery.endereco.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EnderecoService {

    private static final Logger LOG = LoggerFactory.getLogger(EnderecoService.class);

    @Inject
    EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoVo registrar(EnderecoVo vo) {
        try {
            Endereco endereco = EnderecoMapper.INSTANCE.toEntity(vo);
            enderecoRepository.save(endereco);
            return EnderecoMapper.INSTANCE.toVo(endereco);
        } catch (Exception e) {
            if (e.getCause() instanceof InternalServerErrorException) throw e;
            var mensagem = "ENDERECOS: error ao registrar dados";
            LOG.info(mensagem);
            throw new InternalServerErrorException(mensagem);
        }

    }

}
