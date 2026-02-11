package com.canes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Telefone {

    private Long id;
    private String numero;
    private Usuario operador;
    private Fornecedor fornecedor;
    private Cliente cliente;

    public Telefone() {
    }

    public Telefone(String numero) {

        this.numero = numero;

    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getOperador() {
        return operador;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setOperador(Usuario operador) {
        this.operador = operador;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
