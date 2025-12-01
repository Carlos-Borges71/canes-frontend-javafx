package com.canes.model;

public class Pagamento {

    private Long id;
    private String data;
    private String tipo;
    private Double valorPagamento;

    public Pagamento(){

    }

    public Pagamento(Long id, String data, String tipo, Double valorPagamento) {
        this.id = id;
        this.data = data;
        this.valorPagamento = valorPagamento;
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
    

}
