package com.canes.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.canes.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProdutoController {

    @FXML
    private Button btnLimparProduto;

    @FXML
    private TableColumn<Produto, String> colCodigoProduto;

    @FXML
    private TableColumn<Produto, String> colEstoqueProduto;

    @FXML
    private TableColumn<Produto, String> colNomeProduto;

    @FXML
    private TableColumn<Produto, Double> colValorProduto;

    @FXML
    private Pane paneProduto;

    @FXML
    private TableView<Produto> tabelaProduto;

    private FilteredList<Produto> listaFiltradaProduto;

    private ObservableList<Produto> listaProdutos;

    @FXML
    private TextField txtFiltrarProduto;

    private PedidoController pedidoController;

    private DecimalFormat df;

    

    public void initialize(){


        DecimalFormatSymbols simbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        df = new DecimalFormat("#,##0.00", simbols);

         tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label placeholder = new Label("Aguardando Pedido!");
        placeholder.setStyle("-fx-text-fill: #152266 ; -fx-font-weight: bold; -fx-font-size: 40px");

        tabelaProduto.setPlaceholder(placeholder);Label placeholderProduto = new Label("Nenhum Produto encontrado!");
        placeholderProduto.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaProduto.setPlaceholder(placeholderProduto);

        tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEstoqueProduto.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        colValorProduto.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        realColuna(colValorProduto);
        
      
        

        listaProdutos = FXCollections.observableArrayList( 
        
            new Produto("876577777", "Vestido", 10, 123.9, 660.8, 10),
           new Produto("876577777", "Vestido", 10, 123.9, 660.8, 10),
            new Produto("8333564598", "Calça Jeans", 6, 123.9, 760.8, 6),
             new Produto("2457665598", "Vestido Rusti", 4, 123.9, 160.8, 4)
        
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



        //  // Zebrando a tabela
        // tabelaProduto.setRowFactory(tv -> new TableRow<>() {
        //     @Override
        //     protected void updateItem(Produto item, boolean empty) {
        //         super.updateItem(item, empty);
        //         if (item == null || empty) {
        //             setStyle("");
        //         } else {

        //             if (getIndex() % 2 == 0) {
        //                 setStyle("-fx-background-color: #d2cbcbff;"); // cinza claro
        //             } else {
        //                 setStyle("-fx-background-color: #f2f2f2;");
        //             }
        //         }
        //     }
        // });

        

        tabelaProduto.sceneProperty().addListener((obs, oldScene, newScene) -> {

            if (newScene != null) {
                newScene.addEventFilter(MouseEvent.MOUSE_CLICKED, evet -> {
                    if (evet.getClickCount() == 2) {

                       

                        Produto selecionado = tabelaProduto.getSelectionModel().getSelectedItem();
                        

            if(selecionado != null && pedidoController != null ){
                pedidoController.receberProduto(selecionado);
                

                Stage stage =(Stage) tabelaProduto.getScene().getWindow();
                stage.close();

                
            }
                    }
                });
            }
        });
    }

    public void setPedidoController(PedidoController pedidoController){
        this.pedidoController = pedidoController;
    }




    @FXML
    void limparProduto(ActionEvent event) {
        txtFiltrarProduto.clear();

    }

    @FXML
    void onMouseEteredFiltrar(MouseEvent event) {

    }

    @FXML
    void onMouseExitedFiltrar(MouseEvent event) {

    }

    public void realColuna(TableColumn<Produto, Double> coluna) {

        coluna.setCellFactory(col -> new TableCell<Produto, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(df.format(valor));
                }
            }
        });

    }

}
