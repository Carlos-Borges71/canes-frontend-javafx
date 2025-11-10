package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.canes.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/usuarios";
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    public UsuarioService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Long salvarUsuario(Usuario usuario) throws IOException, InterruptedException, ConnectException {

        String json = mapper.writeValueAsString(usuario);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            Usuario usuarioSalvo = mapper.readValue(response.body(), Usuario.class);

            return usuarioSalvo.getId();
        } else {
            throw new RuntimeException("Erro ao salvar usuario: " + response.body());

        }
    }

    public List<Usuario> buscarTodos() throws IOException, InterruptedException, ConnectException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<List<Usuario>>() {
            });
        } else {
            throw new RuntimeException("Erro ao buscar usuários: " + response.statusCode());
        }
    }

}
