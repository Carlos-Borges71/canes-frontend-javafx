package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.PedidoProduto;
import com.canes.util.AlertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PedidoProdutoService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/pedido_produto";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public PedidoProdutoService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public static void salvarPedidoProduto(Integer quant, Double valor, Long pedidoId, Long produtoId)
            throws IOException, InterruptedException {

        String json = "{\n" +
                "  \"id\": {\n" +
                "    \"pedido\": { \"id\": " + pedidoId + " },\n" +
                "    \"produto\": { \"id\": " + produtoId + " }\n" +
                "  },\n" +
                "  \"quant\": " + quant + ",\n" +
                "  \"valor\": " + valor + "\n" +
                "}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status == 200 || status == 201) {

        } else {
            System.out.println(response.body());
            System.out.println(json);
            AlertUtil.mostrarErro("Erro ao inserir:\n " + response.body());

        }

    }

    public List<PedidoProduto> buscarTodos() throws IOException, InterruptedException, ConnectException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<PedidoProduto>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar pedido: " + response.statusCode());
        }
    }
}
