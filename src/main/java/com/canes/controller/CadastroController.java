package com.canes.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.ScreenUtils;
import com.canes.util.TextFieldUtil;
import com.canes.util.UserSession;
import com.canes.model.Cliente;
import com.canes.model.Fornecedor;
import com.canes.model.Produto;
import com.canes.model.Usuario;
import com.canes.services.ClienteService;
import com.canes.services.EnderecoService;
import com.canes.services.FornecedorService;
import com.canes.services.NotaFiscalService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.services.UsuarioService;
import com.canes.util.AlertUtil;
import com.canes.util.ValidadorSenha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CadastroController implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLimpar;

    @FXML
    private ImageView btnNoVisivelReSenha;

    @FXML
    private ImageView btnNoVisivelSenha;

    @FXML
    private Label btnTel;

    @FXML
    private Label feedBackLabel;

    @FXML
    private Label feedBackLabel2;
    @FXML
    private Label labelSenhaRepita;

    @FXML
    private ImageView btnVisivelReSenha;

    @FXML
    private ImageView btnVisivelSenha;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtNoSenha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtReNoSenha;

    @FXML
    private ComboBox<?> txtSetor;

    @FXML
    private VBox vBoxMenos;

    @FXML
    private TextField txtcel;

    @FXML
    private PasswordField passwordReSenha;

    @FXML
    private PasswordField passwordSenha;

    @FXML
    private Label txtOperador;

    @FXML
    private VBox vBoxTel;

    private List<TextField> campos = new ArrayList<>();

    @FXML
    private Button btnCadastrarClient;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnFornec;

    @FXML
    private Button btnCadastrarFornec;

    @FXML
    private Button btnLimparClient;

    @FXML
    private Button btnLimparFornec;

    @FXML
    private Label btnTelClient;

    @FXML
    private Button btnUser;

    @FXML
    private TextField txtBairroFornec;

    @FXML
    private TextField txtCepFornec;

    @FXML
    private TextField txtCelFornec;

    @FXML
    private TextField txtCidadeFornec;

    @FXML
    private TextField txtCnpjFornec;

    // @FXML
    // private TextField txtCodigoFornec;

    @FXML
    private TextField txtEstadoFornec;

    @FXML
    private TextField txtLogradouroFornec;

    @FXML
    private TextField txtNomeFornec;

    @FXML
    private TextField txtNotaFiscalFornec;

    @FXML
    private TextField txtNumeroFornec;

    // @FXML
    // private TextField txtProdutoFornec;

    // @FXML
    // private TextField txtQuantFornec;

    // @FXML
    // private TextField txtTamanhoFornec;

    // @FXML
    // private TextField txtValorCompraFornec;

    // @FXML
    // private TextField txtValorVendaFornec;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Label lblClient;

    @FXML
    private Label lblFornec;

    @FXML
    private Label lbluser;

    @FXML
    private Pane paneCliente;

    @FXML
    private Pane paneUser;

    @FXML
    public Pane paneFornec;

    @FXML
    private TextField txtBairroClient;

    @FXML
    private TextField txtCepClient;

    @FXML
    private TextField txtCidadeClient;

    @FXML
    private TextField txtEstadoClient;

    @FXML
    private TextField txtLogradouroClient;

    @FXML
    private TextField txtNomeClient;

    @FXML
    private TextField txtNumeroClient;

    @FXML
    private TextField txtcelClient;

    @FXML
    private VBox vBoxTelClient;

    @FXML
    private VBox vBoxTelFornec;
    

    

    TextField newText = new TextField();

    @FXML
    void onActionFornec(ActionEvent event) {
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(true);
        lblFornec.setTextFill(Color.RED);

        txtNomeFornec.requestFocus();
        ;

    }

    @FXML
    void onActionPedido(ActionEvent event) {

    }

    @FXML
    void onActionUser(ActionEvent event) {
        paneUser.setVisible(true);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.RED);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        txtNome.requestFocus();
    }

    @FXML
    void onActionVoltar(MouseEvent event) {

        try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/view/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onactionClient(ActionEvent event) {
        paneUser.setVisible(false);
        paneCliente.setVisible(true);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.RED);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        txtNomeClient.requestFocus();
    }

    //
    @FXML
    void onclickLimparFornec(ActionEvent event) {

        txtNomeFornec.clear();
        txtCelFornec.clear();
        txtLogradouroFornec.clear();
        txtNumeroFornec.clear();
        txtBairroFornec.clear();
        txtCidadeFornec.clear();
        txtEstadoFornec.clear();
        txtCepFornec.clear();

    }

    Integer estoque = null;

    @FXML
    void onClickCadastrarFornec(ActionEvent event) {

        if (txtNomeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumeroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidadeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstadoFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCepFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtCelFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;

        }

        try {

            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setEmpresa(txtNomeFornec.getText());
            fornecedor.setCnpjCpf(txtCnpjFornec.getText());
            System.out.println(fornecedor.getCnpjCpf());

            FornecedorService fornecedorService = new FornecedorService();

            Long fornecedorId = fornecedorService.salvarFornecedor(fornecedor);

            // Produto produto = new Produto();

            // String codigo = txtCodigoFornec.getText();
            // String nome = txtProdutoFornec.getText();
            // Double valorCompra =
            // Double.parseDouble(txtValorCompraFornec.getText().replaceAll("[^\\d,\\.]",
            // "").replace( ",","."));
            // Double valorVenda =
            // Double.parseDouble(txtValorVendaFornec.getText().replaceAll("[^\\d,\\.]",
            // "").replace( ",","."));
            // Integer quantCompra = Integer.parseInt(txtQuantFornec.getText());
            // estoque = 5;

            // ProdutoService produtoService = new ProdutoService();

            // produtoService.salvarProduto(codigo,nome, estoque, valorCompra, valorVenda,
            // quantCompra, fornecedorId);

            Integer notaFisccal = Integer.parseInt(txtNotaFiscalFornec.getText());
            String data = instanteFormatado;

            NotaFiscalService ntFiscal = new NotaFiscalService();

            ntFiscal.salvarNotaFiscal(notaFisccal, data, fornecedorId);

            String numeroTel = txtCelFornec.getText();
            TelefoneService telefoneService = new TelefoneService();

            telefoneService.salvarTelefone(numeroTel, null, null, fornecedorId);

            if (campos != null) {
                for (TextField campo : campos) {

                    String numeroTelSeg = campo.getText();
                    telefoneService.salvarTelefone(numeroTelSeg, null, null, fornecedorId);
                }

            }

            String logradouro = txtLogradouroFornec.getText();
            String numero = txtNumeroFornec.getText();
            String bairro = txtBairroFornec.getText();
            String cidade = txtCidadeFornec.getText();
            String estado = txtEstadoFornec.getText();
            String cep = txtCepFornec.getText();

            EnderecoService enderecoService = new EnderecoService();
            enderecoService.salvarEndereco(logradouro, numero, bairro, cidade, estado, cep, null, null, fornecedorId);

            txtNomeFornec.clear();
            txtCelFornec.clear();
            txtLogradouroFornec.clear();
            txtNumeroFornec.clear();
            txtBairroFornec.clear();
            txtCidadeFornec.clear();
            txtEstadoFornec.clear();
            txtCepFornec.clear();
            txtCnpjFornec.clear();
            // txtProdutoFornec.clear();
            // txtValorCompraFornec.clear();
            // txtValorVendaFornec.clear();
            // txtTamanhoFornec.clear();
            // txtCodigoFornec.clear();
            txtNotaFiscalFornec.clear();
        

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/canes/cadastroProduto.fxml"));
            Parent root = loader.load(); // precisa carregar antes!

            // Agora pega o controller
            CadastroProdutoController produtoController = loader.getController();

            // E passa o ID

            produtoController.setFornecedor(fornecedorId);

            // Mostra a tela (pode ser nova janela ou mesma)
            Stage stage = new Stage();
            stage.setTitle("Tela de Produto");
            stage.setScene(new Scene(root));
            stage.show();

            // ScreenUtils.openNewWindow("/com/canes/cadastroProduto.fxml",
            // "Cadastro de Produto", controller -> {
            // if (controller instanceof CadastroProdutoController) {

            // CadastroProdutoController cadastroProdutoController =
            // (CadastroProdutoController) controller;

            // cadastroProdutoController.setFornecedor(fornecedorId);

            // }

            // });

        } catch (Exception e) {
            AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());
            System.out.println(e.getMessage());
        }

        // try {
        // ScreenUtils.changeScreen(event, "/com/canes/view/menu.fxml", "Menu", null);
        // } catch (Exception e) {

        // e.printStackTrace();
        // }

    }

    //
    @FXML
    void onClickLimparFornec(ActionEvent event) {

        txtNomeFornec.clear();
        txtCelFornec.clear();
        txtLogradouroFornec.clear();
        txtNumeroFornec.clear();
        txtBairroFornec.clear();
        txtCidadeFornec.clear();
        txtEstadoFornec.clear();
        txtCepFornec.clear();
        txtCnpjFornec.clear();
        // txtProdutoFornec.clear();
        // txtValorCompraFornec.clear();
        // txtValorVendaFornec.clear();
        // txtTamanhoFornec.clear();
        // txtCodigoFornec.clear();
        txtNotaFiscalFornec.clear();
        

    }

    @FXML
    void onclickLimparClient(ActionEvent event) {
        HBox linha = new HBox();
        txtNomeClient.clear();
        txtcelClient.clear();
        txtLogradouroClient.clear();
        txtNumeroClient.clear();
        txtBairroClient.clear();
        txtCidadeClient.clear();
        txtEstadoClient.clear();
        txtCepClient.clear();
        
       

    }

    @FXML
    void onClickcadastrarClient(ActionEvent event) {

        if (txtNomeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumeroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidadeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstadoClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCepClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtcelClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;

        } else {

            try {

                Cliente cliente = new Cliente();

                cliente.setNome(txtNomeClient.getText());

                cliente.setInstante(instanteFormatado);

                ClienteService ClienteService = new ClienteService();
                Long clienteId = ClienteService.salvarCliente(cliente);

                String numeroTel = txtcelClient.getText();
                TelefoneService telefoneService = new TelefoneService();

                telefoneService.salvarTelefone(numeroTel, (Long) null, clienteId, null);

                if (campos != null) {
                    for (TextField campo : campos) {

                        String numeroTelSeg = campo.getText();
                        telefoneService.salvarTelefone(numeroTelSeg, null, clienteId, null);
                    }

                }

                String logradouro = txtLogradouroClient.getText();
                String numero = txtNumeroClient.getText();
                String bairro = txtBairroClient.getText();
                String cidade = txtCidadeClient.getText();
                String estado = txtEstadoClient.getText();
                String cep = txtCepClient.getText();

                EnderecoService enderecoService = new EnderecoService();
                enderecoService.salvarEndereco(logradouro, numero, bairro, cidade, estado, cep, null, clienteId, null);

            } catch (Exception e) {
                AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());

            }

            try {
                ScreenUtils.changeScreen(event, "/com/canes/view/menu.fxml", "Menu", null);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    @FXML
    void onClickTelClient(MouseEvent event) {

        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("/com/canes/img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTelClient.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTelClient.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTelClient.getChildren().add(linha);
    }

    @FXML
    void onClickTelFornec(MouseEvent event) {

        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("/com/canes/img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTelFornec.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTelFornec.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTelFornec.getChildren().add(linha);
    }

    @FXML
    void onClickEnviar(MouseEvent event) {

    }

    @FXML
    void onClickNoVisivelReSenha(MouseEvent event) {

        passwordReSenha.setText(passwordReSenha.getText());

        passwordReSenha.setVisible(true);
        passwordReSenha.setManaged(true);
        btnVisivelReSenha.setVisible(true);
        btnVisivelReSenha.setManaged(true);

        txtReNoSenha.setVisible(false);
        txtReNoSenha.setManaged(false);
        btnNoVisivelReSenha.setVisible(false);
        btnNoVisivelReSenha.setManaged(false);

    }

    @FXML
    void onClickNoVisivelSenha(MouseEvent event) {

        passwordSenha.setText(txtNoSenha.getText());

        passwordSenha.setVisible(true);
        passwordSenha.setManaged(true);
        btnVisivelSenha.setVisible(true);
        btnVisivelSenha.setManaged(true);

        txtNoSenha.setVisible(false);
        txtNoSenha.setManaged(false);
        btnNoVisivelSenha.setVisible(false);
        btnNoVisivelSenha.setManaged(false);

    }

    @FXML
    void onClickVisivelReSenha(MouseEvent event) {

        if (passwordReSenha.isVisible()) {
            txtReNoSenha.setText(passwordReSenha.getText());

            passwordReSenha.setVisible(false);
            passwordReSenha.setManaged(false);
            btnVisivelReSenha.setVisible(false);
            btnVisivelReSenha.setManaged(false);

            txtReNoSenha.setVisible(true);
            txtReNoSenha.setManaged(true);
            btnNoVisivelReSenha.setVisible(true);
            btnNoVisivelReSenha.setManaged(true);

        }

    }

    @FXML
    void onClickVisivelSenha(MouseEvent event) {

        if (passwordSenha.isVisible()) {
            txtNoSenha.setText(passwordSenha.getText());

            passwordSenha.setVisible(false);
            passwordSenha.setManaged(false);
            btnVisivelSenha.setVisible(false);
            btnVisivelSenha.setManaged(false);

            txtNoSenha.setVisible(true);
            txtNoSenha.setManaged(true);
            btnNoVisivelSenha.setVisible(true);
            btnNoVisivelSenha.setManaged(true);

        }

    }

    Instant instante = Instant.now();

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    String instanteFormatado = formatter.format(instante);

    @FXML
    void onClickcadastrar(ActionEvent event) {

        if (txtNome.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouro.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumero.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairro.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidade.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstado.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCep.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtcel.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;
        } else if (txtSetor.getValue() == null) {
            AlertUtil.mostrarErro("O campo Setor não \npode ficar vazio! \nselecione uma opção");
            return;
        } else if (txtLogin.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo login não \npode ficar vazio!.");
            return;
        } else if (passwordSenha.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo senha não \npode ficar vazio!.");
            return;
        } else if (passwordReSenha.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo repita a senha não \npode ficar vazio!.");
            return;
        } else {

            try {

                Usuario usuario = new Usuario();

                usuario.setSetor(txtSetor.getValue().toString());
                usuario.setNome(txtNome.getText());
                usuario.setLogin(txtLogin.getText());
                usuario.setInstante(instanteFormatado);
                usuario.setSenha(passwordSenha.getText());

                UsuarioService usuarioService = new UsuarioService();
                Long usuarioId = usuarioService.salvarUsuario(usuario);

                String numeroTel = txtcel.getText();
                TelefoneService telefoneService = new TelefoneService();

                telefoneService.salvarTelefone(numeroTel, usuarioId, null, null);

                if (campos != null) {
                    for (TextField campo : campos) {

                        String numeroTelSeg = campo.getText();
                        telefoneService.salvarTelefone(numeroTelSeg, usuarioId, null, null);
                    }

                }

                String logradouro = txtLogradouro.getText();
                String numero = txtNumero.getText();
                String bairro = txtBairro.getText();
                String cidade = txtCidade.getText();
                String estado = txtEstado.getText();
                String cep = txtCep.getText();

                EnderecoService enderecoService = new EnderecoService();
                enderecoService.salvarEndereco(logradouro, numero, bairro, cidade, estado, cep, usuarioId, null, null);

            } catch (Exception e) {
                AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());

            }

            try {
                ScreenUtils.changeScreen(event, "/com/canes/view/menu.fxml", "Menu", null);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        System.out.println("Dados coletados");
        System.out.println(txtSetor.getValue());
        System.out.println(txtLogradouro.getText());
        System.out.println(txtLogin.getText());
        System.out.println(passwordSenha.getText());
        System.out.println(txtcel.getText());
        System.out.println(txtEstado.getText());
        System.out.println(txtNome.getText());
        System.out.println(txtCidade.getText());
        for (TextField campo : campos) {
            System.out.println(campo.getText());
        }

    }

    @FXML
    void onclickLimpar(ActionEvent event) {

        txtNome.clear();
        txtSetor.setValue(null);
        txtLogin.clear();
        passwordSenha.clear();
        passwordReSenha.clear();
        txtcel.clear();
        txtLogradouro.clear();
        txtNumero.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtCep.clear();
        txtReNoSenha.clear();
        txtNoSenha.clear();

    }

    @FXML
    void onclickTel(MouseEvent event) {

        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("/com/canes/img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTel.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTel.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTel.getChildren().add(linha);

    }

    @FXML
    void onMouseEntered(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnCadastrar);
    }

    @FXML
    void onMouseExited(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnCadastrar);
    }

    @FXML
    void onclickLimparExited(MouseEvent event) {

        // HouverEffectUtil.apllyHouverSair(btnLimpar);

    }

    @FXML
    void onclickLimparEntered(ActionEvent event) {

        // HouverEffectUtil.apllyHouverSobre(btnLimpar);

    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        txtNome.requestFocus();

        // MaskTextField.validarNaoVazio(txtNome, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtLogradouro, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtLogin, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtNumero, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtBairro, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtCidade, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtEstado, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtCep, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtcel, btnCadastrar);
        // MaskTextField.validarNaoVazio(passwordSenha, btnCadastrar);
        // MaskTextField.validarNaoVazio(passwordReSenha, btnCadastrar);

        // MaskTextField.limitarCaracteresFixos(txtcelClient, 15, btnCadastrarClient);
        // MaskTextField.limitarCaracteresFixos(txtCepClient, 9, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtNomeClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtLogradouroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtNumeroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtBairroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtCidadeClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtEstadoClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtCepClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtcelClient, btnCadastrarClient);

        lblClient.setMouseTransparent(true);
        lbluser.setMouseTransparent(true);
        lblFornec.setMouseTransparent(true);

        btnClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnClient);
        });

        btnUser.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnUser);
        });

        btnFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFornec);
        });

        btnClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnClient);
        });

        btnUser.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnUser);
        });

        btnFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnFornec);
        });

        btnLimpar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimpar);
        });

        btnLimpar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimpar);
        });

        btnCadastrarClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnCadastrarClient);
        });

        btnCadastrarClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnCadastrarClient);
        });

        btnLimparClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparClient);
        });

        btnLimparClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparClient);
        });

        btnLimparFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparFornec);
        });

        btnLimparFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparFornec);
        });

        btnCadastrarFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnCadastrarFornec);
        });

        btnCadastrarFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnCadastrarFornec);
        });

        MaskTextField.applyPhoneMask(txtcel);

        MaskTextField.applyCepMask(txtCep);

        MaskTextField.applyStateMask(txtEstado);

        TextFieldUtil.aplicarCapitalizacao(txtNome);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouro);
        TextFieldUtil.aplicarCapitalizacao(txtBairro);
        TextFieldUtil.aplicarCapitalizacao(txtCidade);

        MaskTextField.applyPhoneMask(txtcelClient);

        MaskTextField.applyCepMask(txtCepClient);

        MaskTextField.applyStateMask(txtEstadoClient);

        TextFieldUtil.aplicarCapitalizacao(txtNomeClient);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouroClient);
        TextFieldUtil.aplicarCapitalizacao(txtBairroClient);
        TextFieldUtil.aplicarCapitalizacao(txtCidadeClient);

        MaskTextField.applyPhoneMask(txtCelFornec);

        MaskTextField.applyCepMask(txtCepFornec);

        MaskTextField.applyStateMask(txtEstadoFornec);

        TextFieldUtil.aplicarCapitalizacao(txtNomeFornec);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouroFornec);
        TextFieldUtil.aplicarCapitalizacao(txtBairroFornec);
        TextFieldUtil.aplicarCapitalizacao(txtCidadeFornec);
        // TextFieldUtil.aplicarCapitalizacao(txtProdutoFornec);
        MaskTextField.applyCnpjMask(txtCnpjFornec);
        // MaskTextField.quantNumbery(txtCodigoFornec, 13);
        // MaskTextField.quantNumbery(txtQuantFornec, 2);
        // MaskTextField.valor(txtValorCompraFornec);
        // MaskTextField.valor(txtValorVendaFornec);
        // MaskTextField.limitarCaracteresFixos(txtTamanhoFornec, 2);
        MaskTextField.quantNumbery(txtNotaFiscalFornec, 10);

        String nome = UserSession.getInstance().getNomeUsuario();
        String login = UserSession.getInstance().getlogin();

        txtOperador.setText("Operador: " + nome);

        passwordSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                feedBackLabel.setText("");
            } else if (ValidadorSenha.isSenhaValida(newVal)) {
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                passwordSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;");
                passwordSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            }

        });

        txtNoSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                feedBackLabel.setText("");
                feedBackLabel2.setText("");
            } else if (ValidadorSenha.isSenhaValida(newVal)) {
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                txtNoSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;");
                txtNoSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");

            }

        });

        // não vendo senha
        // não vendo senha - repita vendo senha
        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        txtReNoSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(txtReNoSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (txtReNoSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

    }

}
