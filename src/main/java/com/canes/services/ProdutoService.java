package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.Produto;
import com.canes.util.AlertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProdutoService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

     

     HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_URL = "http://localhost:8080/produtos";

    public ProdutoService(){

    this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

    }
    
    public static void salvarProduto(String codigo, String nome, Integer estoque, Double valorCompra, Double valorVenda,
            Integer quantcompra, Long fornecedorId) throws Exception {

       
        String json = String.format("""
        {

        "codigo": "%s",
        "nome": "%s",
        "estoque": "%s",
        "valorCompra": "%s",
        "valorVenda": "%s",
        "quantcompra": "%s",
        "fornecedor": {"id": %d}
        }
        """,
        codigo, nome, estoque, valorCompra, valorVenda, quantcompra, fornecedorId);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status == 200 || status == 201) {

            AlertUtil.mostrarSucesso("Dados inserido com sucesso!");

        } else {
            System.out.println(response.body());
            System.out.println(json);
            AlertUtil.mostrarErro("Erro ao inserir:\n " + response.body());

        }
    }

    public List<Produto> buscarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Produto>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar fornecedores: " + response.statusCode());
        }
    }

}
