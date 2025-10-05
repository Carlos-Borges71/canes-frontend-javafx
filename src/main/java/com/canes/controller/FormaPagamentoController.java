package com.canes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FormaPagamentoController {


       @FXML
    private RadioButton radioDinheiro;

    @FXML
    private RadioButton radioMaestro;

    @FXML
    private RadioButton radioMaster;

    @FXML
    private RadioButton radioPix;

    @FXML
    private RadioButton radiovisa;

    @FXML
    private RadioButton radiovisaelectron;

     private String formaSelecionada;

    public String getFormaSelecionada() {
        return formaSelecionada;
    }
    public void initialize(){
        
    }

    @FXML
    private void confirmar() {
        ToggleGroup grupoPagamento = new ToggleGroup();
        radioDinheiro.setToggleGroup(grupoPagamento);
        radioPix.setToggleGroup(grupoPagamento);

        if (radioDinheiro.isSelected()) {
            formaSelecionada = "Dinheiro";
        } else if (radioMaestro.isSelected()) {
            formaSelecionada = "Maestro";
        } else if (radioMaster.isSelected()) {
            formaSelecionada = "MasterCard";
        } else if (radioPix.isSelected()) {
            formaSelecionada = "Pix";
        } else if (radiovisa.isSelected()) {
            formaSelecionada = "Visa";
        } else if (radiovisaelectron.isSelected()) {
            formaSelecionada = "VisaEletron";
        }
        
        // Fecha a janela
        Stage stage = (Stage) radioDinheiro.getScene().getWindow();
        stage.close();
    }
}


