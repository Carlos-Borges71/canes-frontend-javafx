package com.canes.model.dpo;

import com.canes.model.Fornecedor;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDPO {

    private Long id;
    private String codigo;
    private String nome;
    private Double valorCompra;
    private Double valorVenda;
    private Integer quantcompra;
    private Fornecedor fornecedor;
    private NotaFiscalDTO nota;

    public ProdutoDPO() {

    }

    public ProdutoDPO(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQuantcompra() {
        return quantcompra;
    }

    public void setQuantcompra(Integer quantcompra) {
        this.quantcompra = quantcompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public NotaFiscalDTO getNota() {
        return nota;
    }

    public void setNota(NotaFiscalDTO nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
