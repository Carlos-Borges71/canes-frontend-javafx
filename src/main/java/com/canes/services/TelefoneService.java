package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.Telefone;
import com.canes.util.AlertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelefoneService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/telefones";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public TelefoneService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public static void salvarTelefone(String numero, Long operadorId, Long clienteId, Long fornecedorId)
            throws Exception {

        String json = "{\n" +
                "  \"numero\": \"" + numero + "\"";

        if (operadorId != null) {
            json += ", \"operador\": {\"id\": " + operadorId + "}";
        }

        if (clienteId != null) {
            json += ", \"cliente\": {\"id\": " + clienteId + "}";
        }
        if (fornecedorId != null) {
            json += ", \"fornecedor\": {\"id\": " + fornecedorId + "}";
        }

        json += "\n}";

        // String json = String.format("""
        // {

        // "numero": "%s",

        // "operador": {"id": %d}
        // }
        // """,
        // numero, operadorId);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status == 200 || status == 201) {

            // AlertUtil.mostrarSucesso("Telefone inserido com sucesso!");

        } else {
            System.out.println(response.body());
            System.out.println(json);
            AlertUtil.mostrarErro("Erro ao inserir:\n " + response.body());

        }
    }

    public List<Telefone> buscarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Telefone>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar telefones: " + response.statusCode());
        }
    }

}
