package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Cliente;
import com.canes.model.Pedido;
import com.canes.model.dpo.PedidoDPORequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PedidoService extends BaseService {

    public PedidoService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public Long salvarPedido(String statusPedido, Double valor, String data, Long clienteId)
            throws IOException, InterruptedException {

        // Monta objeto Pedido (Java, não JSON manual)
        PedidoDPORequest pedido = new PedidoDPORequest();
        pedido.setStatus(statusPedido);
        pedido.setValor(valor);
        pedido.setData(data);

        // Só adiciona se não for nulo

        if (clienteId != null) {
            pedido.setCliente(new Cliente(clienteId));
        }

        // // converte o objeto em JSON
        // String json = mapper.writeValueAsString(pedido);

        // // exibe no console
        // System.out.println("JSON enviado:");
        // System.out.println(json);

        // Usa o POST do BaseService
        Pedido pedidoSalvo = post(ApiConstantes.PEDIDOS, pedido, Pedido.class);
        System.out.println("id do pedido = " + pedidoSalvo.getId());
        return pedidoSalvo.getId();
    }

    public List<Pedido> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.PEDIDOS, new TypeReference<List<Pedido>>() {
        });
    }

    // atualizar todo endereço
    public Pedido atualizar(Pedido pedido)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.PEDIDOS + "/" + pedido.getId();

        return put(url, pedido, Pedido.class);
    }

    // Atualizar parte dos endereços
    public Pedido atualizarParcial(Long id, Pedido pedidoParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.PEDIDOS + "/" + id;

        return patch(url, pedidoParcial, Pedido.class);
    }
}
