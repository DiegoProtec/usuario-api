package com.desafio.boundery.endereco.repository;

import com.desafio.boundery.endereco.domain.Endereco;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EnderecoRepository {

    private static final Logger LOG = LoggerFactory.getLogger(EnderecoRepository.class);

    @Transactional
    public void save(Endereco entity) {
        entity.persist();
        if (!entity.isPersistent()) {
            var mensagem = "Não foi possível persistir a entidade";
            LOG.info(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

}
