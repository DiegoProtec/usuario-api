package com.desafio.endereco.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoVo(
        Long id,

        @NotBlank(message = "O Código postal(CEP) é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos")
        String cep,

        @NotBlank(message = "O logradouro (RUA, AVENIDA, CONJUNTO...) é obrigatório")
        String logradouro,

        @NotBlank(message = "O número é obrigatório")
        @Pattern(regexp = "^[1-9]\\d{0,7}$", message = "Deve conter entre 1 e 8 dígitos")
        String numero,

        @NotBlank(message = "O complemento é obrigatório")
        @Size(min = 2, max = 50, message = "Complemento deve ter entre 2 e 50 caracteres")
        String complemento,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(min = 2, max = 50, message = "Bairro deve ter entre 2 e 50 caracteres")
        String bairro,

        @NotBlank(message = "O localidade é obrigatório")
        @Size(min = 2, max = 50, message = "Localidade deve ter entre 2 e 50 caracteres")
        String localidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 50, message = "Estado deve ter entre 2 e 50 caracteres")
        String estado) {
}