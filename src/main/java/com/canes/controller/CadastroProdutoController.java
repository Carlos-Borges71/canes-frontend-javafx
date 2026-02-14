package com.canes.controller;

import java.lang.classfile.Label;

import com.canes.factory.ProdutoFactory;
import com.canes.model.Produto;
import com.canes.model.dpo.ProdutoDPO;
import com.canes.services.ProdutoService;
import com.canes.util.AlertUtil;
import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.TextFieldUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CadastroProdutoController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Pane paneCliente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtProduto;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtTamanho;

    @FXML
    private TextField txtValorCompra;

    @FXML
    private TextField txtValorVenda;

    @FXML
    private VBox vBoxTelClient;

    private Long fornecedorId;
    private long notaFiscalId;

    @FXML
    private Label lblFornecedor;

    public void setFornecedor(Long fornecedorId, Long notaFiscalId) {
        this.fornecedorId = fornecedorId;
        this.notaFiscalId = notaFiscalId;

        // Exibe o nome do fornecedor na tela, se quiser
    }

    @FXML
    void onClickcadastrar(ActionEvent event) throws Exception {

        if (txtProduto.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Produto não \npode ficar vazio!.");
            return;
        }
        if (txtCodigo.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Código não \npode ficar vazio!.");
            return;
        }
        if (txtQuantidade.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Quantidade não \npode ficar vazio!.");
            return;
        }
        if (txtTamanho.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Tamanho não \npode ficar vazio!.");
            return;
        }
        if (txtValorCompra.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Compra não \npode ficar vazio!.");
            return;
        }
        if (txtValorVenda.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Venda não \npode ficar vazio!.");
            return;
        }

        ProdutoDPO produto = new ProdutoDPO();

        String codigo = txtCodigo.getText();
        String nome = txtProduto.getText();
        Double valorCompra = TextFieldUtil.converterParaDouble(txtValorCompra.getText());
        // Double.parseDouble(txtValorCompra.getText().replaceAll("[^\\d,\\.]",
        // "").replace(".", "").replace(",", "."));
        Double valorVenda = TextFieldUtil.converterParaDouble(txtValorVenda.getText());
        // Double.parseDouble(txtValorVenda.getText().replaceAll("[^\\d,\\.]",
        // "").replace(".", "").replace(",", "."));

        Integer quantCompra = Integer.parseInt(txtQuantidade.getText());

        ProdutoService produtoService = ProdutoFactory.getProdutoService();

        produtoService.salvarProduto(codigo, nome, valorCompra, valorVenda, quantCompra, fornecedorId, notaFiscalId);

        txtProduto.clear();
        txtCodigo.clear();
        txtQuantidade.clear();
        txtTamanho.clear();
        txtValorCompra.clear();
        txtValorVenda.clear();

    }

    @FXML
    void onMouseEnteredCadastro(MouseEvent event) {
        HouverEffectUtil.apllyHouverSobre(btnCadastrar);

    }

    @FXML
    void onMouseEnteredLimpar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnLimpar);
    }

    @FXML
    void onMouseExitedCadastro(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnCadastrar);
    }

    @FXML
    void onMouseExitedLimpar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnLimpar);
    }

    @FXML
    void onclickLimpar(ActionEvent event) {

        txtProduto.clear();
        txtCodigo.clear();
        txtQuantidade.clear();
        txtTamanho.clear();
        txtValorCompra.clear();
        txtValorVenda.clear();

    }

    public void initialize() {

        TextFieldUtil.aplicarCapitalizacao(txtProduto);
        MaskTextField.quantNumbery(txtCodigo, 15);
        MaskTextField.valor(txtValorCompra);
        MaskTextField.valor(txtValorVenda);
        MaskTextField.quantNumbery(txtQuantidade, 2);

    }

}
