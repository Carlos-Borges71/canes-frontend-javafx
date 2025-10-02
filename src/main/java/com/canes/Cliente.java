package com.canes;

import java.time.Instant;


public class Cliente {

  
    private Integer id;
    private String nome;
    private Instant instante;
    
    
    public Cliente(){
    }


    public Cliente(Integer id, String nome, Instant instante) {
        
        this.id = id;
        this.nome = nome;
        this.instante = instante;
       
        
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public Instant getInstante() {
        return instante;
    }


    public void setInstante(Instant instante) {
        this.instante = instante;
    }    


      

   
    


   

    
}
