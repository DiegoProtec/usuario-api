package com.desafio.boundery.usuario.application;

import com.desafio.boundery.endereco.domain.Endereco;
import com.desafio.boundery.endereco.domain.EnderecoMapper;
import com.desafio.boundery.endereco.domain.EnderecoVo;
import com.desafio.boundery.usuario.domain.Usuario;
import com.desafio.boundery.usuario.domain.UsuarioMapper;
import com.desafio.boundery.usuario.domain.UsuarioVo;
import com.desafio.boundery.usuario.repository.UsuarioRepository;
import com.desafio.exception.negocio.NegocioException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    @Inject
    UsuarioRepository usuarioRepository;

    public List<UsuarioVo> buscarTodos(int page, int size) {
        try {
            return usuarioRepository.findAll(page, size).stream()
                    .map(UsuarioMapper.INSTANCE::toVo)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            if (e.getCause() instanceof InternalServerErrorException) throw e;
            var mensagem = "USUARIOS: error ao buscar registros";
            LOG.error(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

    @Transactional
    public UsuarioVo registrar(EnderecoVo enderecoVo, UsuarioVo usuarioVo) {
        if (usuarioRepository.emailExists(usuarioVo.email()))
            throw new NegocioException("Email já cadastrado: " + usuarioVo.email());

        try {
            Endereco endereco = EnderecoMapper.INSTANCE.toEntity(enderecoVo);
            Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioVo);
            usuario.setEndereco(endereco);
            usuarioRepository.save(usuario);
            return UsuarioMapper.INSTANCE.toVo(usuario);
        } catch (Exception e) {
            if (e.getCause() instanceof InternalServerErrorException) throw e;
            var mensagem = "USUARIOS: error ao registrar dados";
            LOG.error(mensagem);
            throw new InternalServerErrorException(mensagem);
        }
    }

}
