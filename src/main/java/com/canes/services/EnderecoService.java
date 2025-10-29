package com.canes.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import com.canes.model.Endereco;
import com.canes.util.AlertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnderecoService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/enderecos";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public EnderecoService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    

    }

    public static void salvarEndereco(String logradouro, String numero, String bairro, String cidade, String estado,
            String cep, Long operadorId, Long clienteId, Long fornecedorId) throws Exception {

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n")
                .append("  \"logradouro\": \"").append(logradouro).append("\",\n")
                .append("  \"numero\": \"").append(numero).append("\",\n")
                .append("  \"bairro\": \"").append(bairro).append("\",\n")
                .append("  \"cidade\": \"").append(cidade).append("\",\n")
                .append("  \"estado\": \"").append(estado).append("\",\n")
                .append("  \"cep\": \"").append(cep).append("\"");

        if (operadorId != null) {
            jsonBuilder.append(",\n  \"operador\": {\"id\": ").append(operadorId).append("}");
        }

        if (clienteId != null) {
            jsonBuilder.append(",\n  \"cliente\": {\"id\": ").append(clienteId).append("}");
        }

        if (fornecedorId != null) {
            jsonBuilder.append(",\n  \"fornecedor\": {\"id\": ").append(fornecedorId).append("}");
        }

        jsonBuilder.append("\n}");

        String json = jsonBuilder.toString();

        // String json = String.format("""
        // {

        // "logradouro": "%s",
        // "numero": "%s",
        // "bairro": "%s",
        // "cidade": "%s",
        // "estado": "%s",
        // "cep": "%s",
        // "operador": {"id": %d}
        // }
        // """,
        // logradouro, numero, bairro, cidade, estado, cep, operadorId);

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

    public List<Endereco> buscarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Endereco>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar telefones: " + response.statusCode());
        }
    }


     public Endereco buscarEnderecoPorId(Long id) {
        if (id == null) return null;

        String url = BASE_URL +"/" + id;

        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(8))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200 && resp.body() != null && !resp.body().isBlank()) {
                return mapper.readValue(resp.body(), Endereco.class);
            } else {
                System.out.println("[EnderecoService] status=" + resp.statusCode() + ", body: " + resp.body());
            }
        } catch (Exception e) {
            System.err.println("[EnderecoService] erro ao buscar endereco: " + e.getMessage());
        }

        return null;
    }

}
