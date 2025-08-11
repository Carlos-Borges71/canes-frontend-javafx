package com.canes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskCep;
import com.canes.util.MaskEstado;
import com.canes.util.MaskUtil;
import com.canes.util.TextFieldUtil;
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
    private VBox vBoxTel;

    private List<TextField> campos = new ArrayList<>();

    private MaskUtil maskUtil = new MaskUtil();

    private MaskCep maskCep = new MaskCep();

    private  MaskEstado maskEstado = new MaskEstado();

    private  TextFieldUtil textFieldUtil = new TextFieldUtil();
    

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

         if(passwordReSenha.isVisible()){
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

        
         if(passwordSenha.isVisible()){
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

    @FXML
    void onClickcadastrar(ActionEvent event) {

        
        if(txtNome.getText().isEmpty() || txtNome.getText().isBlank()){
            txtNome.requestFocus();
            txtNome.setPromptText("Digite o nome");
        
        }

        if(txtcel.getText().isEmpty() || txtcel.getText().isBlank()){
            txtcel.requestFocus();
            txtcel.setPromptText("Digite o celular");
        
        }

        if(txtLogradouro.getText().isEmpty() || txtLogradouro.getText().isBlank()){
            txtLogradouro.requestFocus();
            txtLogradouro.setPromptText("Digite o logradouto");
        
        }

        if(txtLogin.getText().isEmpty() || txtLogin.getText().isBlank()){
            txtLogin.requestFocus();
            txtLogin.setPromptText("Digite o login");
        
        }

        if(txtNumero.getText().isEmpty() || txtNumero.getText().isBlank()){
            txtNumero.requestFocus();
            txtNumero.setPromptText("Digite o número");
        
        }

        if(txtBairro.getText().isEmpty() || txtBairro.getText().isBlank()){
            txtBairro.requestFocus();
            txtBairro.setPromptText("Digite o bairro");
        
        }

        if(txtCidade.getText().isEmpty() || txtCidade.getText().isBlank()){
            txtCidade.requestFocus();
            txtCidade.setPromptText("Digite a cidade");
        
        }

        if(txtEstado.getText().isEmpty() || txtEstado.getText().isBlank()){
            txtEstado.requestFocus();
            txtEstado.setPromptText("Digite o estado");
        
        }

        if(txtCep.getText().isEmpty() || txtCep.getText().isBlank()){
            txtCep.requestFocus();
            txtCep.setPromptText("Digite o cep");
        
        }

        if(passwordSenha.getText().isEmpty() || passwordSenha.getText().isBlank()){
            passwordSenha.requestFocus();
            passwordSenha.setPromptText("Digite o senha");
        
        }

        if(txtReNoSenha.getText().isEmpty() || txtReNoSenha.getText().isBlank()){
            txtReNoSenha.requestFocus();
            txtReNoSenha.setPromptText("Digite o senha");
        
        }

        if(txtSetor.getItems().isEmpty()){
            txtSetor.requestFocus();
            txtSetor.setPromptText("escolha o setor");
        
        }

        System.out.println("Dados coletados");
        System.out.println(txtSetor.getValue());
        System.out.println(txtLogradouro.getText());
        System.out.println(txtLogin.getText());
        System.out.println(passwordSenha.getText());
        System.out.println(txtReNoSenha.getText());
        System.out.println(txtEstado.getText());
        System.out.println(txtNome.getText());
        System.out.println(txtCidade.getText());



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

        //HouverEffectUtil.apllyHouverSair(btnLimpar);

    }

    @FXML
    void onclickLimparEntered(ActionEvent event) {

        //HouverEffectUtil.apllyHouverSobre(btnLimpar);

    }




    @Override
    public void initialize(URL url, ResourceBundle resources) {


        btnLimpar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimpar);
        });

        btnLimpar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimpar);
        });


         maskUtil.applyPhoneMask(txtcel);

         maskCep.applyCepMask(txtCep);

         maskEstado.applyStateMask(txtEstado);

         textFieldUtil.aplicarCapitalizacao(txtNome);







          passwordSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.isEmpty()) {
                feedBackLabel.setText("");
            } else if(ValidadorSenha.isSenhaValida(newVal)){
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                passwordSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;"); 
                passwordSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;" + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
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
        
        

         //não vendo senha
         //não vendo senha - repita vendo senha
         passwordReSenha.textProperty().addListener((obs,oldVal, newVal) -> {

            
            if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

          
         txtReNoSenha.textProperty().addListener((obs,oldVal, newVal) -> {

                if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(passwordSenha.getText().equals(txtReNoSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

         passwordReSenha.textProperty().addListener((obs,oldVal, newVal) -> {

                if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(txtReNoSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });

         passwordReSenha.textProperty().addListener((obs,oldVal, newVal) -> {

                if(newVal.isEmpty()) {
                labelSenhaRepita.setText("");
           
            }else if(passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");
                
            } else {
               labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        
         });


         
 
        
    }

   

}
