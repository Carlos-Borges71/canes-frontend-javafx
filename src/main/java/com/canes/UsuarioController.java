package com.canes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.canes.util.MaskUtil;
import com.canes.util.ValidadorSenha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class UsuarioController implements Initializable{

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

    private MaskUtil maskUtil = new MaskUtil();

    

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

        maskUtil.applyPhoneMask(newText);
    

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

    @Override
    public void initialize(URL url, ResourceBundle resources) {

         maskUtil.applyPhoneMask(txtcel);



          txtSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.isEmpty()) {
                feedBackLabel.setText("");
            } else if(ValidadorSenha.isSenhaValida(newVal)){
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                txtSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;"); 
                txtSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            }

        });


        txtNoSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.isEmpty()) {
                feedBackLabel.setText("");
                feedBackLabel2.setText("");
            } else if(ValidadorSenha.isSenhaValida(newVal)){
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                txtNoSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;"); 
                feedBackLabel2.setStyle("-fx-text-fill: red;");
                txtNoSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
                
            }

        });
        
        //repita vendo senha
        //vendo senha - repita vendo a senha
        txtReSenha.textProperty().addListener((obs,oldVal, newVal) -> {

             if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
             }else if(txtSenha.getText().equals(txtReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
               
            }

        
         });

         //repita não vendo senha
         //vendo senha - repita vendo senha

         txtReNoSenha.textProperty().addListener((obs,oldVal, newVal) -> {

            if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(txtSenha.getText().equals(txtReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

         //repita vendo senha
         //não vendo senha - repita vendo senha
         txtReSenha.textProperty().addListener((obs,oldVal, newVal) -> {

            if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(txtNoSenha.getText().equals(txtReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

         //não vendo senha
         //não vendo senha - repita vendo senha
         txtReNoSenha.textProperty().addListener((obs,oldVal, newVal) -> {
            if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(txtNoSenha.getText().equals(txtReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });
 
         // repita vendo senha
         //não vendo senha - repita vendo senha
         txtReSenha.textProperty().addListener((obs,oldVal, newVal) -> {
            if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(txtNoSenha.getText().equals(txtReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }
             });

             //repita não vendo senha
             //não vendo senha - não vendo
             txtReNoSenha.textProperty().addListener((obs,oldVal, newVal) -> {
                if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
                }else if(txtNoSenha.getText().equals(txtReNoSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

    }

   

}
