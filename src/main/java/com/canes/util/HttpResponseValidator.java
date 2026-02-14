package com.canes.util;

import java.net.http.HttpResponse;

import com.canes.exception.ApiException;

public class HttpResponseValidator {

    private HttpResponseValidator() {
    }

    public static void validar(HttpResponse<String> response) {
        int status = response.statusCode();

        if (status >= 200 && status < 300) {
            return;
        }

        System.err.println("========== ERRO HTTP ==========");
        System.err.println("Status Code: " + status);
        System.err.println("Response Body:");
        System.err.println(response.body());
        System.err.println("================================");

        String mensagem = switch (status) {
            case 400 -> "Requisição inválida";
            case 401 -> "Não autorizado";
            case 403 -> "Acesso negado";
            case 404 -> "Recurso não encontrado";
            case 500 -> "Erro interno no servidor";
            default -> "Erro inesperado";
        };

        // Mostra algo amigável na UI
        AlertUtil.mostrarErro(mensagem);

        // Lança exceção com detalhes COMPLETOS
        throw new ApiException(
                status,
                mensagem + " | STATUS=" + status + " | BODY=" + response.body());
    }

}
