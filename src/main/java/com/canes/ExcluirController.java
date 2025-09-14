package com.canes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.ScreenUtils;
import com.canes.util.TextFieldUtil;
import com.canes.util.UserSession;
import com.canes.util.AlertUtil;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ExcluirController implements Initializable{

    
     @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnClient;
    
    @FXML
    private Button btnBuscar;

   @FXML
    private Button btnExcluirProduto;

    @FXML
    private Button btnBuscarFornec;

    @FXML
    private Button btnBuscarProduto;

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
    private Button btnExcluirCliente;

   

    @FXML
    private Button btnFornec;

    @FXML
    private Button btnProduto;



    @FXML
    private Button btnExcluirFornec;

    

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
    private TextField txtCelFornec1;

    @FXML
    private TextField txtCidadeFornec;

    @FXML
    private TextField txtCnpjFornec;

    @FXML
    private TextField txtCodigoFornec;

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

    @FXML
    private TextField txtProdutoFornec;

    @FXML
    private TextField txtQuantFornec;

     @FXML
    private TextField txtTamanhoFornec;

    @FXML
    private TextField txtValorCompraFornec;

    @FXML
    private TextField txtValorVendaFornec;

    

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
    private Pane paneFornec;

    @FXML
    private Pane paneProduto;

   
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
   private Button btnBuscarCliente;

    @FXML
   private Button btnLimparProduto;

    @FXML
    private TextField txtCelClient1;

   

    @FXML
    private TextField txtcelClient;

    @FXML
    private TextField txtCelUsuario;

    @FXML
    private VBox vBoxTelClient;

    @FXML
    private VBox vBoxTelFornec;

    @FXML
    private Label lblProduto;

    @FXML
    void onActionProduto(ActionEvent event) {

        paneProduto.setVisible(true);
        lblProduto.setTextFill(Color.RED);
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);        
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        

    }

    @FXML
    void onClickBuscar(ActionEvent event) {

    }

    @FXML
    void onClickBuscarCliente(ActionEvent event) {

    }

     @FXML
    void onClickBuscarClient(ActionEvent event) {

    }

    @FXML
    void onClickBuscarFornec(ActionEvent event) {

    }

     @FXML
    void onClickBuscarProduto(ActionEvent event) {

    }

    @FXML
    void onActionFornec(ActionEvent event) {
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);        
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(true);
        lblFornec.setTextFill(Color.RED);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);

    }

    @FXML
    void onActionPedido(ActionEvent event) {

        paneProduto.setVisible(true);
        lblProduto.setTextFill(Color.RED);
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);        
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        

    }

    @FXML
    void onActionUser(ActionEvent event) {
        paneUser.setVisible(true);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.RED);        
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        

    }

    @FXML
    void onActionVoltar(MouseEvent event) {


         try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/menu.fxml", "Menu", null);
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
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
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

    @FXML
    void onClickExcluir(ActionEvent event) {

    }

    @FXML
    void onClickExcluirCliente(ActionEvent event) {

    }

     @FXML
    void onClickExcluirFornec(ActionEvent event) {

    }

    @FXML
    void onClickLimparProduto(ActionEvent event) {

    }

    @FXML
    void onClickExcluirProduto(ActionEvent event) {

    }



    @FXML
    void onClickCadastrarFornec(ActionEvent event) {

        if(txtNomeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro( "O campo nome não pode ficar vazio!." );
            return;
        }
        else if(txtLogradouroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro( "O campo Logradouro não \npode ficar vazio!." );
            return;
        }
        else if(txtNumeroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro( "O campo número não \npode ficar vazio!." );
            return;
        }
        else if(txtBairroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!." );
            return;
        }
        
        else if(txtCidadeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!." );
            return;
        }
        
        else if(txtEstadoFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!." );
            return;
        }
        
        else if(txtCepFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!." );
            return;
        }
        
        else if(txtCelFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!." );
            return;
        }
        else {
            AlertUtil.mostrarSucesso("Cadastro do Cliente " + txtNomeFornec.getText() + "\nSalvo com sucesso");

            try{ 
            ScreenUtils.changeScreen(event, "/com/canes/menu.fxml", "Menu", null);
            }catch(Exception e) {

            e.printStackTrace();
            }
        }

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
        txtProdutoFornec.clear();
        txtValorCompraFornec.clear();
        txtValorVendaFornec.clear();
        txtTamanhoFornec.clear();
        txtCodigoFornec.clear();
        txtNotaFiscalFornec.clear();
        

    }

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

    @FXML
    void onClickcadastrarClient(ActionEvent event) {

        if(txtNomeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo nome não pode ficar vazio!." );
            return;
        }
        else if(txtLogradouroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo Logradouro não \npode ficar vazio!." );
            return;
        }
        else if(txtNumeroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo número não \npode ficar vazio!." );
            return;
        }
        else if(txtBairroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!." );
            return;
        }
        
        else if(txtCidadeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!." );
            return;
        }
        
        else if(txtEstadoClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!." );
            return;
        }
        
        else if(txtCepClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!." );
            return;
        }
        
        else if(txtcelClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!." );
            return;
        }
        else {
            AlertUtil.mostrarSucesso("Cadastro do Cliente " + txtNomeClient.getText() + "\nSalvo com sucesso");

            try{ 
            ScreenUtils.changeScreen(event, "/com/canes/menu.fxml", "Menu", null);
            }catch(Exception e) {

            e.printStackTrace();
            }
        }

    }
   
    @FXML
    void onClickTelClient(MouseEvent event) {
         TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
        "-fx-border-radius: 7;" + "-fx-text-fill: fff;" );

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);
        
    

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
        "-fx-border-radius: 7;" + "-fx-text-fill: fff;" );

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);
       
    

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

    
        if(txtNome.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo nome não pode ficar vazio!." );
            return;
        }
         else if(txtLogradouro.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo Logradouro não \npode ficar vazio!." );
            return;
        }
         else if(txtNumero.getText().isEmpty()) {
            AlertUtil.mostrarErro( "O campo número não \npode ficar vazio!." );
            return;
        }
         else if(txtBairro.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!." );
            return;
        }
        
         else if(txtCidade.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!." );
            return;
        }
        
         else if(txtEstado.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!." );
            return;
        }
        
         else if(txtCep.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!." );
            return;
        }
        
         else if(txtcel.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!." );
            return;
        }
        else if(txtSetor.getValue() == null) {
            AlertUtil.mostrarErro("O campo Setor não \npode ficar vazio! \nselecione uma opção" );
            return;
        }
        else if(txtLogin.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo login não \npode ficar vazio!." );
            return;
        }
        else if(passwordSenha.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo senha não \npode ficar vazio!." );
            return;
        }
        else if(passwordReSenha.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo repita a senha não \npode ficar vazio!." );
            return;
        }
        else{
            AlertUtil.mostrarSucesso("Cadastro do Usuário " + txtNome.getText() + "\nSalvo com sucesso");

            try{ 
            ScreenUtils.changeScreen(event, "/com/canes/menu.fxml", "Menu", null);
            }catch(Exception e) {

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
        for (TextField campo : campos){
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
    void onclickBuscar(ActionEvent event) {       

    }
    

    @FXML
    void onclickTel(MouseEvent event) {

       
        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
        "-fx-border-radius: 7;" + "-fx-text-fill: fff;" );

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);
       
    

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
        lblProduto.setMouseTransparent(true);

        btnClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnClient);
        });

        btnUser.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnUser);
        });

        btnFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFornec);
        });

        btnProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnProduto);
        });

        btnClient.setOnMouseExited(e ->{
            HouverEffectUtil.apllyHouverSair(btnClient);
        });

        btnProduto.setOnMouseExited(e ->{
            HouverEffectUtil.apllyHouverSair(btnProduto);
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

         btnExcluirCliente.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnExcluirCliente);
        });

        btnExcluirCliente.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnExcluirCliente);
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

        btnExcluirFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnExcluirFornec);
        });

        btnExcluirFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnExcluirFornec);
        });

        btnBuscar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscar);
        });

        btnBuscar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscar);
        });

        btnBuscarCliente.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarCliente);
        });

        btnBuscarCliente.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarCliente);
        });

        btnBuscarFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarFornec);
        });

        btnBuscarFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarFornec);
        });

        btnBuscarProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarProduto);
        });

        btnBuscarProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarProduto);
        });

         btnLimparProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparProduto);
        });

        btnLimparProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparProduto);
        });

        btnExcluirProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnExcluirProduto);
        });

        btnExcluirProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnExcluirProduto);
        });


         MaskTextField.applyPhoneMask(txtcel);

         MaskTextField.applyCepMask(txtCep);  

         MaskTextField.applyStateMask(txtEstado);

         TextFieldUtil.aplicarCapitalizacao(txtNome);
         TextFieldUtil.aplicarCapitalizacao(txtLogradouro);
         TextFieldUtil.aplicarCapitalizacao(txtBairro);
         TextFieldUtil.aplicarCapitalizacao(txtCidade);

          MaskTextField.applyPhoneMask(txtcelClient);
          MaskTextField.applyPhoneMask(txtCelClient1);

         MaskTextField.applyCepMask(txtCepClient);  

         MaskTextField.applyStateMask(txtEstadoClient);

         TextFieldUtil.aplicarCapitalizacao(txtNomeClient);
         TextFieldUtil.aplicarCapitalizacao(txtLogradouroClient);
         TextFieldUtil.aplicarCapitalizacao(txtBairroClient);
         TextFieldUtil.aplicarCapitalizacao(txtCidadeClient);

         MaskTextField.applyPhoneMask(txtCelFornec);
         MaskTextField.applyPhoneMask(txtCelFornec1);

         MaskTextField.applyCepMask(txtCepFornec);  

         MaskTextField.applyStateMask(txtEstadoFornec);

         TextFieldUtil.aplicarCapitalizacao(txtNomeFornec);
         TextFieldUtil.aplicarCapitalizacao(txtLogradouroFornec);
         TextFieldUtil.aplicarCapitalizacao(txtBairroFornec);
         TextFieldUtil.aplicarCapitalizacao(txtCidadeFornec);
         TextFieldUtil.aplicarCapitalizacao(txtProdutoFornec);
        MaskTextField.applyCnpjMask(txtCnpjFornec);
        MaskTextField.quantNumbery(txtCodigoFornec,13);
        MaskTextField.quantNumbery(txtQuantFornec,2);
        MaskTextField.valor(txtValorCompraFornec);
        MaskTextField.valor(txtValorVendaFornec);
        MaskTextField.limitarCaracteresFixos(txtTamanhoFornec, 2);
        MaskTextField.quantNumbery(txtNotaFiscalFornec, 10);
        
        MaskTextField.applyPhoneMask(txtCelUsuario);

         


         String nome = UserSession.getInstance().getNomeUsuario();
         String login = UserSession.getInstance().getlogin();

         txtOperador.setText("Operador: "+ nome);

         







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
