package com.canes.model;

import java.util.List;

public class Fornecedor {

    private Long id;
    private String empresa;
    private String cnpjCpf;

    private List<Telefone> telefones;
    private Endereco endereco;
    private NotaFiscal notasFiscais;
    private List<Produto> produtos;

    public Fornecedor() {
    }

    public Fornecedor(String empresa, String cnpjCpf) {

        this.empresa = empresa;
        this.cnpjCpf = cnpjCpf;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

   

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public NotaFiscal getNotasFiscais() {
        return notasFiscais;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fornecedor other = (Fornecedor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
