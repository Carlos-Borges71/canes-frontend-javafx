package com.canes.model.dpo;

public class ClienteTabelaDPO {

    private Long id;
    private String nome;
    private String telefones;
    private String instante;
    private String endereco;
    private String pedidos;
    
    

    public ClienteTabelaDPO(Long id,String nome, String instante, String telefones, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefones = telefones;
        this.instante = instante;
        this.endereco = endereco;
        //this.pedidos = pedidos;
        
    }
    

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public String getTelefones() {
        return telefones;
    }

    public String getInstante() {
        return instante;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPedidos() {
        return pedidos;
    }

   

}
