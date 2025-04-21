package com.desafio.endereco.repository;

import com.desafio.endereco.domain.Endereco;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnderecoRepository {

    @Transactional
    public void save(Endereco entity) {
        entity.persist();
    }

}
