package com.canes.model;

public class PedidoProduto {
    private Long id;
    private Integer quant;
    private Double valor;
    private Pedido pedido;
    private Produto produto;

    public PedidoProduto() {
    }

    public PedidoProduto(Long id, Integer quant, Double valor, Pedido pedido, Produto produto) {
        this.id = id;
        this.quant = quant;
        this.valor = valor;
        this.pedido = pedido;
        this.produto = produto;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant, Double valor) {
        this.quant = quant;
        this.valor = valor;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
