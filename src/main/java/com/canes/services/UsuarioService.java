package com.canes.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioService {

    private static final String API_URL = "http://localhost:8080/usuarios";

    public Long salvarUsuario(Usuario usuario) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(usuario);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
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

}
