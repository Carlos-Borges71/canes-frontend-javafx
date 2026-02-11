package com.canes.model;

import java.util.Set;

public class Fornecedor {

    private Long id;
    private String empresa;
    private String cnpjCpf;

    private Telefone telefones;
    private Endereco endereco;

    private Set<Produto> produtos;

    private Set<NotaFiscal> notasFiscais;

    public Fornecedor() {
    }

    public Fornecedor(Long id) {
        this.id = id;
    }

    public Fornecedor(Long id, String empresa, String cnpjCpf, Telefone telefones, Endereco endereco,
            Set<Produto> produtos) {
        this.id = id;
        this.empresa = empresa;
        this.cnpjCpf = cnpjCpf;
        this.telefones = telefones;
        this.endereco = endereco;
        this.produtos = produtos;
        // this.notasFiscais = notasFiscais;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTelefones(Telefone telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Fornecedor [empresa=" + empresa + ", cnpjCpf=" + cnpjCpf + ", endereco=" + endereco + ", produtos="
                + produtos + ", notasFiscais=" + notasFiscais + "]";
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setNotasFiscais(Set<NotaFiscal> notasFiscais) {
        this.notasFiscais = notasFiscais;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public Telefone getTelefones() {
        return telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Set<NotaFiscal> getNotasFiscais() {
        return notasFiscais;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

}
