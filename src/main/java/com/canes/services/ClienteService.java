package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClienteService {

    private static final String API_URL = "http://localhost:8080/clientes";

    public Long salvarCliente(Cliente cliente) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(cliente);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            Cliente clienteSalvo = mapper.readValue(response.body(), Cliente.class);

            return clienteSalvo.getId();
        } else {
            System.out.println(json + response);
            throw new RuntimeException("Erro ao salvar cliente: " + response.body());
        }
    }
}
