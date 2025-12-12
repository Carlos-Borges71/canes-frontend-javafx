package com.canes.model;

import java.util.List;

public class NotaFiscal {
    private long id;
    private Integer notaFiscal;
    private String data;
    private List<Produto> produtos;
    private Fornecedor fornecedor;

    public NotaFiscal() {
        
    }

    public NotaFiscal(Integer notaFiscal, String data, Fornecedor fornecedor) {
        this.notaFiscal = notaFiscal;
        this.data = data;
        this.fornecedor = fornecedor;
        
    }

    public long getId() {
        return id;
    }

    public Integer getNotaFiscal() {
        return notaFiscal;
    }

    public String getData() {
        return data;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

     public List<Produto> getProduto() {
        return produtos;
    }

     public void setId(long id) {
         this.id = id;
     }

     public void setNotaFiscal(Integer notaFiscal) {
         this.notaFiscal = notaFiscal;
     }

     public void setData(String data) {
         this.data = data;
     }

     public List<Produto> getProdutos() {
         return produtos;
     }

     public void setProdutos(List<Produto> produtos) {
         this.produtos = produtos;
     }

     public void setFornecedor(Fornecedor fornecedor) {
         this.fornecedor = fornecedor;
     }

    

}
