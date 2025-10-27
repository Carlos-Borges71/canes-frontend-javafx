package com.canes.model;

import java.util.List;

public class Produto {

    private Long id;
    private String codigo;
    private String nome;
    private Integer estoque;
    private Double valorCompra;
    private Double valorVenda;
    private Integer quantcompra;

    private Fornecedor fornecedor;
    private List<Pedido> pedidos;

    public Produto() {
    }

    public Produto(String codigo, String nome, Integer estoque, Double valorCompra,
            Double valorVenda, Integer quantcompra) {

        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantcompra = quantcompra;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
