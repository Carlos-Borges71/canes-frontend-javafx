package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.model.Fornecedor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FornecedorService {

     private static final String API_URL = "http://localhost:8080/fornecedores";

    public Long salvarFornecedor(Fornecedor fornecedor) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(fornecedor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
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
}
