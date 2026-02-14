package com.canes.model.dpo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDPO {
    private Long id;
    private Long item;
    private String codigo;
    private String produto;
    private Integer quant;
    private Double valorUnitario;
    private Double total;
    private String cliente;
    private String status;
    private String pagamento;
    private String data;

    @JsonIgnore
    private boolean totalRow;

    public PedidoDPO() {

    }

    public PedidoDPO(Long id) {
        this.id = id;
    }

    public PedidoDPO(Long item, String codigo, String produto, Integer quant, Double valorUnitario, Double total,
            String cliente, String status, String pagamento, String data, Long id) {
        this.item = item;
        this.codigo = codigo;
        this.produto = produto;
        this.quant = quant;
        this.valorUnitario = valorUnitario;
        this.total = total;
        this.cliente = cliente;
        this.status = status;
        this.pagamento = pagamento;
        this.data = data;
        this.id = id;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
