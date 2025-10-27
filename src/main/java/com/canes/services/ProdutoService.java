package com.canes.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.canes.util.AlertUtil;

public class ProdutoService {

    private static final String API_URL = "http://localhost:8080/produtos";

    public static void salvarProduto(String codigo, String nome, Integer estoque, Double valorCompra, Double valorVenda,
            Integer quantcompra, Long fornecedorId) throws Exception {

       
        String json = String.format("""
        {

        "codigo": "%s",
        "nome": "%s",
        "estoque": "%s",
        "valorCompra": "%s",
        "valorVenda": "%s",
        "quantcompra": "%s",
        "fornecedor": {"id": %d}
        }
        """,
        codigo, nome, estoque, valorCompra, valorVenda, quantcompra, fornecedorId);

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
