package com.canes;




public class Telefone {
    private static final long serialVersionUID = 1L;

  
    private Integer id;
    private String numero;

   

    public Telefone(){        
    }

    public Telefone(Integer id, String numero) {
        this.id = id;
        this.numero = numero;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    
   

   
   

    
}
