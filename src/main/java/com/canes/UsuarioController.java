package com.canes;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    private List<TextField> campos = new ArrayList<>();

    @FXML
    void onClickEnviar(MouseEvent event) {

    }

    @FXML
    void onClickNoVisivelReSenha(MouseEvent event) {

        

         txtReSenha.setText(txtReNoSenha.getText());

            txtReSenha.setVisible(true);
            txtReSenha.setManaged(true);
            btnVisivelReSenha.setVisible(true);
            btnVisivelReSenha.setManaged(true);

            txtReNoSenha.setVisible(false);
            txtReNoSenha.setManaged(false);
            btnNoVisivelReSenha.setVisible(false);
            btnNoVisivelReSenha.setManaged(false);  

    }

    @FXML
    void onClickNoVisivelSenha(MouseEvent event) {

        
         txtSenha.setText(txtNoSenha.getText());

            txtSenha.setVisible(true);
            txtSenha.setManaged(true);
            btnVisivelSenha.setVisible(true);
            btnVisivelSenha.setManaged(true);

            txtNoSenha.setVisible(false);
            txtNoSenha.setManaged(false);
            btnNoVisivelSenha.setVisible(false);
            btnNoVisivelSenha.setManaged(false);    

    }

    @FXML
    void onClickVisivelReSenha(MouseEvent event) {

         if(txtReSenha.isVisible()){
            txtReNoSenha.setText(txtReSenha.getText());

            txtReSenha.setVisible(false);
            txtReSenha.setManaged(false);
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

        
         if(txtSenha.isVisible()){
            txtNoSenha.setText(txtSenha.getText());

            txtSenha.setVisible(false);
            txtSenha.setManaged(false);
            btnVisivelSenha.setVisible(false);
            btnVisivelSenha.setManaged(false);


            txtNoSenha.setVisible(true);
            txtNoSenha.setManaged(true);
            btnNoVisivelSenha.setVisible(true);
            btnNoVisivelSenha.setManaged(true);

            
         }

    }

    @FXML
    void onClickcadastrar(ActionEvent event) {

    }

    @FXML
    void onclickLimpar(ActionEvent event) {

        txtNome.clear();
        txtSetor.setValue(null);
        txtLogin.clear();
        txtSenha.clear();
        txtReSenha.clear();
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
        "-fx-border-radius: 7;" + "-fx-text-fill: fff;" );

        campos.add(newText);
    

        Image imgExcluir = new Image(getClass().getResourceAsStream("img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        //Label labelRemover = new Label("Remover");
        //labelRemover.setStyle("-fx-text-fill: red;" +
        //    "-fx-font-size: 14;" +
            //"-fx-font-weight: bold ;" +
            //"-fx-alignment: center;" +
        //    "-fx-padding: 4 0 0 0;" +
        //    "-fx-cursor: hand; ");


         HBox linha = new HBox(10, newText,img);
         linha.setAlignment(Pos.CENTER);

        vBoxTel.setMargin(linha, new Insets(0, 0, 10, -10));

         

         img.setOnMouseClicked(e -> {
            vBoxTel.getChildren().remove(linha);     
            campos.remove(newText);
        });

         vBoxTel.getChildren().add(linha);

         

         btnCadastrar.setOnAction(e -> {
            System.out.println("Dados digitados");


            for (TextField campo : campos){
                System.out.println(campo.getText());
            }
         });

        

    }

    

   

}
