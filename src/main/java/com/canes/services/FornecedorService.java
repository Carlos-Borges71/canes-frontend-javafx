package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.Fornecedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FornecedorService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

     private static final String BASE_URL = "http://localhost:8080/fornecedores";

     HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public FornecedorService(){
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }



    public Long salvarFornecedor(Fornecedor fornecedor) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(fornecedor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            Fornecedor fornecedorSalvo = mapper.readValue(response.body(), Fornecedor.class);
        
            return fornecedorSalvo.getId();
        } else {
            System.out.println(json + response);
            System.out.println(new RuntimeException("Erro ao salvar fornecedor: " + response.body()));
            throw new RuntimeException("Erro ao salvar fornecedor: " + response.body());
        }
    }

     public List<Fornecedor> buscarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Fornecedor>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar fornecedores: " + response.statusCode());
        }
    }
}
