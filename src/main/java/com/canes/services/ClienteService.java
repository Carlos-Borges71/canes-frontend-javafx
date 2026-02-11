package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClienteService extends BaseService {

    public ClienteService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    // Salvar cliente
    public Long salvarCliente(Cliente cliente) throws IOException, InterruptedException, ConnectException {

        // Usando método generico
        Cliente clienteSalvo = post(ApiConstantes.CLIENTES, cliente, Cliente.class);

        return clienteSalvo.getId();
    }

    public List<Cliente> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.CLIENTES, new TypeReference<List<Cliente>>() {
        });
    }

    // atualizar todo cliente
    public Cliente atualizarCliente(Cliente cliente)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.CLIENTES + "/" + cliente.getId();

        return put(url, cliente, Cliente.class);
    }

    // Atualizar parte dos clientes
    public Cliente atualizarParcial(Long id, Cliente clienteParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.CLIENTES + "/" + id;

        return patch(url, clienteParcial, Cliente.class);
    }

    // Deletar Cliente
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.CLIENTES + "/" + id);
    }
}
