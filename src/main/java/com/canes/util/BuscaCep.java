package com.canes.util;

import com.canes.model.Endereco;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaCep {

    /**
     * Busca um CEP na API ViaCEP e retorna um objeto Endereco preenchido.
     *
     * @param cepStr CEP digitado pelo usuário (com ou sem máscara)
     * @return Endereco preenchido ou null se CEP inválido
     * @throws Exception caso ocorra erro na requisição HTTP
     */
    public static Endereco buscar(String cepStr) throws Exception {

        // Remove caracteres não numéricos
        String cep = cepStr.replaceAll("\\D", "");

        // Validação simples
        if (cep.length() != 8) {
            return null; // CEP inválido
        }

        // Criar cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Criar requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .GET()
                .build();

        // Enviar requisição
        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        // Converter JSON para objeto JsonObject
        JsonObject json = new Gson().fromJson(response.body(), JsonObject.class);

        // Se a API retornou "erro": true → CEP não existe
        if (json.has("erro")) {
            return null;
        }

        // Criar e preencher objeto Endereco
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(json.get("logradouro").getAsString());
        endereco.setBairro(json.get("bairro").getAsString());
        endereco.setCidade(json.get("localidade").getAsString());
        endereco.setEstado(json.get("uf").getAsString());

        return endereco;
    }
}

