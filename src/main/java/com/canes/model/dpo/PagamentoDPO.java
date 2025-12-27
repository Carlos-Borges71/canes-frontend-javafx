package com.canes.model.dpo;

public class PagamentoDPO {

    private String Pagamento;
    private Double valor;

    public PagamentoDPO(){

    }

    public PagamentoDPO(String pagamento, Double valor) {
        Pagamento = pagamento;
        this.valor = valor;
    }

    public String getPagamento() {
        return Pagamento;
    }

    public void setPagamento(String pagamento) {
        Pagamento = pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
