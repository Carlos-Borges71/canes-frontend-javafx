package com.canes.model;

public class Pagamento {

    private Long id;
    private String data;
    private String tipo;
    private Double valorPagamento;
    private Pedido pedido;

    public Pagamento(){

    }

    public Pagamento(Long id, String data, String tipo, Double valorPagamento, Pedido pedido) {
        this.id = id;
        this.data = data;
        this.valorPagamento = valorPagamento;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    

}
