package com.canes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.ClienteService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.util.AlertUtil;
import com.canes.util.MaskTextField;
import com.canes.util.RelogioUtil;
import com.canes.util.ScreenUtils;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PedidoController {

    private Produto produto;

    @FXML
    private TableColumn<PedidoDPO, Integer> colItem;
    @FXML
    private TableColumn<PedidoDPO, Integer> colCodigo;

    @FXML
    private TableColumn<PedidoDPO, String> colProduto;

    @FXML
    private TableColumn<PedidoDPO, Integer> colQuant;

    @FXML
    private TableColumn<PedidoDPO, Double> colTotal;

    @FXML
    private TableColumn<PedidoDPO, Double> colValorUnitario;

    @FXML
    private Label lblDesconto;

    @FXML
    private Label lblQuant;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<PedidoDPO> tabelaPedido;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDesconto;

    @FXML
    private TextField txtPagamento;

    @FXML
    private TextField txtQuant;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtTotalRecebido;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtTroco;

    @FXML
    private Label txtRelogio;

    @FXML
    private TextField txtValorUnitario;

    @FXML
    private AnchorPane root;

    private ObservableList<PedidoDPO> listaPedidos;

    private DecimalFormat df;

    private Long idCliente;

    private ClienteService clienteService;
    private TelefoneService telefoneService;

    // private CadastroController cadastroController;

    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    DecimalFormatSymbols simbols = new DecimalFormatSymbols(new Locale("pt", "BR"));

    @FXML
    void pressedEnter(ActionEvent event) {
        try {
            TelefoneService telefones = new TelefoneService();

            List<Telefone> listaTelefones = telefones.buscarTodos();

            boolean encontrado = false;

            for (Telefone t : listaTelefones) {
                if (txtTelefone.getText().equals(t.getNumero()) && t.getCliente() != null) {
                    txtCliente.setText(t.getCliente().getNome());
                    encontrado = true;
                    break;

                }
            }

            if (!encontrado) {

                cadastro();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void cadastro() {
        try {
            // Carrega o FXML normalmente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/canes/cadastroCliente.fxml"));
            Parent root = loader.load();

            // Pega o controller criado pelo FXMLLoader
            CadastroClienteController cadastroClienteController = loader.getController();

            // 👉 Envia o valor do TextField atual
            cadastroClienteController.setTelefoneInicial(txtTelefone.getText());

            // Exibe a tela
            Stage stage = new Stage();
            stage.setTitle("Cadastro Cliente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
            stage.show();

            // ✅ Se quiser capturar os valores depois que fechar a tela:
            stage.setOnHidden(event -> {
                Cliente cliente = cadastroClienteController.getClienteSalvo();
                Telefone telefone = cadastroClienteController.getTelefoneSalvo();
                Endereco endereco = cadastroClienteController.getEnderecoSalvo();

                if (cliente != null) {
                    txtCliente.setText(cliente.getNome());
                }
                if (telefone != null) {
                    txtTelefone.setText(telefone.getNumero());
                }
                if (endereco != null) {
                    // ...
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calcularTroco() {

        BigDecimal subtotal = MaskTextField.parseValor(txtSubTotal.getText());
        BigDecimal recebido = MaskTextField.parseValor(txtTotalRecebido.getText());

        BigDecimal troco = recebido.subtract(subtotal);

        // se ainda não recebeu o suficiente
        if (troco.compareTo(BigDecimal.ZERO) < 0) {
            txtTroco.setText("0");
            return;
        }

        txtTroco.setText(String.valueOf(troco));
    }

    private void tabela() {

        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

        colValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        realColuna(colValorUnitario);

        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        realColuna(colTotal);

        tabelaPedido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label placeholder = new Label("Aguardando Pedido!");
        placeholder.setStyle("-fx-text-fill: #152266 ; -fx-font-weight: bold; -fx-font-size: 40px");

        tabelaPedido.setPlaceholder(placeholder);
    }

    public void initialize() {

        txtTotalRecebido.textProperty().addListener((obs, oldText, newText) -> {
            calcularTroco();
        });

        System.out.println(lblTotal.getText());

        tabela();

        // colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        // colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        // colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        // colQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

        // colValorUnitario.setCellValueFactory(new
        // PropertyValueFactory<>("valorUnitario"));
        // realColuna(colValorUnitario);

        // colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        // realColuna(colTotal);

        // tabelaPedido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Label placeholder = new Label("Aguardando Pedido!");
        // placeholder.setStyle("-fx-text-fill: #152266 ; -fx-font-weight: bold;
        // -fx-font-size: 40px");

        // tabelaPedido.setPlaceholder(placeholder);

        RelogioUtil.iniciarRelogio(txtRelogio);

        MaskTextField.valor(txtDesconto);
        MaskTextField.valor(txtSubTotal);
        MaskTextField.valor(txtTotalRecebido);
        MaskTextField.valor(txtTroco);
        MaskTextField.valor(txtValorUnitario);
        MaskTextField.number(txtQuant);
        MaskTextField.applyPhoneMask(txtTelefone);
        MaskTextField.quantNumbery(txtCodigo, 15);

        // Zebrando a tabela
        tabelaPedido.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(PedidoDPO item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {

                    if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: #d2cbcbff;"); // cinza claro

                    } else {
                        setStyle("-fx-background-color: #f2f2f2;");
                    }
                }
            }
        });

        simbols.setDecimalSeparator(',');
        simbols.setGroupingSeparator('.');
        df = new DecimalFormat("#,##0.00", simbols);

        lblTotal.textProperty().addListener((obs, oldVal, newVal) -> {
            if (lblTotal != null) {
                String totalValor = lblTotal.getText();
                txtSubTotal.setText(totalValor);
            }
        });

        Platform.runLater(() -> {

            Scene scene = txtCodigo.getScene();
            if (scene == null)
                return;

            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ESCAPE) {
                    try {
                        ScreenUtils.changeScreenElement(
                                txtCodigo,
                                "/com/canes/view/menu.fxml",
                                "MENU",
                                null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    e.consume(); // impede conflito
                }
            });
        });

        tabelaPedido.sceneProperty().addListener((obs, oldScene, newScene) -> {

            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, evet -> {
                    if (evet.getCode() == KeyCode.DELETE) {

                        PedidoDPO selecionado = tabelaPedido.getSelectionModel().getSelectedItem();

                        if (selecionado != null) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmação");
                            alert.setHeaderText("Excluir Produto");
                            alert.setContentText(
                                    "Tem Certeza que deseja excluir produto do item " + selecionado.getItem()
                                            + "?\nAo confirma este produto será excluído permanentemente.");

                            alert.showAndWait().ifPresent(resposta -> {
                                if (resposta == ButtonType.OK) {
                                    tabelaPedido.getItems().remove(selecionado);
                                    totalValor();
                                    totalQuant();
                                    desconto();

                                }
                            });
                        }

                    }
                });

            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN),
                        () -> txtDesconto.requestFocus());

            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN),
                        () ->

                        carregarPaneExterna());

            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN),
                        () -> txtTelefone.requestFocus());
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN),
                        () -> txtQuant.requestFocus());
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),
                        () -> txtTotalRecebido.requestFocus());
            }
        });

        txtDesconto.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN),
                        () -> txtCodigo.requestFocus());
            }
        });

        txtDesconto.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN), () ->

                {
                    try {
                        if (txtTelefone.getText().isEmpty()) {
                            AlertUtil.mostrarErro("Digite um número de telefone!");
                            txtTelefone.requestFocus();
                            return;
                        }

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/canes/formaPagamento1.fxml"));
                        Parent root = loader.load();
                        FormaPagamentoController controller = loader.getController();

                        // ScreenUtils.changeScreenController(txtPagamento,
                        // "/com/canes/formaPagamento1.fxml",
                        // "Selecionar forma de Pagamento", controller -> {
                        // if (controller instanceof FormaPagamentoController) {

                        // FormaPagamentoController formaPagamentoController =
                        // (FormaPagamentoController) controller;

                        // try {

                        ClienteService clienteService = new ClienteService();
                        TelefoneService telefoneService = new TelefoneService();

                        List<Cliente> todosCliente = clienteService.buscarTodos();
                        List<Telefone> todosTelefones = telefoneService.buscarTodos();

                        String telefoneDigitado = txtTelefone.getText();

                        for (Cliente c : todosCliente) {
                            for (Telefone t : todosTelefones) {

                                // Evita NullPointerException
                                if (t.getCliente() == null || t.getCliente().getId() == null) {
                                    continue;
                                }

                                // Confere se telefone pertence ao cliente
                                if (c.getId().equals(t.getCliente().getId())) {

                                    // Confere se número é igual
                                    if (telefoneDigitado.equals(t.getNumero())) {
                                        Long idCliente = c.getId();

                                        List<PedidoDPO> produtosTabela = new ArrayList<>(
                                                tabelaPedido.getItems());
                                        String total = "R$ "+lblTotal.getText();
                                        String status = txtStatus.getText();
                                        controller.receberDados(idCliente, total,
                                                produtosTabela);

                                        // Recupera a escolha feita

                                    }

                                }
                            }
                        }

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
                        stage.setTitle("FORMA DE PAGAMENTO");

                        // controller.prepararFechamento(stage);

                        // Mostra e espera fechar
                        stage.showAndWait();

                        String formaEscolhida = controller.getStatusSelecionada();
                        System.out.println("Forma escolida " + formaEscolhida);

                        if (formaEscolhida != null) {
                            txtStatus.setText(formaEscolhida);

                        }
                        String formaPagamento = controller.getFormaSelecionada();
                        System.out.println("Forma pagamento " + formaPagamento);

                        if (formaPagamento != null) {
                            txtPagamento.setText(formaPagamento);
                        }

                        String totalrecebido = String.valueOf(controller.getTotalRecebido());

                        if (totalrecebido != null) {
                            txtTotalRecebido.setText(totalrecebido);
                        }

                        lblTitulo.setText("Aguarde Imprimindo ...");

                        Stage stage1 = (Stage) tabelaPedido.getScene().getWindow();

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(e -> {
                            lblTitulo.setText("Pedido");
                            novoPedido();
                            // se quiser, pode fechar a tela aqui:
                            // stage1.close();
                        });

                        pause.play();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    // }
                    // });
                    // } catch (IOException e) {

                    // e.printStackTrace();
                    // }
                }

                );
            }
        });

        txtTotalRecebido.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), () ->

                {
                    try {
                        ScreenUtils.changeScreenController(txtStatus, "/com/canes/statusPagamento.fxml",
                                "Selecionar Status de Pagamento", controller -> {
                                    if (controller instanceof StatusPagamentoController) {

                                        StatusPagamentoController statusPagamentoController = (StatusPagamentoController) controller;

                                        String formaEscolhida = ((StatusPagamentoController) controller)
                                                .getFormaSelecionada();

                                        System.out.println(formaEscolhida);
                                        if (formaEscolhida != null) {
                                            txtStatus.setText(formaEscolhida);
                                        }

                                    }

                                });

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                });

            }
        });

        txtTotalRecebido.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN), () ->

                {
                    try {
                        ScreenUtils.changeScreenController(txtCliente, "/com/canes/cadastroCliente.fxml",
                                "Cadastro Cliente", controller -> {
                                    if (controller instanceof CadastroClienteController) {

                                        // CadastroClienteController cadastroClienteController =
                                        // (CadastroClienteController) controller;

                                        Cliente cliente = ((CadastroClienteController) controller).getClienteSalvo();
                                        Telefone telefone = ((CadastroClienteController) controller).getTelefoneSalvo();
                                        Endereco enderco = ((CadastroClienteController) controller).getEnderecoSalvo();
                                        System.out.println(cliente.getNome());
                                        idCliente = cliente.getId();

                                        if (cliente != null) {
                                            txtCliente.setText(cliente.getNome());
                                        }
                                        if (telefone != null) {
                                            txtTelefone.setText(telefone.getNumero());
                                        }
                                        if (enderco != null) {

                                        }

                                    }

                                });

                    } catch (IOException e) {
                        // 4
                        e.printStackTrace();
                    }

                });

            }
        });

    }

    private void carregarPaneExterna() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/canes/view/produto.fxml"));
            Scene scene = new Scene(loader.load());
            ProdutoController controller = loader.getController();
            controller.setPedidoController(this);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Produto");
            // Mostra e espera fechar
            stage.showAndWait();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void statusPagamento() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("statusPagamento.fxml"));
        Parent root = loader.load();
        StatusPagamentoController controller = loader.getController();

        // Cria uma nova janela (modal)
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
        stage.setTitle("Selecionar Status de Pagamento");

        // Mostra e espera fechar
        stage.showAndWait();

        // Recupera a escolha feita
        String formaEscolhida = controller.getFormaSelecionada();
        if (formaEscolhida != null) {
            txtStatus.setText(formaEscolhida);
        }

    }

    @FXML
    private void formaPagamento() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormaPagamento.fxml"));
        Parent root = loader.load();

        // Pega o controller da tela de forma de pagamento
        FormaPagamentoController controller = loader.getController();

        // Cria uma nova janela (modal)
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
        stage.setTitle("Selecionar Forma de Pagamento");

        // Mostra e espera fechar
        stage.showAndWait();

        // Recupera a escolha feita
        String formaEscolhida = controller.getFormaSelecionada();
        if (formaEscolhida != null) {
            txtPagamento.setText(formaEscolhida);
        }

    }

    Long item = 1L;

    @FXML
    void onEnterActionDesconto(ActionEvent event) {

        lblDesconto.setText(txtDesconto.getText());
        desconto();

    }

    @FXML
    void onEnterAction(ActionEvent event) {

        try {

            ProdutoService produtoService = new ProdutoService();
            List<Produto> produtos = produtoService.buscarTodos();

            String codigo = txtCodigo.getText();
            Produto encontrado = null;

            // Busca o produto certo
            for (Produto p : produtos) {
                if (codigo.equals(p.getCodigo())) {
                    encontrado = p;
                    break;
                }
            }

            // Se não encontrou, mostra erro e sai
            if (encontrado == null) {
                AlertUtil.mostrarErro("Código não cadastrado!");
                txtCodigo.setText("");
                return;
            }

            // =============================
            // PRODUTO OK
            // =============================

            String vlrVenda = String.format(new Locale("pt", "BR"), "%.2f", encontrado.getValorVenda());
            txtValorUnitario.setText(vlrVenda);

            String nomeProduto = encontrado.getNome();
            Integer quant = Integer.parseInt(txtQuant.getText());
            double valorUnit = encontrado.getValorVenda();
            double total = quant * valorUnit;

            PedidoDPO pe = new PedidoDPO(item, codigo, nomeProduto, quant, valorUnit, total, null, null, null, null,
                    null);

            tabelaPedido.getItems().add(pe);
            tabelaPedido.scrollTo(pe);

            item++;

            // 🔹 ADICIONA/ATUALIZA A LINHA DE TOTAL
            //atualizarLinhaTotal();

            totalQuant();
            totalValor();
            desconto();

            txtCodigo.clear();

        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.mostrarErro("Erro ao processar: " + e.getMessage());

        }
    }

    private void novoPedido() {

        // 1️⃣ Limpa a tabela
        tabelaPedido.getItems().clear();

        // 2️⃣ Reseta contador de itens
        item = 1L;

        // 3️⃣ Limpa totais
        lblTotal.setText("0,00");
        lblQuant.setText("0");
        lblDesconto.setText("0,00");
        txtQuant.setText("1");

        // 4️⃣ Limpa campos de entrada
        txtCodigo.clear();
        txtValorUnitario.clear();
        txtStatus.clear();
        txtPagamento.clear();
        txtTotalRecebido.clear();
        txtCliente.clear();
        txtTelefone.clear();

        // 5️⃣ Foco no código
        txtCodigo.requestFocus();
    }

    public void receberProduto(Produto produto) {
        if (produto != null) {

            String codigo = produto.getCodigo();
            String produt = produto.getNome();
            Integer quant = Integer.parseInt(txtQuant.getText());
            double unitario = produto.getValorVenda();
            String txtUnitario = String.valueOf(unitario);

            txtValorUnitario.setText(nf.format(unitario));
            double total = quant * unitario;

            PedidoDPO p = new PedidoDPO(item, codigo, produt, quant, unitario, total, null, null, null, null, null);
            tabelaPedido.getItems().add(p);

            tabelaPedido.scrollTo(p);

            item++;

            totalQuant();
            totalValor();
            desconto();

        }
    }

    private void atualizarLinhaTotal() {

        ObservableList<PedidoDPO> lista = tabelaPedido.getItems();

        // remover total antigo, caso exista
        lista.removeIf(PedidoDPO::isTotalRow);

        // calcular total
        double soma = lista.stream()
                .mapToDouble(PedidoDPO::getTotal) // seu campo total
                .sum();

        Integer somaQuant = lista.stream()
                .mapToInt(PedidoDPO::getQuant) // seu campo total
                .sum();

        // criar a linha total
        PedidoDPO total = new PedidoDPO(null, "", "TOTAL "+ soma, 0, null, 0.0, null, null, null, null, null);
        total.setTotalRow(true);

        lista.add(total);

    }

    private void atualizarTotalNaTabela() {
        ObservableList<PedidoDPO> lista = tabelaPedido.getItems();

        // remove qualquer linha TOTAL antiga
        lista.removeIf(p -> p.getProduto() != null && p.getProduto().equalsIgnoreCase("TOTAL"));

        // soma
        double soma = lista.stream()
                .mapToDouble(PedidoDPO::getTotal)
                .sum();

        // adiciona a nova linha
        PedidoDPO total = new PedidoDPO(null, "", "TOTAL", null, 0.0, soma, null, null, null, null, null);
        lista.add(total);

        // 🔥 importantíssimo → força atualização da tabela!
        Platform.runLater(() -> tabelaPedido.refresh());
        System.out.println(lista);
    }

    @FXML
    void onEnterActionRecebido(ActionEvent event) {

        BigDecimal total = MaskTextField.getValue(txtTotalRecebido);
        BigDecimal subtotal = MaskTextField.getValue(txtSubTotal);

        BigDecimal soma = total.subtract(subtotal);
        if (total.compareTo(subtotal) <= 0) {
            txtTroco.setText("0");
        } else {
            txtTroco.setText(soma.toString());
        }
    }

    private void totalQuant() {
        Long total = tabelaPedido.getItems().stream().mapToLong(PedidoDPO::getQuant).sum();
        String totalString = String.valueOf(total);
        lblQuant.setText(totalString);
    }

    private void totalValor() {
        Double total = tabelaPedido.getItems().stream().mapToDouble(PedidoDPO::getTotal).sum();
        String totalString = String.valueOf(total);
        lblTotal.setText(totalString);

    }

    private void desconto() {
        double total = tabelaPedido.getItems().stream().mapToDouble(PedidoDPO::getTotal).sum();
        BigDecimal totalBig = BigDecimal.valueOf(total);
        BigDecimal desc = new BigDecimal(
                lblDesconto.getText().replace("R$ ", "").replaceAll("[^\\d,\\.]", "").replace(",", ".").trim());

        BigDecimal resultado = totalBig.subtract(desc);

        lblTotal.setText(nf.format(resultado).replace("R$", ""));

    }

    public void realColuna(TableColumn<PedidoDPO, Double> coluna) {

        coluna.setCellFactory(col -> new TableCell<PedidoDPO, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(df.format(valor));
                }
            }
        });

    }

    public static BigDecimal getValueLbl(Label label) {

        String text = label.getText();
        if (text == null || text.isBlank())
            return BigDecimal.ZERO;
        String digits = text.replaceAll("\\D", "");
        try {
            return new BigDecimal(digits).movePointLeft(2);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }

    }

}
