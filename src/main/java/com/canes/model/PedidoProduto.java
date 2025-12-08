package com.canes.model;

public class PedidoProduto {

    private Integer quant;
    private Double valor;

    PedidoProduto(){

    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant, Double valor) {
        this.quant = quant;
        this.valor =valor;
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

    
    
}
