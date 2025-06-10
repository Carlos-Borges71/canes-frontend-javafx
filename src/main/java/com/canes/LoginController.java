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
        
        System.out.println(txtUser.getText());
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

            stage.show();

            // fechar janela
            Stage stageFc = (Stage)btEnviar.getScene().getWindow();
            stageFc.close();
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    

}
