package com.canes;

public class tblExibirPedido {

    private Integer item;
    private String produto;
    private Integer quant;
    private Double valorUnitario;
    private Double total;


    public tblExibirPedido(Integer item, String produto, Integer quant, Double valorUnitario, Double total) {
        this.item = item;
        this.produto = produto;
        this.quant = quant;
        this.valorUnitario = valorUnitario;
        this.total = total;
    }


    public Integer getItem() {
        return item;
    }


    public String getProduto() {
        return produto;
    }


    public Integer getQuant() {
        return quant;
    }


    public Double getValorUnitario() {
        return valorUnitario;
    }


    public Double getTotal() {
        return total;
    }

    
}
