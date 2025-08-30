package com.canes;


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
    private Button btnCliente;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnFornecedor;

    @FXML
    private Button btnPedido;

    @FXML
    private Button btnPesquisa;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnUsuario;

    @FXML
    private Label lblSair;

    @FXML
    private Label lblUser;

    @FXML
    void onClickAtualizar(ActionEvent event) {

    }

    @FXML
    void onClickCliente(ActionEvent event) {

    }

    @FXML
    void onClickExcluir(ActionEvent event) {

    }

    @FXML
    void onClickFornecedor(ActionEvent event) {

    }

    @FXML
    void onClickPedido(ActionEvent event) {

    }

    @FXML
    void onClickPesquisar(ActionEvent event) {

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
    void onClickUsuario(ActionEvent event) {

        
         try {
            ScreenUtils.changeScreen(event,"/com/canes/usuario.fxml", "Cadastro de Usuário", null);
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
    void onMouseEteredUser(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnUsuario);
        lblUser.setMouseTransparent(true);
        

    }

    @FXML
    void onMouseExitedUser(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnUsuario);

    }

}



