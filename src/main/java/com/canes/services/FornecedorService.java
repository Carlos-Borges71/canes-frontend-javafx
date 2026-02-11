package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Fornecedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FornecedorService extends BaseService {

    public FornecedorService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    // Salvar Fornecedor
    public Long salvarFornecedor(Fornecedor fornecedor) throws IOException, InterruptedException, ConnectException {

        // Usando método generico
        Fornecedor fornecedorSalvo = post(ApiConstantes.FORNECEDOR, fornecedor, Fornecedor.class);

        return fornecedorSalvo.getId();
    }

    public List<Fornecedor> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.FORNECEDOR, new TypeReference<List<Fornecedor>>() {
        });
    }

    // atualizar todo Fornecedor
    public Fornecedor atualizarFornecedor(Fornecedor fornecedor)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.FORNECEDOR + "/" + fornecedor.getId();

        return put(url, fornecedor, Fornecedor.class);
    }

    // Atualizar parte dos Fornecedors
    public Fornecedor atualizarParcial(Long id, Fornecedor fonecedorParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.FORNECEDOR + "/" + id;

        return patch(url, fonecedorParcial, Fornecedor.class);
    }

    // Deletar Fornecedor
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.FORNECEDOR + "/" + id);
    }
}
