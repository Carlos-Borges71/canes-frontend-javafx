package com.canes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.TelefoneService;
import com.canes.util.MaskTextField;
import com.canes.util.ScreenUtils;
import com.canes.util.TextFieldUtil;

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
    private TextField txtValorUnitario;

    @FXML
    private AnchorPane root;

    private ObservableList<PedidoDPO> listaPedidos;

    private DecimalFormat df;

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

    public void initialize() {

        MaskTextField.valor(txtDesconto);
        MaskTextField.valor(txtSubTotal);
        MaskTextField.valor(txtTotalRecebido);
        MaskTextField.valor(txtTroco);
        MaskTextField.valor(txtValorUnitario);
        MaskTextField.number(txtQuant);
        MaskTextField.applyPhoneMask(txtTelefone);

        tabelaPedido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label placeholder = new Label("Aguardando Pedido!");
        placeholder.setStyle("-fx-text-fill: #152266 ; -fx-font-weight: bold; -fx-font-size: 40px");

        tabelaPedido.setPlaceholder(placeholder);

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

        // alterando a cor quando selecionada a linha
        // tabelaPedido.setRowFactory(tv -> new TableRow<>() {
        // @Override
        // protected void updateItem(tblExibirPedido item, boolean empty) {
        // super.updateItem(item, empty);

        // if (empty || item == null) {
        // setStyle("");

        // } else if (isSelected()) {
        // setStyle("-fx-background-color: #90caf9");

        // } else {
        // setStyle("");
        // }
        // }
        // });

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

            scene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ESCAPE) {

                    try {
                        ScreenUtils.changeScreenElement(txtCodigo, "/com/canes/view/menu.fxml", "MENU", null);
                    } catch (Exception event) {
                        event.printStackTrace();
                    }
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
                        ScreenUtils.changeScreenController(txtPagamento, "/com/canes/formaPagamento.fxml",
                                "Selecionar forma de Pagamento", controller -> {
                                    if (controller instanceof FormaPagamentoController) {

                                        FormaPagamentoController formaPagamentoController = (FormaPagamentoController) controller;

                                        String formaEscolhida = ((FormaPagamentoController) controller)
                                                .getFormaSelecionada();

                                        // Recupera a escolha feita

                                        if (formaEscolhida != null) {
                                            txtPagamento.setText(formaEscolhida);

                                        }
                                    }
                                });
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
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

    Integer item = 1;

    @FXML
    void onEnterActionDesconto(ActionEvent event) {

        lblDesconto.setText(txtDesconto.getText());
        desconto();

    }

    @FXML
    void onEnterAction(ActionEvent event) {
        try {
            colItem.setCellValueFactory(new PropertyValueFactory<>("item"));

            colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

            colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));

            colQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

            colValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
            realColuna(colValorUnitario);

            colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            realColuna(colTotal);

            String codigo = txtCodigo.getText();
            String produt = "Calça";
            int quant = Integer.parseInt(txtQuant.getText());
            double unitario = TextFieldUtil.converterParaDouble(txtValorUnitario.getText());
            double total = quant * unitario;
            PedidoDPO p = new PedidoDPO(item, codigo, produt, quant, unitario, total);

            tabelaPedido.getItems().add(p);

            tabelaPedido.scrollTo(p);

            item += 1;

            totalQuant();
            totalValor();
            desconto();

            txtCodigo.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void receberProduto(Produto produto) {
        if (produto != null) {

            colItem.setCellValueFactory(new PropertyValueFactory<>("item"));

            colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

            colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));

            colQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

            colValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
            realColuna(colValorUnitario);

            colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            realColuna(colTotal);

            String codigo = produto.getCodigo();
            String produt = produto.getNome();
            int quant = Integer.parseInt(txtQuant.getText());
            double unitario = produto.getValorVenda();
            String txtUnitario = String.valueOf(unitario);

            txtValorUnitario.setText(nf.format(unitario));
            double total = quant * unitario;

            PedidoDPO p = new PedidoDPO(item, codigo, produt, quant, unitario, total);
            tabelaPedido.getItems().add(p);

            tabelaPedido.scrollTo(p);

            item += 1;

            totalQuant();
            totalValor();
            desconto();

        }
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
        int total = tabelaPedido.getItems().stream().mapToInt(PedidoDPO::getQuant).sum();
        String totalString = String.valueOf(total);
        lblQuant.setText(totalString);
    }

    private void totalValor() {
        double total = tabelaPedido.getItems().stream().mapToDouble(PedidoDPO::getTotal).sum();
        String totalString = String.valueOf(nf.format(total));
        lblTotal.setText(totalString.replace("R$", ""));

    }

    private void desconto() {
        double total = tabelaPedido.getItems().stream().mapToDouble(PedidoDPO::getTotal).sum();
        BigDecimal totalBig = BigDecimal.valueOf(total);
        BigDecimal desc = new BigDecimal(
                lblDesconto.getText().replace("R$ ", "").replaceAll("[^\\d,\\.]", "").replace(",", ".").trim());

        BigDecimal resultado = totalBig.subtract(desc);

        lblTotal.setText(nf.format(resultado));

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
