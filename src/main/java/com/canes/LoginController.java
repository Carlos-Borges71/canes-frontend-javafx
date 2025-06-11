package com.canes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button btEnviar;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    void onClickEnviar(ActionEvent event) {
        String user = txtUser.getText();
        String password = txtPassword.getText();

        if(user.equals("Carlos") && password.equals("1234")){
            System.out.println("nome: "+ user + " senha: "+ password);
        }else{
            System.out.println("ERROUUUU!");
        }

        
        
        txtUser.clear();
        txtPassword.clear();     
        cadastroUsuario();  
    }

    private void cadastroUsuario(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
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

    

}
