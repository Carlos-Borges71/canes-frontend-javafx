package com.canes.model.dpo;

public class PedidoDPO {

    private Integer item;
    private String codigo;
    private String produto;
    private Integer quant;
    private Double valorUnitario;
    private Double total;

    public PedidoDPO(){
        
    }

    public PedidoDPO(Integer item, String codigo,  String produto, Integer quant, Double valorUnitario, Double total) {
        this.item = item;
        this.codigo = codigo;
        this.produto = produto;
        this.quant = quant;
        this.valorUnitario = valorUnitario;
        this.total = total;
    }


    public Integer getItem() {
        return item;
    }

    public String getCodigo() {
        return codigo;
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
