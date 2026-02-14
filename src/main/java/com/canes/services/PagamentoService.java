package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Pagamento;
import com.canes.model.Pedido;
import com.canes.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PagamentoService extends BaseService {

    public PagamentoService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public void salvarPagamento(String data, String tipo, Double valorPagamneto, Long pedidoId)
            throws IOException, InterruptedException, ConnectException {

        // Monta objeto produto (Java, não JSON manual)
        Pagamento pagamento = new Pagamento();
        pagamento.setData(data);
        pagamento.setTipo(tipo);
        pagamento.setValorPagamento(valorPagamneto);

        // Só adiciona se não for nulo

        if (pedidoId != null) {
            pagamento.setPedido(new Pedido(pedidoId));
        }

        // Usa o POST genérico do BaseService
        post(ApiConstantes.PAGAMENTOS, pagamento, Void.class);

    }

    public List<Pagamento> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.PAGAMENTOS, new TypeReference<List<Pagamento>>() {
        });
    }

    // atualizar todo pagamento
    public Pagamento atualizar(Pagamento pagamento)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.PAGAMENTOS + "/" + pagamento.getId();

        return put(url, pagamento, Pagamento.class);
    }

    // Atualizar parte dos pagamentos
    public Pagamento atualizarParcial(Long id, Produto pagamentoParcial)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.PAGAMENTOS + "/" + id;

        return patch(url, pagamentoParcial, Pagamento.class);
    }

    // Deletar pagamento
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.PAGAMENTOS + "/" + id);
    }

}
