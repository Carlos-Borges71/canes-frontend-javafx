package com.canes.model;

import java.time.Instant;
import java.util.List;

public class Pedido {

    private Integer id;
    private List<Produto> produtos;
    private String status;
    private Double valor;
    private Instant data;

    public Pedido() {
    }

    public Pedido(Integer id, String status,  Double valor, Instant data, List<Produto> produtos) {
        this.id = id;
        this.status = status;
        this.valor = valor;
        this.data = data;
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
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
