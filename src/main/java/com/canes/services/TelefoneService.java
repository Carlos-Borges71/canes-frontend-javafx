package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Cliente;
import com.canes.model.Fornecedor;
import com.canes.model.Telefone;
import com.canes.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelefoneService extends BaseService {

    public TelefoneService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public void salvarTelefone(String numero, Long operadorId, Long clienteId, Long fornecedorId)
            throws IOException, InterruptedException, ConnectException {

        // Monta objeto Endereco (Java, não JSON manual)
        Telefone telefone = new Telefone();
        telefone.setNumero(numero);

        // Só adiciona se não for nulo
        if (operadorId != null) {
            telefone.setOperador(new Usuario(operadorId));
        }

        if (clienteId != null) {
            telefone.setCliente(new Cliente(clienteId));
        }

        if (fornecedorId != null) {
            telefone.setFornecedor(new Fornecedor(fornecedorId));
        }

        // Usa o POST genérico do BaseService
        post(ApiConstantes.TELEFONES, telefone, Void.class);
    }

    public List<Telefone> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.TELEFONES, new TypeReference<List<Telefone>>() {
        });
    }

    // atualizar todo endereço
    public Telefone atualizar(Telefone telefone)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.TELEFONES + "/" + telefone.getId();

        return put(url, telefone, Telefone.class);
    }

    // Atualizar parte dos endereços
    public Telefone atualizarParcial(Long id, Telefone telefoneParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.ENDERECOS + "/" + id;

        return patch(url, telefoneParcial, Telefone.class);
    }

    // Deletar Endereço
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.TELEFONES + "/" + id);
    }

}
