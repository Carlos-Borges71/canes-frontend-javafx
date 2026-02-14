package com.canes.infra.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.util.HttpResponseValidator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService {

    protected final HttpClient client;
    protected final ObjectMapper mapper;

    // Construtor recebe dependências prontas
    public BaseService(HttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    /**
     * Método genérico para enviar POST com JSON
     * T = tipo do objeto que será retornado
     */
    protected <T> T post(String url, Object body, Class<T> responseType)
            throws IOException, InterruptedException {

        // Converte o objeto enviado para JSON
        String json = mapper.writeValueAsString(body);

        // Monta a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Envia para o servidor
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        HttpResponseValidator.validar(response);

        // Se sucesso
        if (response.statusCode() == 200 || response.statusCode() == 201) {

            // Converte JSON de resposta para objeto Java
            return mapper.readValue(response.body(), responseType);

        } else {
            // Se erro
            throw new RuntimeException("Erro HTTP: " + response.body());
        }
    }

    /*
     * =========================
     * 🔹 MÉTODO GET
     * =========================
     */
    protected <T> List<T> getList(String url, TypeReference<List<T>> typeReference)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseValidator.validar(response);

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), typeReference);
        } else {
            throw new RuntimeException("Erro GET: " + response.body());
        }
    }

    /*
     * =========================
     * 🔹 MÉTODO PUT (atualizar)
     * =========================
     */
    protected <T> T put(String url, Object body, Class<T> responseType)
            throws IOException, InterruptedException {

        String json = mapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseValidator.validar(response);

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), responseType);
        } else {
            throw new RuntimeException("Erro PUT: " + response.body());
        }
    }

    /*
     * =========================
     * 🔹 MÉTODO PATCH
     * =========================
     */
    protected <T> T patch(String url, Object body, Class<T> responseType)
            throws IOException, InterruptedException {

        // Converte o objeto parcial para JSON
        String json = mapper.writeValueAsString(body);

        // Monta requisição PATCH manualmente
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Envia requisição
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseValidator.validar(response);

        // Alguns backends retornam 200, outros 204
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), responseType);
        } else if (response.statusCode() == 204) {
            return null; // sem conteúdo de retorno
        } else {
            throw new RuntimeException("Erro PATCH: " + response.body());
        }
    }

    /*
     * =========================
     * 🔹 MÉTODO DELETE
     * =========================
     */
    protected void delete(String url)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponseValidator.validar(response);

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new RuntimeException("Erro DELETE: " + response.body());
        }
    }
}
