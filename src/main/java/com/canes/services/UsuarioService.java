package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

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

    // atualizar tudo usuario

    public Usuario atualizarUsuario(Usuario usuario)
            throws IOException, InterruptedException, ConnectException {

        if (usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário precisa ter ID para atualização.");
        }

        String json = mapper.writeValueAsString(usuario);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + usuario.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), Usuario.class);
        } else {
            throw new RuntimeException("Erro ao atualizar usuário: " + response.body());
        }
    }

    // Atualizar parte dos usuario
    public void atualizarParcial(Long id, Map<String, Object> campos)
            throws IOException, InterruptedException {

        String json = mapper.writeValueAsString(campos);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao atualizar parcialmente: " + response.body());
        }
    }

}
