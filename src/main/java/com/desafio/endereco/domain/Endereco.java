package com.desafio.endereco.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Endereco extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 8)
    private String cep;
    @Column(nullable = false, length = 50)
    private String logradouro;
    @Column(nullable = false, length = 20)
    private String numero;
    @Column(nullable = false, length = 20)
    private String complemento;
    @Column(nullable = false, length = 50)
    private String bairro;
    @Column(nullable = false, length = 50)
    private String localidade;
    @Column(nullable = false, length = 20)
    private String estado;

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
