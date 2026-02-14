package com.canes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto {

    private Long id;
    private String codigo;
    private String nome;
    private Integer estoque;
    private Double valorCompra;
    private Double valorVenda;
    private Integer quantcompra;
    private String fornec;

    private Fornecedor fornecedor;
    private List<Pedido> pedidos;
    private NotaFiscal nota;

    public Produto() {
    }

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(Long id, String codigo, String nome, Integer estoque, Double valorCompra,
            Double valorVenda, Integer quantcompra, String fornec) {

        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantcompra = quantcompra;
        this.id = id;
        this.fornec = fornec;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String getFornec() {
        return fornec;
    }

    public NotaFiscal getNota() {
        return nota;
    }

    public void setNota(NotaFiscal nota) {
        this.nota = nota;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
