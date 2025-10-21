package com.canes.model;

import java.util.List;

public class Fornecedor {


    private Integer id;
    private String empresa;
    private String cnpj;
    private List<Telefone> telefones;

    


    public Fornecedor(){        
    }

    public Fornecedor(String empresa, String cnpj, List<Telefone> telefones) {
        
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.telefones = telefones;
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
       
     public List<Telefone> getTelefone() {
        return telefones;
    }

    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fornecedor other = (Fornecedor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   

}
