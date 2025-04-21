package com.desafio.usuario.repository;

import com.desafio.usuario.domain.Usuario;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository {

    public List<Usuario> findAll(int page, int size) {
        return Usuario.findAll().page(Page.of(page, size)).list();
    }

    @Transactional
    public void save(Usuario entity) {
        entity.persist();
    }

    public boolean emailExists(String email) {
        return Usuario.count("email", email) > 0;
    }

    public Optional<Usuario> findByEmail(String email) {
        return Usuario.find("email", email).firstResultOptional();
    }

}
