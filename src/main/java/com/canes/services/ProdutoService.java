package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Fornecedor;
import com.canes.model.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProdutoService extends BaseService {

    public ProdutoService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public void salvarProduto(String codigo, String nome, Double valorCompra, Double valorVenda,
            Integer quantcompra, Long fornecedorId, Long notaFiscalId)
            throws IOException, InterruptedException, ConnectException {

        // Monta objeto produto (Java, não JSON manual)
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setNome(nome);
        produto.setValorCompra(valorCompra);
        produto.setValorVenda(valorVenda);
        produto.setQuantcompra(quantcompra);

        // Só adiciona se não for nulo

        if (fornecedorId != null) {
            produto.setFornecedor(new Fornecedor(fornecedorId));
        }

        // Usa o POST genérico do BaseService
        post(ApiConstantes.PRODUTOS, produto, Void.class);

    }

    public List<Produto> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.PRODUTOS, new TypeReference<List<Produto>>() {
        });
    }

    // atualizar todo produto
    public Produto atualizar(Produto produto)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.PRODUTOS + "/" + produto.getId();

        return put(url, produto, Produto.class);
    }

    // Atualizar parte dos produto
    public Produto atualizarParcial(Long id, Produto produtoParcial)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.PRODUTOS + "/" + id;

        return patch(url, produtoParcial, Produto.class);
    }

    // Deletar produto
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.PRODUTOS + "/" + id);
    }

}
