package com.canes.model;

import java.time.Instant;
import java.util.List;


public class Pedido {

    private Long id;
    private List<Produto> produtos;
    private String status;
    private Double valor;
    private String data;
    private Cliente cliente;
    private List<Pagamento> pagamentos;
    private List<PedidoProduto> pedido;

    public Pedido() {
    }

    public Pedido(Long id, String status,  Double valor, String data, List<Produto> produtos, List<Pagamento> pagamentos,List<PedidoProduto> pedido) {
        this.id = id;
        this.status = status;
        this.valor = valor;
        this.data = data;
        this.produtos = produtos;
        this.pagamentos = pagamentos;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
    public List<PedidoProduto> getPedido() {
        return pedido;
    }

    public void setPedido(List<PedidoProduto> pedido) {
        this.pedido = pedido;
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
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
