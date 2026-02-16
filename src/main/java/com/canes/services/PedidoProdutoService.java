package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Endereco;
import com.canes.model.dpo.PedidoDPO;
import com.canes.model.dpo.PedidoProdutoDPO;
import com.canes.model.dpo.ProdutoDPO;
import com.canes.model.pk.PedidoProdutoPK;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PedidoProdutoService extends BaseService {

    public PedidoProdutoService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);

    }

    public void salvarPedidoProduto(Long pedidoId, Long produtoId, Integer quant, Double valor)
            throws IOException, InterruptedException {

        PedidoProdutoDPO pedidoProduto = new PedidoProdutoDPO();

        // 🔹 cria a PK composta
        PedidoProdutoPK pk = new PedidoProdutoPK();

        if (pedidoId != null) {
            pk.setPedido(new PedidoDPO(pedidoId));
        }

        if (produtoId != null) {
            pk.setProduto(new ProdutoDPO(produtoId));
        }

        // 🔹 seta o ID composto
        pedidoProduto.setId(pk);

        // 🔹 seta os outros campos
        pedidoProduto.setQuant(quant);
        pedidoProduto.setValor(valor);

        // converte para JSON
        String json = mapper.writeValueAsString(pedidoProduto);

        System.out.println("JSON enviado:");
        System.out.println(json);

        post(ApiConstantes.PEDIDOPRODUTOS, pedidoProduto, Void.class);
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
