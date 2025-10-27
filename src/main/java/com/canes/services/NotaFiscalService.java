package com.canes.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.util.AlertUtil;

public class NotaFiscalService {

    private static final String API_URL = "http://localhost:8080/notasfiscais";

    public static void salvarNotaFiscal(Integer notafiscal, String data, Long fornecedorId) throws Exception {

        String json = String.format("""
                {

                "notaFiscal": "%s",

                "data": "%s",

                "fornecedor": {"id": %d}
                }
                """,
                notafiscal, data, fornecedorId);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status == 200 || status == 201) {

            // AlertUtil.mostrarSucesso("inserido com sucesso!");

        } else {
            System.out.println(response.body());
            System.out.println(json);
            AlertUtil.mostrarErro("Erro ao inserir:\n " + response.body());

        }
    }

}
