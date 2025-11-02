package com.canes.controller;

import java.lang.classfile.Label;

import com.canes.model.Fornecedor;
import com.canes.model.Produto;
import com.canes.services.ProdutoService;
import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.TextFieldUtil;

import javafx.application.Platform;
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

    @FXML
    private Label lblFornecedor;

    public void setFornecedor(Long fornecedorId) {
        this.fornecedorId = fornecedorId;

        // Exibe o nome do fornecedor na tela, se quiser
    }

    @FXML
    void onClickcadastrar(ActionEvent event) throws Exception {

        Produto produto = new Produto();

        String codigo = txtCodigo.getText();
        String nome = txtProduto.getText();
        Double valorCompra = Double
                .parseDouble(txtValorCompra.getText().replaceAll("[^\\d,\\.]", "").replace(".", "").replace(",", "."));
        Double valorVenda = Double.parseDouble(txtValorVenda.getText().replaceAll("[^\\d,\\.]", "").replace(".", "").replace(",", "."));
        Integer quantCompra = Integer.parseInt(txtQuantidade.getText());
        Integer estoque = 5;
        System.out.println(codigo + nome + valorCompra + valorVenda + quantCompra);
        ProdutoService produtoService = new ProdutoService();

        produtoService.salvarProduto(codigo, nome, estoque, valorCompra, valorVenda, quantCompra, fornecedorId);

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
