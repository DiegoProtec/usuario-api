package com.desafio.boundery.usuario.repository;

import com.desafio.boundery.usuario.domain.Usuario;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioRepository.class);

    public List<Usuario> findAll(int page, int size) {
        try {
            return Usuario.findAll().page(Page.of(page, size)).list();
        } catch (Exception e) {
            var mensagem = "Não foi possível realizar a consulta";
            LOG.info(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

    @Transactional
    public void save(Usuario entity) {
        entity.persist();
        if (!entity.isPersistent()) {
            var mensagem = "Não foi possível persistir a entidade";
            LOG.info(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

    public boolean emailExists(String email) {
        try {
            return Usuario.count("email", email) > 0;
        } catch (Exception e) {
            var mensagem = "Não foi possível realizar validar o usuário";
            LOG.info(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

}
