package com.canes.model;

public class NotaFiscal {
    private long id;
    private Integer notaFiscal;
    private String data;

    private Fornecedor fornecedor;

    public NotaFiscal(Integer notaFiscal, String data) {
        this.notaFiscal = notaFiscal;
        this.data = data;
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

}
