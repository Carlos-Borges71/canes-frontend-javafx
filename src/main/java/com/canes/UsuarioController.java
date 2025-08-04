package com.canes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class UsuarioController {

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
    private TextField txtNoSenha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtReNoSenha;

    @FXML
    private PasswordField txtReSenha;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private ComboBox<?> txtSetor;

     @FXML
    private VBox vBoxMenos;

    @FXML
    private TextField txtcel;

    @FXML
    private VBox vBoxTel;

    @FXML
    void onClickEnviar(MouseEvent event) {

    }

    @FXML
    void onClickNoVisivelReSenha(MouseEvent event) {

    }

    @FXML
    void onClickNoVisivelSenha(MouseEvent event) {

    }

    @FXML
    void onClickVisivelReSenha(MouseEvent event) {

    }

    @FXML
    void onClickVisivelSenha(MouseEvent event) {

    }

    @FXML
    void onClickcadastrar(ActionEvent event) {

    }

    @FXML
    void onclickLimpar(ActionEvent event) {

    }

    @FXML
    void onclickTel(MouseEvent event) {

        TextField newText = new TextField();
        newText.setMaxWidth(132);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
        "-fx-border-radius: 7;" + "-fx-text-fill: fff;" );
        vBoxTel.setMargin(newText, new Insets(0, 0, 10, 0));

        vBoxTel.getChildren().add(newText);

        Label labelMenos = new Label("-");
        labelMenos.setStyle("-fx-text-fill: fff;" +
            "-fx-font-size: 40;" +
            "-fx-font-weight: bold ;" +
            "-fx-font-style: verdana;" +
            "-fx-alignment: center;");
        vBoxMenos.setMargin(labelMenos, new Insets(0, 0, -21, 0));
        vBoxMenos.getChildren().add(labelMenos);


        System.out.println("Clicou");

    }

}
