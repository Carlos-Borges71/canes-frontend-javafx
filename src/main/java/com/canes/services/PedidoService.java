package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.Cliente;
import com.canes.model.Pedido;
import com.canes.model.dpo.ClienteDPO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PedidoService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/pedidos";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public PedidoService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Long salvarPedido(Pedido pedido) throws IOException, InterruptedException, ConnectException {

        String json = mapper.writeValueAsString(pedido);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            Pedido pedidoSalvo = mapper.readValue(response.body(), Pedido.class);

            return pedidoSalvo.getId();
        } else {
            System.out.println(json + response);
            throw new RuntimeException("Erro ao salvar cliente: " + response.body());
        }
    }

    public List<Pedido> buscarTodos() throws IOException, InterruptedException ,ConnectException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Pedido>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar pedido: " + response.statusCode());
        }
    }
}
