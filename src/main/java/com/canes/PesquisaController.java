package com.canes;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.canes.util.HouverEffectUtil;
import com.canes.util.ScreenUtils;

import javafx.beans.property.SimpleStringProperty;
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
    private TableColumn<Usuario, String> colData;


    @FXML
    private TableColumn<Usuario, String> colSetor;

    @FXML
    private TableColumn<Usuario, String> colTelefone;

    @FXML
    private TableColumn<Cliente, String> colTelefoneCliente;

    @FXML
    private TableColumn<Cliente, String> colNomeCliente;

    @FXML
    private TableColumn<Cliente, String> colDataCliente;

    @FXML
    private TableColumn<Fornecedor, String> colFornec;

    @FXML
    private TableColumn<Fornecedor, String> colTelefoneFornec;

    @FXML
    private TableColumn<Fornecedor, String> colCnpj;

    @FXML
    private TableColumn<Produto, String> colNomeProduto;

    @FXML
    private TableColumn<Produto, Long> colCodigoProduto;

    @FXML
    private TableColumn<Produto, Double> colValorProduto;

     @FXML
    private TableColumn<Produto, Integer> colEstoqueProduto;

    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableView<Cliente> tabelaCliente;

    @FXML
    private TableView<Fornecedor> tabelaFornec;

    @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private Button btnFiltrar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField txtFiltrar;

    private FilteredList<Usuario> listaFiltrada;

    private FilteredList<Cliente> listaFiltradaCliente;

    private FilteredList<Fornecedor> listaFiltradaFornecedor;

    private FilteredList<Produto> listaFiltradaProduto;


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

    private DateTimeFormatter formatter;

    private Fornecedor fornecedor;


    

    
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

    private ObservableList<Cliente> listaClientes;

    private ObservableList<Fornecedor> listaFornecedores;

    private ObservableList<Produto> listaProdutos;

    @FXML
    public void initialize(){

        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

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
        colData.setCellValueFactory(cellData -> {
            Instant data = cellData.getValue().getInstante();
            String formatada = data == null ? "" : formatter.format(data);
            return new SimpleStringProperty(formatada);
        });
        

        listaUsuarios = FXCollections.observableArrayList( 
        new Usuario(1,"Carlos Borges", "ADM", "Carlos", Instant.parse("2025-09-10T12:00:09Z"),"(99) 88888-0000"), 
        new Usuario(2, "Cintia Yellon", "ADMINISTRATIVO", "GRU",Instant.now(), "(99)43434343"),
        new Usuario(3,"Joâo Pedro", "ADM", "GRU", Instant.now(),"(65)43434343"),
        new Usuario(4,"Terla Gomes", "ADM", "GRU", Instant.now(),"43434343"),
        new Usuario(5, "Joana Black", "ADM", "GRU",Instant.now(), "43434343"),
        new Usuario(6, "Flavio Blue", "ADM", "GRU", Instant.now(),"43434343"),
        new Usuario(7, "Yasmin Red", "ADM", "GRU", Instant.now(),"43434343"),
        new Usuario(8, "Washington Pink", "ADM", "GRU",Instant.now(), "43434343"),
        new Usuario(9,"Karla Khaki", "ADM", "GRU", Instant.now(),"43434343"));

    
    

        listaFiltrada = new FilteredList<>(listaUsuarios, p -> true);

        tabelaUsuario.setItems(listaFiltrada);

        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        txtFiltrar.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltrada.setPredicate(usuario -> {
             if(filtro == null || filtro.isEmpty()) {
                return true;
            }

            String dataString = usuario.getInstante() == null ? "" : formatter.format(usuario.getInstante()).toLowerCase();
            
            return usuario.getNome().toLowerCase().contains(filtro) || 
            usuario.getSetor().toLowerCase().contains(filtro) ||
            usuario.getLogin().toLowerCase().contains(filtro) ||
            usuario.getTelefone().toLowerCase().contains(filtro) ||
            dataString.contains(filtro);
            });
        });


        Label placeholderCliente = new Label("Nenhum Cliente encontrado!");
        placeholderCliente.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");

        tabelaCliente.setPlaceholder(placeholderCliente);
        tabelaCliente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("Telefone")); 
        
        colDataCliente.setCellValueFactory(cellData -> {
            Instant instante = cellData.getValue().getInstante();
            String formatada = instante == null ? "" : formatter.format(instante);
            return new SimpleStringProperty(formatada);
        });
        // colDataCliente.setCellValueFactory(new PropertyValueFactory<>("instante"));
      
        

        listaClientes = FXCollections.observableArrayList( 
        new Cliente(1, "João Dias", Instant.parse("2025-05-10T10:00:00Z"), "(21) 99999-0000"),
        new Cliente(2, "João Dias", Instant.parse("2025-05-10T10:00:00Z"), "(21) 99999-0000"),
        new Cliente(3, "João Dias", Instant.now(), "(21) 99999-0000"),
        new Cliente(4, "João Dias", Instant.now(), "(21) 99999-0000")
        );

    
    

        listaFiltradaCliente = new FilteredList<>(listaClientes, p -> true);

        tabelaCliente.setItems(listaFiltradaCliente);
        
        txtFiltrarCliente.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltradaCliente.setPredicate(cliente -> {
             if(filtro == null || filtro.isEmpty()) {
                return true;
            }

            String dataString = cliente.getInstante() == null ? "" : formatter.format(cliente.getInstante()).toLowerCase();
            
            return cliente.getNome().toLowerCase().contains(filtro) ||             
            cliente.getTelefone().toLowerCase().contains(filtro)||
            dataString.contains(filtro);
            
            });
        });


        Label placeholderFornec = new Label("Nenhum Fornecedor encontrado!");
        placeholderFornec.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaFornec.setPlaceholder(placeholderFornec);

        
        tabelaFornec.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colFornec.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colTelefoneFornec.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        
      
        

        listaFornecedores = FXCollections.observableArrayList( 
        new Fornecedor(1, "Cia Roupas", "11.111.222/0001-09", "(88) 88888-0000"),
        new Fornecedor(2, "Roupas Cia", "22.333.444/0001-07", "(21) 22222-4444")
        
        );

        
    
    

        listaFiltradaFornecedor = new FilteredList<>(listaFornecedores, p -> true);

        tabelaFornec.setItems(listaFiltradaFornecedor);
        
        txtFiltrarFornec.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltradaFornecedor.setPredicate(fornecedor -> {
             if(filtro == null || filtro.isEmpty()) {
                return true;
            }

            
            
            return fornecedor.getEmpresa().toLowerCase().contains(filtro) ||             
            fornecedor.getTelefone().toLowerCase().contains(filtro) ||
            fornecedor.getCnpj().toLowerCase().contains(filtro);
            
            
            });
        });


    
        Label placeholderProduto = new Label("Nenhum Produto encontrado!");
        placeholderProduto.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaProduto.setPlaceholder(placeholderProduto);

        tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEstoqueProduto.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        colValorProduto.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        
      
        

        listaProdutos = FXCollections.observableArrayList( 
           new Produto(1,876577777L, "Vestido", 10, 123.9, 660.8, 10),
            new Produto(2,8333564598L, "Calça Jeans", 6, 123.9, 760.8, 6),
             new Produto(3,2457665598L, "Vestido Rusti", 4, 123.9, 160.8, 4)
        
        );

        
    
    

        listaFiltradaProduto = new FilteredList<>(listaProdutos, p -> true);

        tabelaProduto.setItems(listaFiltradaProduto);
        
        txtFiltrarProduto.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltradaProduto.setPredicate(produto -> {
             if(filtro == null || filtro.isEmpty()) {
                return true;
            }

            String codigoStr = String.valueOf(produto.getCodigo());
            String estoqueStr = String.valueOf(produto.getEstoque());
            String valorStr = String.valueOf(produto.getValorVenda());
            
            
            return produto.getNome().toLowerCase().contains(filtro) ||             
            codigoStr.toLowerCase().contains(filtro) ||
            estoqueStr.toLowerCase().contains(filtro) ||
            valorStr.toLowerCase().contains(filtro);
            
            
            });
        });

        tabelaProduto.setOnMouseClicked(e -> {

            if (e.getClickCount() == 2){ 
            Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();

                if (produtoSelecionado != null) {
                    System.out.println("Selecionado: Código " + produtoSelecionado.getId()+ " Produto: "+ produtoSelecionado.getNome());
                }

            }
        });

    
    }

    
}

    



    

