package com.canes.model.dpo;

public class PedidoDPO {

    private Long item;
    private String codigo;
    private String produto;
    private Integer quant;
    private Double valorUnitario;
    private Double total;

    private boolean totalRow;

   

    public PedidoDPO(){
        
    }

    public PedidoDPO(Long item, String codigo,  String produto, Integer quant, Double valorUnitario, Double total) {
        this.item = item;
        this.codigo = codigo;
        this.produto = produto;
        this.quant = quant;
        this.valorUnitario = valorUnitario;
        this.total = total;
    }

     public boolean isTotalRow() {
        return totalRow;
    }

    public void setTotalRow(boolean totalRow) {
        this.totalRow = totalRow;
    }


    public Long getItem() {
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

    public void setItem(Long item) {
        this.item = item;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
