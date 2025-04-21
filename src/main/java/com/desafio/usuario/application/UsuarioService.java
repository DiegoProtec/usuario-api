package com.desafio.usuario.application;

import com.desafio.endereco.domain.Endereco;
import com.desafio.endereco.domain.EnderecoMapper;
import com.desafio.endereco.domain.EnderecoVo;
import com.desafio.usuario.domain.Usuario;
import com.desafio.usuario.domain.UsuarioMapper;
import com.desafio.usuario.domain.UsuarioVo;
import com.desafio.usuario.exception.email.EmailUnicoException;
import com.desafio.usuario.exception.negocio.NegocioException;
import com.desafio.usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<UsuarioVo> buscarTodos(int page, int size) {
        return usuarioRepository.findAll(page, size).stream()
                .map(UsuarioMapper.INSTANCE::toVo)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioVo registrar(EnderecoVo enderecoVo, UsuarioVo usuarioVo) {
        if (usuarioRepository.emailExists(usuarioVo.email()))
            throw new EmailUnicoException("Email já cadastrado: " + usuarioVo.email());

        try {
            Endereco endereco = EnderecoMapper.INSTANCE.toEntity(enderecoVo);
            Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioVo);
            usuario.setEndereco(endereco);
            usuarioRepository.save(usuario);
            return UsuarioMapper.INSTANCE.toVo(usuario);
        } catch (Exception e) {
            throw new NegocioException("Erro ao criar usuário", e);
        }
    }

/*    public UsuarioVo buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioMapper.INSTANCE::toVo)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }*/

}
