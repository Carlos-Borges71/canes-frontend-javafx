package com.canes.model.dpo;

public class ClienteDPO {

    private Long id;
    private String nome;
    private String instante;
    
    

    public ClienteDPO() {
    }

    public ClienteDPO(String nome, String instante) {

        this.nome = nome;
        this.instante = instante;
        //this.operador = operador;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstante() {
        return instante;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    
    
    

}
