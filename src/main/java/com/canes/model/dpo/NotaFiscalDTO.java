package com.canes.model.dpo;

import com.canes.model.Fornecedor;

public class NotaFiscalDTO {

    private Long id;
    private Integer notaFiscal;
    private String data;

    private Fornecedor fornecedor;

    public NotaFiscalDTO() {

    }

    public NotaFiscalDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotaFiscal(Integer notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
