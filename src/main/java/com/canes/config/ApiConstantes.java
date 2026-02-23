package com.canes.config;

public class ApiConstantes {

    private ApiConstantes() {
        // impede instanciar
    }

    public static final String BASE_URL = "http://localhost:8080";

    public static final String USUARIOS = BASE_URL + "/usuarios";
    public static final String ENDERECOS = BASE_URL + "/enderecos";
    public static final String CLIENTES = BASE_URL + "/clientes";
    public static final String FORNECEDOR = BASE_URL + "/fornecedores";
    public static final String TELEFONES = BASE_URL + "/telefones";
    public static final String PEDIDOS = BASE_URL + "/pedidos";
    public static final String PRODUTOS = BASE_URL + "/produtos";
    public static final String PAGAMENTOS = BASE_URL + "/pagamentos";
    public static final String PEDIDOPRODUTOS = BASE_URL + "/pedido_produto";
    public static final String NOTASFISCAIS = BASE_URL + "/notasfiscais";
}
