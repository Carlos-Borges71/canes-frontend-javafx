package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

import com.canes.model.Pagamento;
import com.canes.model.Pedido;
import com.canes.model.dpo.PedidoDPO;
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

    public static Long salvarPedido(String statusPedido, Double valor, String data, Long clienteId)
        throws IOException, InterruptedException {

    String json = "{\n" +
            "  \"status\": \"" + statusPedido + "\"";
    json += ", \"valor\": " + valor;
    json += ", \"data\": \"" + data + "\"";
    json += ", \"cliente\": {\"id\": " + clienteId + "}";
    json += "\n}";

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    int status = response.statusCode();

    if (status == 201 || status == 200) {
        // Extrai o ID retornado
        JSONObject obj = new JSONObject(response.body());
        Long idPedido = obj.getLong("id");

        System.out.println("Pedido salvo com ID: " + idPedido);

        return idPedido;
    } else {
        System.out.println("Erro ao salvar pedido: " + status);
        System.out.println("Resposta: " +json);
        return null;
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

    //  public Long buscarUltimoPedidoId()
    //         throws IOException, InterruptedException {

    //     return buscarTodos().stream()
    //             .max(Comparator.comparing(PedidoDPO::getId))
    //             .map(Pedido::getId)
    //             .orElse(null);
    // }
}
