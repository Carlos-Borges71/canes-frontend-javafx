package com.canes.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.util.AlertUtil;

public class EnderecoService {

    private static final String API_URL = "http://localhost:8080/enderecos";

    public static void salvarEndereco(String logradouro, String numero, String bairro, String cidade, String estado,
            String cep, Long operadorId, Long clienteId,Long fornecedorId) throws Exception {

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
                .uri(URI.create(API_URL))
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

}
