package com.canes.controller;


import com.canes.util.HouverEffectUtil;
import com.canes.util.ScreenUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController {

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;


    @FXML
    private Button btnPesquisa;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnPedido;

    @FXML
    private Label lblCadastrar;

    @FXML
    private Label lblAtualizar;

    @FXML
    private Label lblExcluir;

    @FXML
    private Label lblPesquisa;

    @FXML
    private Label lblPedido;
    

    @FXML
    private Label lblSair;

    @FXML
    private Label lblUser;

    @FXML
    void onClickAtualizar(ActionEvent event) {

        
         try {
            ScreenUtils.changeScreen(event,"/com/canes/atualizar.fxml", "Atualização", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @FXML
    void onClickExcluir(ActionEvent event) {

        try {
            ScreenUtils.changeScreen(event,"/com/canes/excluir.fxml", "Exclusão", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickPedido(ActionEvent event) {

        try {
            ScreenUtils.changeScreen(event,"/com/canes/pedido.fxml", "Pedido", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    void onClickPesquisar(ActionEvent event) {

         try {
            ScreenUtils.changeScreen(event,"/com/canes/pesquisar.fxml", "Pesquisar", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onClickRelatorio(ActionEvent event) {

    }

    @FXML
    void onClickSair(ActionEvent event) {

         try {
            ScreenUtils.changeScreen(event,"/com/canes/login.fxml", "Login", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickCadastrar(ActionEvent event) {

        
         try {
            ScreenUtils.changeScreen(event,"/com/canes/cadastro.fxml", "Cadastro de Usuário", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onMouseEteredSair(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnSair);
        lblSair.setMouseTransparent(true);

        

    }

    @FXML
    void onMouseExitedSair(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnSair);

    }
   
        

    

    @FXML
    void onMouseExitedCadastrar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnCadastrar);

    }

    @FXML
    void onMouseEnteredCadastrar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnCadastrar);
        lblCadastrar.setMouseTransparent(true);
        

    }

    @FXML
    void onMouseExitedAtualizar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnAtualizar);

    }

    @FXML
    void onMouseEnteredAtualizar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnAtualizar);
        lblAtualizar.setMouseTransparent(true);
        

    }

     @FXML
    void onMouseExitedExcluir(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnExcluir);

    }

    @FXML
    void onMouseEnteredExcluir(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnExcluir);
        lblExcluir.setMouseTransparent(true);
        

    }

    @FXML
    void onMouseExitedPedido(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnPedido);

    }

    @FXML
    void onMouseEnteredPedido(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnPedido);
        lblPedido.setMouseTransparent(true);
        

    }

     @FXML
    void onMouseExitedPesquisar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnPesquisa);

    }

    @FXML
    void onMouseEnteredPesquisar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnPesquisa);
        lblPesquisa.setMouseTransparent(true);
        

    }
  

}



