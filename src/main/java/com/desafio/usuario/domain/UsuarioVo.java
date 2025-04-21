package com.desafio.usuario.domain;

import com.desafio.endereco.domain.EnderecoVo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record UsuarioVo(
        Long id,

        @Email(message = "Email deve ser válido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 13, max = 13, message = "telefone deve conter 13 caracteres")
        String telefone,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O sobrenome é obrigatório")
        @Size(min = 2, max = 50, message = "Sobrenome deve conter entre 2 e 50 caracteres")
        String sobrenome,

        @NotNull(message = "O endereco é obrigatório")
        @Valid
        EnderecoVo endereco
) {

}


