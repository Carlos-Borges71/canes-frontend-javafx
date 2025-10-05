package com.canes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.tblExibirPedido;
import com.canes.util.MaskTextField;
import com.canes.util.ScreenUtils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PedidoController {

    private Produto produto;

    @FXML
    private TableColumn<tblExibirPedido, Integer> colItem;

    @FXML
    private TableColumn<tblExibirPedido, String> colProduto;

    @FXML
    private TableColumn<tblExibirPedido, Integer> colQuant;

    @FXML
    private TableColumn<tblExibirPedido, Double> colTotal;

    @FXML
    private TableColumn<tblExibirPedido, Double> colValorUnitario;

    @FXML
    private Label lblDesconto;

    @FXML
    private Label lblQuant;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<tblExibirPedido> tabelaPedido;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDesconto;

    @FXML
    private TextField txtPagamento;

    @FXML
    private TextField txtQuant;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtTotalRecebido;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtTroco;

    @FXML
    private TextField txtValorUnitario;

    private ObservableList<tblExibirPedido> listaPedidos;

    private DecimalFormat df;

   // private CadastroController cadastroController;

    NumberFormat nf =  NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    DecimalFormatSymbols simbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
    

    public void initialize(){

        
        MaskTextField.valor(txtDesconto);    
        MaskTextField.valor(txtSubTotal);
        MaskTextField.valor(txtTotalRecebido);
        MaskTextField.valor(txtTroco);
        MaskTextField.valor(txtValorUnitario);
        MaskTextField.number(txtQuant);
        MaskTextField.applyPhoneMask(txtTelefone);

        tabelaPedido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label placeholder = new Label("Aguardando Pedido!");
        placeholder.setStyle("-fx-text-fill: #152266 ; -fx-font-weight: bold; -fx-font-size: 40px");

        tabelaPedido.setPlaceholder(placeholder);

       simbols.setDecimalSeparator(',');
       simbols.setGroupingSeparator('.');
       df = new DecimalFormat("#,##0.00", simbols);


        lblTotal.textProperty().addListener((obs, oldVal, newVal) -> {
            if(lblTotal != null){ 
            String totalValor = lblTotal.getText();
            txtSubTotal.setText(totalValor);
            }
        });

        Platform.runLater(() -> {
            Scene scene = txtCodigo.getScene();

            scene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ESCAPE){

                    try {
                        ScreenUtils.changeScreenElement(txtCodigo, "/com/canes/menu.fxml", "MENU",  null);
                        } catch (Exception event) {
                            event.printStackTrace();
                        }
                }
            });
        });

        tabelaPedido.sceneProperty().addListener((obs, oldScene, newScene) -> {

            if(newScene != null){
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, evet -> {
                    if(evet.getCode() == KeyCode.DELETE){

                    tblExibirPedido selecionado = tabelaPedido.getSelectionModel().getSelectedItem();
                
                if(selecionado != null){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmação");
                    alert.setHeaderText("Excluir Produto");
                    alert.setContentText("Tem Certeza que deseja excluir produto do item " + selecionado.getItem() +  "?\nAo confirma este produto será excluído permanentemente.");

                    alert.showAndWait().ifPresent(resposta -> {
                        if(resposta == ButtonType.OK){
                            tabelaPedido.getItems().remove(selecionado);
                            totalValor();
                            totalQuant();
                        
                        }
                    });
                }

                    }
                });
                
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN), () -> 
                txtDesconto.requestFocus());
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN), () -> 
                txtTelefone.requestFocus());
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN), () -> 
                txtQuant.requestFocus());
            }
        });

        txtCodigo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), () -> 
                txtTotalRecebido.requestFocus());
            }
        });

        txtDesconto.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN), () -> 
                txtCodigo.requestFocus());
            }
        });

        txtDesconto.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN), () -> 
                
                {
                    try{ 
                        ScreenUtils.changeScreenController(txtPagamento, "/com/canes/formaPagamento.fxml", "Selecionar forma de Pagamento", controller -> {
                            if(controller instanceof FormaPagamentoController) {

                                FormaPagamentoController formaPagamentoController = (FormaPagamentoController) controller;                            

                                String formaEscolhida = ((FormaPagamentoController)controller).getFormaSelecionada();
                                
                                // Recupera a escolha feita
                                
                                
                                if (formaEscolhida != null) {
                                txtPagamento.setText(formaEscolhida);
                                        

                                }
                            }
                        });
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                
                
                );
            }
        });

        txtTotalRecebido.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), () -> 
                
                {
                    try{ 
                        ScreenUtils.changeScreenController(txtStatus, "/com/canes/statusPagamento.fxml", "Selecionar Status de Pagamento", controller -> {
                            if(controller instanceof StatusPagamentoController) {

                                StatusPagamentoController statusPagamentoController = (StatusPagamentoController) controller;                            

                                String formaEscolhida = ((StatusPagamentoController)controller).getFormaSelecionada();
                                
                                System.out.println(formaEscolhida);
                                if (formaEscolhida != null) {
                                    txtStatus.setText(formaEscolhida);
                                }


                            }
                    
                        });

                        

                    }catch(IOException e) {
                        // 4
                        e.printStackTrace();
                    }


                });
                
                
                
                
            }
        });

        txtTotalRecebido.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.getAccelerators().put(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN), () -> 
                
                {
                    try{ 
                        ScreenUtils.changeScreenController(txtCliente, "/com/canes/cadastroCliente.fxml", "Cadastro Cliente", controller -> {
                            if(controller instanceof CadastroClienteController) {

                                 //CadastroClienteController cadastroClienteController = (CadastroClienteController) controller;                                
                                                               
                                Cliente cliente = ((CadastroClienteController)controller).getClienteSalvo();
                                Telefone telefone = ((CadastroClienteController)controller).getTelefoneSalvo();
                                Endereco enderco = ((CadastroClienteController)controller).getEnderecoSalvo();
                        
                                if(cliente != null){
                                    txtCliente.setText(cliente.getNome());                                    
                                }if (telefone != null)  {
                                    txtTelefone.setText(telefone.getNumero());
                                }if(enderco != null) {

                                }                      
                                                      


                            }
                           
                                
                            
                    
                        });

                        

                    }catch(IOException e) {
                        // 4
                        e.printStackTrace();
                    }


                });
                
                
                
                
            }
        });
    
        
   


    }
    



   




    private void statusPagamento() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("statusPagamento.fxml"));
        Parent root = loader.load();
        StatusPagamentoController controller = loader.getController();

        // Cria uma nova janela (modal)
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
        stage.setTitle("Selecionar Status de Pagamento");
        

        // Mostra e espera fechar
        stage.showAndWait();

        // Recupera a escolha feita
        String formaEscolhida = controller.getFormaSelecionada();        
        if (formaEscolhida != null) {
            txtStatus.setText(formaEscolhida);
        }
        
    }




    @FXML
    private void formaPagamento() throws IOException {
    


    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormaPagamento.fxml"));
        Parent root = loader.load();

        // Pega o controller da tela de forma de pagamento
        FormaPagamentoController controller = loader.getController();

        // Cria uma nova janela (modal)
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // bloqueia a tela principal até fechar
        stage.setTitle("Selecionar Forma de Pagamento");
        

        // Mostra e espera fechar
        stage.showAndWait();

        // Recupera a escolha feita
        String formaEscolhida = controller.getFormaSelecionada();
        if (formaEscolhida != null) {
            txtPagamento.setText(formaEscolhida);
        }
    



    }





    Integer item = 1;


     @FXML
    void onEnterActionDesconto(ActionEvent event) {
         if(lblDesconto != null){         

            BigDecimal desconto = MaskTextField.getValue(txtDesconto);
            BigDecimal subTotal = getValueLbl(lblTotal);
            BigDecimal result = subTotal.subtract(desconto);
            
            if(subTotal.compareTo(desconto) <= 0){
            lblTotal.setText("0");
            return;
            }else{ 
            String resultFor = df.format(result);
            lblTotal.setText(resultFor.toString());
    
            }
            
        }
        lblDesconto.setText(txtDesconto.getText());
    
    }
    
    
    @FXML
    void onEnterAction(ActionEvent event) {


        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        colProduto.setCellValueFactory( new PropertyValueFactory<>("produto"));


        colQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

        colValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        realColuna(colValorUnitario);

        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        realColuna(colTotal);
       
        String produt = txtCodigo.getText();
        int quant = Integer.parseInt(txtQuant.getText());        
        double unitario = Double.parseDouble(txtValorUnitario.getText().replace(",", ".").trim().replace("R$", ""));      
        double total = quant * unitario;

    
        

        tblExibirPedido p = new tblExibirPedido(item,produt,quant,unitario,total);
        tabelaPedido.getItems().add(p);

        tabelaPedido.scrollTo(p);

        item += 1;

        totalQuant();           
        totalValor();

        

        txtCodigo.clear();
    

    }
    

     @FXML
    void onEnterActionRecebido(ActionEvent event) {

        BigDecimal total = MaskTextField.getValue(txtTotalRecebido);
        BigDecimal subtotal = MaskTextField.getValue(txtSubTotal); 
        
        BigDecimal soma = total.subtract(subtotal);
        if(total.compareTo(subtotal) <= 0){
            txtTroco.setText("0");
        }else{ 
        txtTroco.setText(soma.toString());
        }
    }

    private void totalQuant(){
        int total = tabelaPedido.getItems().stream().mapToInt(tblExibirPedido :: getQuant).sum();
        String totalString = String.valueOf(total);
        lblQuant.setText(totalString);
    }
    private void totalValor(){
        double total = tabelaPedido.getItems().stream().mapToDouble(tblExibirPedido :: getTotal).sum();
        String totalString = String.valueOf(nf.format(total));
        lblTotal.setText(totalString.replace("R$",""));
    
    }



    private void  realColuna(TableColumn<tblExibirPedido,Double> coluna){

        coluna.setCellFactory(col -> new TableCell<tblExibirPedido, Double>() {
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

     public static BigDecimal getValueLbl(Label label) {
        
        String text = label.getText();
        if (text == null || text.isBlank()) return BigDecimal.ZERO;
        String digits = text.replaceAll("\\D", "");
        try {
            return new BigDecimal(digits).movePointLeft(2);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
        
    }
    

    
    

}


