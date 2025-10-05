package com.canes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class StatusPagamentoController {


    @FXML
    private RadioButton rdAguardandoPagamento;

    @FXML
    private RadioButton rdPago;

    @FXML
    private RadioButton rdCancelado;

    private String formaSelecionada;


    public String getFormaSelecionada() {
        return formaSelecionada;
    }
    public void initialize(){
        
    }

    @FXML
    void confirmar(ActionEvent event) {
        
       

        if (rdAguardandoPagamento.isSelected()) {
            formaSelecionada = "AGUARDANDO_PAGAMENTO";
        } else if (rdPago.isSelected()) {
            formaSelecionada = "PAGO";
        } else if (rdCancelado.isSelected()) {
            formaSelecionada = "CANCELADO";
        }
        
        // Fecha a janela
        Stage stage = (Stage) rdAguardandoPagamento.getScene().getWindow();
        stage.close();
        
    }
}


