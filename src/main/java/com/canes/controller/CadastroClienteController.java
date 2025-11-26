package com.canes.controller;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.TextFieldUtil;
import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Telefone;
import com.canes.model.dpo.ClienteDPO;
import com.canes.services.ClienteService;
import com.canes.services.EnderecoService;
import com.canes.services.TelefoneService;
import com.canes.util.AlertUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroClienteController {

    @FXML
    private Button btnCadastrarClient;

    @FXML
    private Button btnLimparClient;

    @FXML
    private Label btnTelClient;

    @FXML
    private Pane paneCliente;

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

    private List<TextField> campos = new ArrayList<>();

    private Cliente clienteSalvo;
    private Telefone telefoneSalvo;
    private Endereco enderecoSalvo;

    private String telefoneInicial;

    @FXML
    void onclickLimparClient(ActionEvent event) {

        txtNomeClient.clear();
        txtcelClient.clear();
        txtLogradouroClient.clear();
        txtNumeroClient.clear();
        txtBairroClient.clear();
        txtCidadeClient.clear();
        txtEstadoClient.clear();
        txtCepClient.clear();

    }

    public Cliente getClienteSalvo() {
        return clienteSalvo;
    }

    public Endereco getEnderecoSalvo() {
        return enderecoSalvo;
    }

    public Telefone getTelefoneSalvo() {
        return telefoneSalvo;
    }

    Instant instante = Instant.now();

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    String instanteFormatado = formatter.format(instante);

    @FXML
    void onClickcadastrarClient(ActionEvent event) {

        clienteSalvo = new Cliente(null, txtNomeClient.getText(), null, null, null);
        telefoneSalvo = new Telefone(txtcelClient.getText());
        enderecoSalvo = new Endereco(txtLogradouroClient.getText(), txtNumeroClient.getText(),
                txtBairroClient.getText(), txtCidadeClient.getText(), txtEstadoClient.getText(),
                txtCepClient.getText());

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

                String nome = txtNomeClient.getText();
                String data = instanteFormatado;

                ClienteDPO clientes = new ClienteDPO(
                        nome,
                        data

                );

                ClienteService clienteService = new ClienteService();
                Long clienteId = clienteService.salvarCliente(clientes);

                String numeroTel = txtcelClient.getText();
                TelefoneService telefoneService = new TelefoneService();

                telefoneService.salvarTelefone(numeroTel, null, clienteId, null);

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

                AlertUtil.mostrarSucesso("Cadastro do Cliente " + txtNomeClient.getText() + "\nSalvo com sucesso");

                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());

                System.out.println(e.getMessage());
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
    void onClickEnviar(MouseEvent event) {

    }

    @FXML
    void onclickLimparExited(MouseEvent event) {

        // HouverEffectUtil.apllyHouverSair(btnLimpar);

    }

    @FXML
    void onclickLimparEntered(ActionEvent event) {

        // HouverEffectUtil.apllyHouverSobre(btnLimpar);

    }

    @FXML
    void onMouseEntered(MouseEvent event) {

    }

    @FXML
    void onMouseExited(MouseEvent event) {

    }

    public void setTelefoneInicial(String telefone) {

        this.telefoneInicial = telefone;

        Platform.runLater(() -> {
            txtcelClient.setText(telefone);
            // if (txtcelClient != null) {
            //     txtcelClient.setText(telefone);
            // }
        });

    }

    public void initialize() {

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

        MaskTextField.applyPhoneMask(txtcelClient);

        MaskTextField.applyCepMask(txtCepClient);

        MaskTextField.applyStateMask(txtEstadoClient);

        TextFieldUtil.aplicarCapitalizacao(txtNomeClient);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouroClient);
        TextFieldUtil.aplicarCapitalizacao(txtBairroClient);
        TextFieldUtil.aplicarCapitalizacao(txtCidadeClient);

    }

}
