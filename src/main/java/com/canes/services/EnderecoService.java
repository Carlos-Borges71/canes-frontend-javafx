package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Fornecedor;
import com.canes.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnderecoService extends BaseService {

    public EnderecoService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public void salvarEndereco(String logradouro, String numero, String bairro,
            String cidade, String estado, String cep,
            Long operadorId, Long clienteId, Long fornecedorId)
            throws IOException, InterruptedException, ConnectException {

        // Monta objeto Endereco (Java, não JSON manual)
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        // Só adiciona se não for nulo
        if (operadorId != null) {
            endereco.setOperador(new Usuario(operadorId));
        }

        if (clienteId != null) {
            endereco.setCliente(new Cliente(clienteId));
        }

        if (fornecedorId != null) {
            endereco.setFornecedor(new Fornecedor(fornecedorId));
        }

        // Usa o POST genérico do BaseService
        post(ApiConstantes.ENDERECOS, endereco, Void.class);
    }

    public List<Endereco> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.ENDERECOS, new TypeReference<List<Endereco>>() {
        });
    }

    // atualizar todo endereço
    public Endereco atualizar(Endereco endereco)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.ENDERECOS + "/" + endereco.getId();

        return put(url, endereco, Endereco.class);
    }

    // Atualizar parte dos endereços
    public Endereco atualizarParcial(Long id, Endereco enderecoParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.ENDERECOS + "/" + id;

        return patch(url, enderecoParcial, Endereco.class);
    }

    // Deletar Endereço
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.ENDERECOS + "/" + id);
    }

}
