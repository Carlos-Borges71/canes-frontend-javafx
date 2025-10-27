package com.canes.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.util.AlertUtil;

public class TelefoneService {

    private static final String API_URL = "http://localhost:8080/telefones";

    public static void salvarTelefone(String numero, Long operadorId, Long clienteId, Long fornecedorId) throws Exception {

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
                .uri(URI.create(API_URL))
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

}
