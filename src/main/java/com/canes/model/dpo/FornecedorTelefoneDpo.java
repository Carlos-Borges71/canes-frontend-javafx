package com.canes.model.dpo;

public class FornecedorTelefoneDpo {

    private Integer id;
    private String empresa;
    private String cnpj;
    private String telefone;

    


    public FornecedorTelefoneDpo(){        
    }

    public FornecedorTelefoneDpo( String empresa, String cnpj, String telefone) {
        
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
       
     public String getTelefone() {
        return telefone;
    }


}
