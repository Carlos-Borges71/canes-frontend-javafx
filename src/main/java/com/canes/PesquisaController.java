package com.canes;

import com.canes.util.HouverEffectUtil;
import com.canes.util.ScreenUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PesquisaController{

    @FXML
    private TableColumn<Usuario, String> colLogin;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TableColumn<Usuario, String> colSetor;

    @FXML
    private TableColumn<Usuario, String> colTelefone;

    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableView<Cliente> tabelaCliente;

    @FXML
    private TableView<?> tabelaFornec;

    @FXML
    private TableView<?> tabelaProduto;

    @FXML
    private Button btnFiltrar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField txtFiltrar;

    private FilteredList<Usuario> listaFiltrada;

    @FXML
    private Pane paneCliente;

    @FXML
    private Pane paneFornec;

    @FXML
    private Pane paneProduto;

    @FXML
    private Pane paneUser;

    @FXML
    private Label lbluser;

    @FXML
    private Label lblClient;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnFornec;

    @FXML
    private Button btnProduto;

     @FXML
    private Label lblFornec;

     @FXML
    private Label lblProduto;

    @FXML
    private Button btnLimparCliente;

    @FXML
    private Button btnLimparFornec;

    @FXML
    private Button btnLimparProduto;

    @FXML
    private Button btnLimparUsuario;

   
    @FXML
    private TextField txtFiltrarCliente;

    @FXML
    private TextField txtFiltrarFornec;

    @FXML
    private TextField txtFiltrarProduto;


    
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
    void  onActionVoltar(MouseEvent event) {

         try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    void onActionProduto(ActionEvent event) {

        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);        
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(true);
        lblProduto.setTextFill(Color.RED);
    }

    @FXML
    void limparCliente(ActionEvent event) {
        txtFiltrarCliente.clear();

    }

    @FXML
    void limparUsuario(ActionEvent event) {
        txtFiltrar.clear();
    }

    @FXML
    void limparFornec(ActionEvent event) {
        txtFiltrarFornec.clear();
    }

    @FXML
    void limparProduto(ActionEvent event) {
        txtFiltrarProduto.clear();
    }

    @FXML
    void onMouseEteredFiltrar(MouseEvent event) {

    HouverEffectUtil.apllyHouverSobre(btnFiltrar);
    

    }

    @FXML
    void onMouseExitedFiltrar(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnFiltrar);
    }

    private ObservableList<Usuario> listaUsuarios;

    @FXML
    public void initialize(){

        lbluser.setMouseTransparent(true);
        lblClient.setMouseTransparent(true);
        lblFornec.setMouseTransparent(true);
        lblProduto.setMouseTransparent(true);

        btnUser.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnUser);
        });

        btnUser.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnUser);
            
        });

        btnClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnClient);
        });

        btnClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnClient);
        });

        btnFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnFornec);
        });

        btnFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFornec);
        });

        btnProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnProduto);
        });

        btnProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnProduto);
        });

        btnLimparUsuario.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparUsuario);
        });

        btnLimparUsuario.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparUsuario);
        });

        btnLimparCliente.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparCliente);
        });

        btnLimparCliente.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparCliente);
        });

        btnLimparFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparFornec);
        });

        btnLimparFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparFornec);
        });

        

        btnLimparProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparProduto);
        });

        btnLimparProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparProduto);
        });

        

        Label placeholder = new Label("Nenhum usuário encontrado!");
        placeholder.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");

        tabelaUsuario.setPlaceholder(placeholder);
        

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        

        listaUsuarios = FXCollections.observableArrayList( 
        new Usuario("Carlos Borges", "ADM", "Carlos", "(99) 88888-0000"), 
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"),
        new Usuario("Karla", "ADM", "GRU", "43434343"));

    
    

        listaFiltrada = new FilteredList<>(listaUsuarios, p -> true);

        tabelaUsuario.setItems(listaFiltrada);

        txtFiltrar.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltrada.setPredicate(usuario -> {
             if(filtro == null || filtro.isEmpty()) {
                return true;
            }
            return usuario.getNome().toLowerCase().contains(filtro) || 
            usuario.getSetor().toLowerCase().contains(filtro) ||
            usuario.getLogin().toLowerCase().contains(filtro) ||
            usuario.getTelefone().toLowerCase().contains(filtro);
            });
        });


        Label placeholderCliente = new Label("Nenhum Cliente encontrado!");
        placeholderCliente.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");

        tabelaCliente.setPlaceholder(placeholderCliente);


        Label placeholderFornec = new Label("Nenhum Fornecedor encontrado!");
        placeholderFornec.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaFornec.setPlaceholder(placeholderFornec);

    
        Label placeholderProduto = new Label("Nenhum Produto encontrado!");
        placeholderProduto.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaProduto.setPlaceholder(placeholderProduto);

    
    }

    
}

    



    

