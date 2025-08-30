package com.canes;

import java.io.IOException;

import com.canes.util.HouverEffectUtil;
import com.canes.util.ScreenUtils;
import com.canes.util.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button btEnviar;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    private ImageView noVisible;
    
    @FXML
    private ImageView visible;

    @FXML
    private TextField txtPassword2;

     @FXML
    private Label aviso;

     @FXML
    private Label labelUser;

   @FXML
    void onNoVisible(DragEvent event) {
        txtUser.setAccessibleHelp("test");
    }


    @FXML
    void onClickEnviar(ActionEvent event) {

        
        // String user = txtUser.getText();
        // String password = txtPassword.getText();

        // if(user.equals("Carlos") && password.equals("1234")){
        //     System.out.println("nome: "+ user + " senha: "+ password);

           

        // }else{

        //     aviso.setText("Login ou senha inválido! Tente novamente.");
        //     aviso.setVisible(true);
        //     PauseTransition pausa = new PauseTransition(Duration.seconds(5));
        //     pausa.setOnFinished(ignored -> aviso.setVisible(false));
        //     pausa.play();

        // }

        
    
    // Exemplo: usuário autenticado com sucesso
    String nome = "Carlos Borges";
    String login = txtUser.getText();

    // Salva na sessão
    UserSession.getInstance().setUsuario(nome, login);

   


        try {
            ScreenUtils.changeScreen(event,"/com/canes/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        
        
        // txtUser.clear();
        // txtPassword.clear();     
        
    }

    private void cadastroUsuario(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Usuario");
            stage.setScene(new Scene(root));

            stage.setMaximized(true);

            stage.show();

            // fechar janela
            Stage stageFc = (Stage)btEnviar.getScene().getWindow();
            stageFc.close();
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }

        

    @FXML
    void onClickPassword(MouseEvent event) {

        
        
        if(txtPassword.isVisible()){
            txtPassword2.setText(txtPassword.getText());

            txtPassword.setVisible(false);
            txtPassword.setManaged(false);
            visible.setVisible(false);
            visible.setManaged(false);


            txtPassword2.setVisible(true);
            txtPassword2.setManaged(true);
            noVisible.setVisible(true);
            noVisible.setManaged(true);

            
         }
    

    }

     @FXML
    void onClickNoVisible(MouseEvent event) {
        

         txtPassword.setText(txtPassword2.getText());

            txtPassword.setVisible(true);
            txtPassword.setManaged(true);
            visible.setVisible(true);
            visible.setManaged(true);

            txtPassword2.setVisible(false);
            txtPassword2.setManaged(false);
            noVisible.setVisible(false);
            noVisible.setManaged(false);         
        
        

    }

    @FXML
    void onClickUser(MouseEvent event) {

        try {
            ScreenUtils.openNewWindow("/com/canes/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

         //cadastroUsuario();  
    }

    @FXML
    void onMouseEtered(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btEnviar);

    }

    @FXML
    void onMouseExited(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btEnviar);

    }


    

}
