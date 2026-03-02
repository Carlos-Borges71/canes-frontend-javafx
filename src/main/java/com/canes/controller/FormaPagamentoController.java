package com.canes.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.util.Duration;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.canes.factory.PagamentoFactory;
import com.canes.factory.PedidoFactory;
import com.canes.factory.PedidoProdutoFactory;
import com.canes.factory.ProdutoFactory;
import com.canes.model.Produto;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.ClienteService;
import com.canes.services.PagamentoService;
import com.canes.services.PedidoProdutoService;
import com.canes.services.PedidoService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.util.AlertUtil;
import com.canes.util.MaskTextField;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FormaPagamentoController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblValor;

    @FXML
    private Pane paneCliente;

    @FXML
    private TextField txtPagamento;

    @FXML
    private TextField txtValor;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vboxPagamento;

    @FXML
    private ImageView imgOk;

    private ClienteService clienteService;
    private TelefoneService telefoneService;

    private ObservableList<PedidoDPO> pedidosRecebidos;
    private TextField txtTelefone;

    // private String formaSelecionada;

    private Long idCliente;
    private String total;
    private String status;
    private List<PedidoDPO> produtosTabela;
    private String newStatus;

    private TextField txtPagamento2;

    private BigDecimal valorDigitado;
    private BigDecimal valorRestante;
    private Long pedidoId = null;
    private Long produtoId = null;

    private String statusSelecionado;
    private String formaPagamento;
    private BigDecimal totalPago = BigDecimal.ZERO;

    // Parar se fechar no x
    private boolean fechadoNoX = false;

    // Parar se fechar no x
    public boolean isFechadoNoX() {
        return fechadoNoX;
    }

    public String getStatusSelecionada() {
        statusSelecionado = lblStatus.getText();
        return statusSelecionado;
    }

    public String getFormaSelecionada() {
        return formaPagamento;
    }

    public BigDecimal getTotalRecebido() {
        return totalPago;
    }

    // public void prepararFechamento(Stage stage) {
    // stage.setOnHiding(event -> {
    // newStatus = lblStatus.getText();
    // System.out.println("Forma capturada: " + newStatus);
    // });
    // }

    Instant instante = Instant.now();
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    String instanteFormatado = formatter.format(instante);
    String data = instanteFormatado.formatted(instanteFormatado);

    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    PedidoService pedidoService = PedidoFactory.getPedidoService();

    public Long salvarPedido() {
        try {

            Double valor = Double.parseDouble(total.replaceAll("[^0-9,]", "").replace(",", "."));

            pedidoId = pedidoService.salvarPedido(getStatusSelecionada(), valor, data, idCliente);

            return pedidoId;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return pedidoId;
        }

    }

    public void salvarPagamento(Long pedidoId, BigDecimal valorDigitado) {

        try {

            Double valorPagamento = valorDigitado.doubleValue();
            String tipo = txtPagamento.getText();
            PagamentoService pagamentoService = PagamentoFactory.getPagamentoService();
            pagamentoService.salvarPagamento(
                    data,
                    tipo,
                    valorPagamento,
                    pedidoId);

            imgOk.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 segundos
            pause.setOnFinished(e -> imgOk.setVisible(false));
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    // public boolean salvarDados() {

    // PedidoProdutoService pedidoProdutoService =
    // PedidoProdutoFactory.getPedidoProdutoService();
    // ProdutoService produtoService = ProdutoFactory.getProdutoService();

    // try {

    // List<Produto> produtosBanco = produtoService.buscarTodos();

    // for (PedidoDPO p : produtosTabela) {

    // Integer quant = produtosTabela.stream()
    // .filter(pe -> p.getQuant() != null)
    // .mapToInt(PedidoDPO::getQuant)
    // .sum();
    // Double valorProd = p.getValorUnitario();
    // String codigo = p.getCodigo();

    // Produto produtoEncontrado = null;

    // // 🔎 Busca o produto correto
    // for (Produto prod : produtosBanco) {
    // if (codigo.equals(prod.getCodigo())) {
    // produtoEncontrado = prod;
    // break;
    // }
    // }

    // if (produtoEncontrado == null) {
    // System.out.println("ERRO: Produto NÃO encontrado para o código: " + codigo);
    // return false; // ❗ interrompe tudo
    // }

    // // 🚨 Verifica estoque corretamente
    // if (produtoEncontrado.getEstoque() < quant) {

    // AlertUtil.mostrarErro(
    // "Quantidade em estoque insuficiente para venda! \nRestam "
    // + produtoEncontrado.getEstoque() + " produtos em estoque");

    // return false; // ❗ PARA O MÉTODO INTEIRO
    // } else {

    // Integer novoEstoque = produtoEncontrado.getEstoque() - quant;

    // Produto produtoAtualizacao = new Produto();
    // produtoAtualizacao.setEstoque(novoEstoque);

    // ObjectMapper mapper = new ObjectMapper();
    // System.out.println(mapper.writeValueAsString(produtoAtualizacao));

    // produtoService.atualizarParcial(produtoEncontrado.getId(),
    // produtoAtualizacao);

    // }

    // if (pedidoId == null) {
    // pedidoId = salvarPedido();

    // if (pedidoId == null) {
    // System.out.println("Erro ao salvar pedido.");
    // return false;
    // }
    // }

    // // ✅ Só salva se passou na validação
    // pedidoProdutoService.salvarPedidoProduto(
    // pedidoId,
    // produtoEncontrado.getId(),
    // quant,
    // valorProd);
    // }

    // return true;

    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return false;
    // }

    public void receberDados(Long idCliente, String total, List<PedidoDPO> produtosTabela) {

        this.idCliente = idCliente;
        this.total = total;
        this.produtosTabela = produtosTabela;

        valorRestante = MaskTextField.parseValor(total);
        lblValor.setText(total);

        Platform.runLater(() -> {

            lblValor.setText(total);

            System.out.println("Valor recebido " + total);
        });

        // for (PedidoDPO p : pedidosRecebidos) {
        // System.out.println(p.getCodigo() + " " + idCliente);
        // System.out.println(p.getProduto() + " " + idCliente);
        // }

    }

    public boolean criarPedidoEItens() {

        ProdutoService produtoService = ProdutoFactory.getProdutoService();
        PedidoProdutoService pedidoProdutoService = PedidoProdutoFactory.getPedidoProdutoService();

        try {

            if (pedidoId != null) {
                return true;
            }

            // 🔵 1️⃣ Busca produtos do banco
            List<Produto> produtosBanco = produtoService.buscarTodos();

            // 🔵 2️⃣ Agrupa e soma produtos repetidos
            Map<String, Integer> quantidadePorProduto = new HashMap<>();

            for (PedidoDPO p : produtosTabela) {

                String codigo = p.getCodigo();
                Integer quant = p.getQuant();

                quantidadePorProduto.merge(codigo, quant, Integer::sum);
            }

            // 🔵 3️⃣ VALIDA ESTOQUE PRIMEIRO (ANTES DE CRIAR PEDIDO)
            for (Map.Entry<String, Integer> entry : quantidadePorProduto.entrySet()) {

                String codigo = entry.getKey();
                Integer totalQuant = entry.getValue();

                Produto produtoEncontrado = produtosBanco.stream()
                        .filter(prod -> codigo.equals(prod.getCodigo()))
                        .findFirst()
                        .orElse(null);

                if (produtoEncontrado == null) {
                    return false;
                }

                if (produtoEncontrado.getEstoque() < totalQuant) {

                    AlertUtil.mostrarErro(
                            "Estoque insuficiente para o produto "
                                    + produtoEncontrado.getNome() + "\nDisponível: "
                                    + produtoEncontrado.getEstoque());

                    return false; // ❗ PARA TUDO AQUI
                }
            }

            // 🔵 4️⃣ AGORA cria pedido (só se passou na validação)
            pedidoId = salvarPedido();

            if (pedidoId == null) {
                return false;
            }

            // 🔵 5️⃣ Baixa estoque
            for (Map.Entry<String, Integer> entry : quantidadePorProduto.entrySet()) {

                String codigo = entry.getKey();
                Integer totalQuant = entry.getValue();

                Produto produtoEncontrado = produtosBanco.stream()
                        .filter(prod -> codigo.equals(prod.getCodigo()))
                        .findFirst()
                        .orElse(null);

                Produto atualizacao = new Produto();
                atualizacao.setEstoque(
                        produtoEncontrado.getEstoque() - totalQuant);

                produtoService.atualizarParcial(
                        produtoEncontrado.getId(),
                        atualizacao);
            }

            // 🔵 6️⃣ Salva itens do pedido
            for (PedidoDPO p : produtosTabela) {

                Produto produtoEncontrado = produtosBanco.stream()
                        .filter(prod -> p.getCodigo().equals(prod.getCodigo()))
                        .findFirst()
                        .orElse(null);

                pedidoProdutoService.salvarPedidoProduto(
                        pedidoId,
                        produtoEncontrado.getId(),
                        p.getQuant(),
                        p.getValorUnitario());
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void initialize() {

        // Para quando apertar x
        Platform.runLater(() -> {
            Stage stage = (Stage) lblValor.getScene().getWindow();

            stage.setOnCloseRequest(event -> {
                fechadoNoX = true;
                System.out.println("Fechou no X");
            });
        });

        Platform.runLater(() -> txtValor.requestFocus());

        lblStatus.setText("AGUARDANDO_PAGAMENTO");

        MaskTextField.valor(txtValor);

        newStatus = txtPagamento.getText();

        txtPagamento.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {

                newScene.getAccelerators().put(
                        new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.CONTROL_DOWN),
                        this::criarHBox);

                newScene.getAccelerators().put(
                        new KeyCodeCombination(KeyCode.ADD),
                        this::criarHBox);
            }
        });

        txtPagamento.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_DOWN),
                        () -> criarHBox());
            }
        });

        rootPane.addEventFilter(KeyEvent.KEY_PRESSED, evet -> {
            if (evet.getCode() == KeyCode.F1) {
                lblStatus.setText("AGUARDANDO_PAGAMENTO");

            }
            if (evet.getCode() == KeyCode.F2) {
                lblStatus.setText("PAGO");

            }
            if (evet.getCode() == KeyCode.F3) {
                lblStatus.setText("CANCELADO");

            }
            if (evet.getCode() == KeyCode.F4) {
                txtPagamento.setText("Dinheiro");

            }
            if (evet.getCode() == KeyCode.F5) {
                txtPagamento.setText("Pix");

            }
            if (evet.getCode() == KeyCode.F6) {
                txtPagamento.setText("Maestro");

            }
            if (evet.getCode() == KeyCode.F7) {
                txtPagamento.setText("VisaElectron");

            }
            if (evet.getCode() == KeyCode.F8) {
                txtPagamento.setText("MasterCard");

            }
            if (evet.getCode() == KeyCode.F9) {
                txtPagamento.setText("Visa");

            }
            if (evet.getCode() == KeyCode.F10) {
                try {
                    if (lblValor == null || lblStatus == null) {
                        AlertUtil.mostrarErro("Preencha valor ou o status do pagamento");
                        return;
                    }
                    if (txtPagamento.getText().isBlank()) {
                        AlertUtil.mostrarErro("Escolha a forma de pagamento");
                        return;
                    }

                    BigDecimal valorDigitado = MaskTextField.parseValor(txtValor.getText());

                    if (valorDigitado.compareTo(BigDecimal.ZERO) <= 0) {
                        AlertUtil.mostrarErro("Informe um valor válido.");
                        return;
                    }
                    System.out.println(txtPagamento.getText());
                    if (valorDigitado.compareTo(valorRestante) > 0
                            && !"Dinheiro".equalsIgnoreCase(txtPagamento.getText().trim())) {
                        AlertUtil.mostrarErro("Valor maior que o saldo restante.");
                        return;
                    }
                    // 🔵 SOMA AO TOTAL PAGO
                    totalPago = totalPago.add(valorDigitado);

                    String txt = lblValor.getText();

                    String valorLimpo = txt
                            .replaceAll("[^0-9,.-]", "") // mantém números, vírgula, ponto e sinal
                            .replace(".", "") // remove separador de milhar
                            .replace(",", "."); // troca decimal para padrão Java

                    BigDecimal valor = new BigDecimal(valorLimpo);

                    String txt1 = txtValor.getText();

                    BigDecimal valor1 = new BigDecimal(
                            txt1.replaceAll("[^0-9,.-]", "") // remove tudo que não é número
                                    .replace(".", "")
                                    .replace(",", "."));

                    if (valor1.compareTo(valor) >= 0) {
                        lblStatus.setText("PAGO");
                    }
                    BigDecimal valorPagamento = valorDigitado;

                    if (valorDigitado.compareTo(valor) > 0) {
                        valorPagamento = valor;
                    }

                    if (pedidoId == null) {

                        boolean criado = criarPedidoEItens();

                        if (!criado) {
                            return;
                        }
                    }

                    if (pedidoId == null) {
                        System.out.println("pedido id = nulo");
                    } else {
                        System.out.println("pedido id = com valor");
                    }

                    salvarPagamento(pedidoId, valorPagamento);

                    // 🔽 atualiza valor restante
                    valorRestante = valorRestante.subtract(valorDigitado);

                    if (valorRestante.compareTo(BigDecimal.ZERO) < 0) {
                        lblTotal.setText("Troco:");

                        String texto = nf.format(valorRestante);

                        lblValor.setText(texto.replace("-", ""));

                        fecharComDelay();
                        return;

                    }

                    if (valorRestante.compareTo(BigDecimal.ZERO) == 0) {
                        lblValor.setText("0,00");

                        fecharComDelay();
                    }

                    // 🟡 ainda falta pagar → pode escolher outra forma

                    lblValor.setText(nf.format(valorRestante));
                    txtValor.clear();
                    txtPagamento.clear();
                    txtValor.requestFocus();

                } catch (Exception e) {
                    e.printStackTrace();
                    AlertUtil.mostrarErro("Valor inválido.");
                }

            }

            // if (evet.getCode() == KeyCode.F11) {
            // try{
            // PagamentoService pagamentoService = PagamentoFactory.getPagamentoService();
            // Long pagamentoId = pagamentoService.buscarUltimoPagamentoId();

            // }catch(Exception e){
            // System.out.println(e.getMessage());
            // }
            // }
        });

    }

    private void fecharComDelay() {

        // 1️⃣ SALVA OS DADOS
        statusSelecionado = lblStatus.getText();
        formaPagamento = txtPagamento.getText();

        // totalRecebido = valorDigitado;

        Stage stage = (Stage) rootPane.getScene().getWindow();

        PauseTransition pause = new PauseTransition(Duration.seconds(5)); // 2 segundos
        pause.setOnFinished(e -> stage.close());
        pause.play();
    }

    private void criarHBox() {
        HBox hbox = new HBox();

        Label lblPagamento = new Label("Pagamento:");
        TextField txtPagamento2 = new TextField();
        Label lblValor = new Label("Valor:");
        TextField txtValor2 = new TextField();

        lblPagamento.setStyle("-fx-text-fill: fff;");
        lblPagamento.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        lblValor.setStyle("-fx-text-fill: fff ;");
        lblValor.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        txtPagamento2.setStyle(
                "-fx-background-color: transparent; -fx-border-radius: 7; -fx-border-color: fff; -fx-text-fill: fff; -fx-font-size: 18px;");
        txtValor2.setStyle(
                "-fx-background-color: transparent; -fx-border-radius: 7; -fx-border-color: fff; -fx-text-fill: fff;-fx-font-size: 18px;");

        txtPagamento2.setPrefWidth(180);
        txtPagamento2.setPrefHeight(40);
        txtValor2.setPrefWidth(180);
        txtValor2.setPrefHeight(35);

        hbox.setSpacing(15);
        hbox.getChildren().addAll(lblPagamento, txtPagamento2, lblValor, txtValor2);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5));

        vboxPagamento.getChildren().add(hbox);
    }
}
