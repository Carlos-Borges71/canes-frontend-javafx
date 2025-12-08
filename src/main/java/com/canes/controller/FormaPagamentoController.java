package com.canes.controller;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.canes.model.Pagamento;
import com.canes.model.Pedido;
import com.canes.model.Produto;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.ClienteService;
import com.canes.services.PagamentoService;
import com.canes.services.PedidoProdutoService;
import com.canes.services.PedidoService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FormaPagamentoController {

    @FXML
    private RadioButton radioDinheiro;

    @FXML
    private RadioButton radioMaestro;

    @FXML
    private RadioButton radioMaster;

    @FXML
    private RadioButton radioPix;

    @FXML
    private RadioButton radiovisa;

    @FXML
    private RadioButton radiovisaelectron;

    private ClienteService clienteService;
    private TelefoneService telefoneService;

    private ObservableList<PedidoDPO> pedidosRecebidos;
    private TextField txtTelefone;

    private String formaSelecionada;

    public String getFormaSelecionada() {
        return formaSelecionada;
    }

    @FXML
    private void confirmar() {
        ToggleGroup grupoPagamento = new ToggleGroup();
        radioDinheiro.setToggleGroup(grupoPagamento);
        radioPix.setToggleGroup(grupoPagamento);

        if (radioDinheiro.isSelected()) {
            formaSelecionada = "Dinheiro";
        } else if (radioMaestro.isSelected()) {
            formaSelecionada = "Maestro";
        } else if (radioMaster.isSelected()) {
            formaSelecionada = "MasterCard";
        } else if (radioPix.isSelected()) {
            formaSelecionada = "Pix";
        } else if (radiovisa.isSelected()) {
            formaSelecionada = "Visa";
        } else if (radiovisaelectron.isSelected()) {
            formaSelecionada = "VisaEletron";
        }

        // Fecha a janela
        Stage stage = (Stage) radioDinheiro.getScene().getWindow();
        stage.close();
    }

    // public void initialize(){
    // receberDados(pedidos, null);
    // }

    public void receberDados(Long idCliente, Label lbltotal, TextField txtStatus, List<PedidoDPO> produtosTabela) {

        Instant instante = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC);

        String instanteFormatado = formatter.format(instante);
        String data = instanteFormatado.formatted(instanteFormatado);

        String status = txtStatus.getText();
        Double valor = Double.parseDouble(lbltotal.getText().replaceAll("[^0-9,]", "").replace(",", "."));

        PedidoService pedidoService = new PedidoService();
        PedidoProdutoService pedidoProdutoService = new PedidoProdutoService();
        ProdutoService produtoService = new ProdutoService();
        PagamentoService pagamentoService = new PagamentoService();

        try {

            Long pedidoId = pedidoService.salvarPedido(status, valor, data, idCliente );

            System.out.println("DEBUG: PedidoID antes do loop = " + pedidoId);

            List<Produto> busca = produtoService.buscarTodos();

            for (PedidoDPO p : produtosTabela) {

                Long produtoId = null;

                Integer quant = p.getQuant();
                Double valorProd = p.getValorUnitario();
                String codigo = p.getCodigo();

                for (Produto b : busca) {
                    if (codigo.equals(b.getCodigo())) {
                        produtoId = b.getId();
                        break;
                    }
                }

                if (produtoId == null) {
                    System.out.println("ERRO: Produto NÃO encontrado para o código: " + codigo);
                    continue;
                }

                pedidoProdutoService.salvarPedidoProduto(
                        quant,
                        valorProd,
                        pedidoId,
                        produtoId);

            }

            pagamentoService.salvarPagamento(
                data,
                getFormaSelecionada(),
                valor,
                pedidoId
            );

        } catch (Exception e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        }

        // for (PedidoDPO p : pedidosRecebidos) {
        // System.out.println(p.getCodigo() + " " + idCliente);
        // System.out.println(p.getProduto() + " " + idCliente);
        // }

    }
}
