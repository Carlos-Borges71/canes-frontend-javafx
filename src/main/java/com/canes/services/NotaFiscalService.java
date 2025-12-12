package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.NotaFiscal;
import com.canes.util.AlertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotaFiscalService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

     

     HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_URL = "http://localhost:8080/notasfiscais";

    public NotaFiscalService(){

    this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

    }

   public static Long salvarNotaFiscal(Integer notafiscal, String data, Long fornecedorId) throws Exception {

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
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    int status = response.statusCode();

    if (status == 200 || status == 201) {

        // Converte o JSON retornado
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        NotaFiscal nota = mapper.readValue(response.body(), NotaFiscal.class);

        return nota.getId(); // <-- retorna o ID salvo
    } else {
        System.out.println(response.body());
        System.out.println(json);
        AlertUtil.mostrarErro("Erro ao inserir:\n " + response.body());
        return null;
    }
}


    public List<NotaFiscal> buscarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<NotaFiscal>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar notas fiscais: " + response.statusCode());
        }
    }

}
