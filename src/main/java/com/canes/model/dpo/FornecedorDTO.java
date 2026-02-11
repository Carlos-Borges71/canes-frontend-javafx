package com.canes.model.dpo;

public class FornecedorDTO {

    private Long id;
    private String empresa;
    private String cnpjCpf;

    private String telefones;
    private String endereco;

    private String produtos;

    private Integer notasFiscais;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Long id, String empresa, String cnpjCpf, String telefones, String endereco, String produtos,
            Integer notasFiscais) {
        this.id = id;
        this.empresa = empresa;
        this.cnpjCpf = cnpjCpf;
        this.telefones = telefones;
        this.endereco = endereco;
        this.produtos = produtos;
        this.notasFiscais = notasFiscais;

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

    public String getTelefones() {
        return telefones;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getNotasFiscais() {
        return notasFiscais;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public void setNotasFiscais(Integer notasFiscais) {
        this.notasFiscais = notasFiscais;
    }

}
