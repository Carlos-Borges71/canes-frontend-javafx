package com.canes.controller;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Fornecedor;
import com.canes.model.Pedido;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.Usuario;
import com.canes.model.dpo.ClienteTelefoneDpo;
import com.canes.model.dpo.FornecedorTelefoneDpo;
import com.canes.model.dpo.UsuarioTelefoneDpo;
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

public class PesquisaController {

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colLogin;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colNome;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colData;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colSetor;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colTelefone;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colEndereco;

    @FXML
    private TableColumn<UsuarioTelefoneDpo, String> colClienteUser;

    @FXML
    private TableColumn<ClienteTelefoneDpo, String> colTelefoneCliente;

    @FXML
    private TableColumn<ClienteTelefoneDpo, String> colNomeCliente;

    @FXML
    private TableColumn<ClienteTelefoneDpo, String> colDataCliente;

    @FXML
    private TableColumn<ClienteTelefoneDpo, String> colEnderecoCliente;

    @FXML
    private TableColumn<ClienteTelefoneDpo, String> colPedidoCliente;

    @FXML
    private TableColumn<FornecedorTelefoneDpo, String> colFornec;

    @FXML
    private TableColumn<FornecedorTelefoneDpo, String> colTelefoneFornec;

    @FXML
    private TableColumn<FornecedorTelefoneDpo, String> colCnpj;

    @FXML
    private TableColumn<Produto, String> colNomeProduto;

    @FXML
    private TableColumn<Produto, String> colCodigoProduto;

    @FXML
    private TableColumn<Produto, Double> colValorProduto;

    @FXML
    private TableColumn<Produto, Integer> colEstoqueProduto;

    @FXML
    private TableView<UsuarioTelefoneDpo> tabelaUsuario;

    @FXML
    private TableView<ClienteTelefoneDpo> tabelaCliente;

    @FXML
    private TableView<FornecedorTelefoneDpo> tabelaFornec;

    @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private Button btnFiltrar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField txtFiltrar;

    private FilteredList<UsuarioTelefoneDpo> listaFiltrada;

    private FilteredList<ClienteTelefoneDpo> listaFiltradaCliente;

    private FilteredList<FornecedorTelefoneDpo> listaFiltradaFornecedor;

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

    private Cliente cliente;
    private Telefone telefone;

    private ObservableList<Usuario> listaUsuarios;

    private ObservableList<Cliente> listaClientes;

    private ObservableList<Fornecedor> listaFornecedores;

    private ObservableList<Produto> listaProdutos;

    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

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
    void onActionVoltar(MouseEvent event) {

        try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/view/menu.fxml", "Menu", null);
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

    @FXML
    public void initialize() {

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
        // colData.setCellValueFactory(cellData -> {
        //     Instant data = cellData.getValue().getInstante();
        //     String formatada = data == null ? "" : formatter.format(data);
        //     return new SimpleStringProperty(formatada);
        // });
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("enderecos"));
        colClienteUser.setCellValueFactory(new PropertyValueFactory<>("clientes"));

        Usuario u1 = new Usuario(1, "Carlos Borges", "ADM", "Carlos", Instant.parse("2025-09-10T12:00:09Z"), "123",
                Arrays.asList(new Telefone(1, "(99) 88888-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Sebastião Soares ", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u2 = new Usuario(2, "Cintia Yellon", "ADMINISTRATIVO", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 99988-0000"), new Telefone(2, "(21) 99888-8776")),
                Arrays.asList(new Endereco("Rua Alan", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Antonio Pires ", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u3 = new Usuario(3, "Joâo Pedro", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(3, "(65)4343-4343")),
                Arrays.asList(new Endereco("Rua Orca", "234", "Centro", "Rio de Janeiro", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Danilo Silva", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u4 = new Usuario(4, "Terla Gomes", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 823888-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Willian Lima", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u5 = new Usuario(5, "Joana Black", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 88888-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("José Soares ", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u6 = new Usuario(6, "Flavio Blue", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 877788-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Cristian Morais", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u7 = new Usuario(7, "Yasmin Red", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 88888-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Washington Torres Soares ", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u8 = new Usuario(8, "Washington Pink", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 76588-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Flavio Tulio", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        Usuario u9 = new Usuario(9, "Karla Khaki", "ADM", "GRU", Instant.now(), "123",
                Arrays.asList(new Telefone(1, "(99) 09038-0000")),
                Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Cliente("Luis Carlos", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido())),new Cliente("Lucca ferraz Carlos", Instant.now(), Arrays.asList(new Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));

        ObservableList<UsuarioTelefoneDpo> listaUsuarios = FXCollections.observableArrayList();

        for (Usuario u : Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9)) {
            for (Telefone t : u.getTelefone()) {
                for (Endereco e : u.getEnderecos()) {
                    for (Cliente c: u.getClientes()){ 

                    String enderecoCompleto = String.format("%s, %s - %s, %s/%s (%s)",
                            e.getLogradouro(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado(), e.getCep());

                    String ClienteCompleto = String.format(" Cad -  %s ", c.getNome());        

                    // listaUsuarios.add(new UsuarioTelefoneDpo(u.getNome(), u.getLogin(), u.getSetor(), u.getInstante(),u.getSenha(),
                    //         t.getNumero(), enderecoCompleto, ClienteCompleto));
                    // }

                }

            }
        }

        listaFiltrada = new FilteredList<>(listaUsuarios, p -> true);
        tabelaUsuario.setItems(listaFiltrada);

        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        txtFiltrar.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltrada.setPredicate(usuario -> {
                if (filtro == null || filtro.isEmpty()) {
                    return true;
                }

                // String dataString = usuario.getInstante() == null ? ""
                //         : formatter.format(usuario.getInstante()).toLowerCase();

                return usuario.getNome().toLowerCase().contains(filtro) ||
                        usuario.getSetor().toLowerCase().contains(filtro) ||
                        usuario.getLogin().toLowerCase().contains(filtro) ||
                        //usuario.getTelefone().toLowerCase().contains(filtro) ||
                        //dataString.contains(filtro);
                        //usuario.getTelefone().contains(filtro) ||
                        //usuario.getEnderecos().toLowerCase().contains(filtro) ||
                        //usuario.getClientes().toLowerCase().contains(filtro);
            });
        });

        Label placeholderCliente = new Label("Nenhum Cliente encontrado!");
        placeholderCliente.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");

        tabelaCliente.setPlaceholder(placeholderCliente);
        tabelaCliente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefones"));

        colDataCliente.setCellValueFactory(cellData -> {
            Instant data = cellData.getValue().getInstante();
            if (data == null)
                return new SimpleStringProperty("");
            return new SimpleStringProperty(formatter.format(data));
        });

        colEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("enderecos"));
        colPedidoCliente.setCellValueFactory(new PropertyValueFactory<>("pedidos"));

        // colDataCliente.setCellValueFactory(new PropertyValueFactory<>("instante"));

        Cliente c1 = new Cliente("Carlos borges", Instant.now(),
                Arrays.asList(new Telefone(1, "(21) 98989-0000"), new Telefone(2, "(21) 98989-9999")),
                Arrays.asList(new Endereco("Rua Abacaxi", "23234", "Centro", "Rio", "RJ", "26.000-000")),
                Arrays.asList(new Pedido(1, "PAGO", 150.9, Instant.now(), Arrays.asList(new Produto()))));
        Cliente c2 = new Cliente("Carlos ", Instant.now(),
                Arrays.asList(new Telefone(3, "(21) 98359-0550"), new Telefone(4, "(21) 98989-9999")),
                Arrays.asList(new Endereco("Rua Abc", "2w34", "Cuica", "São Paulo", "SP", "02.000-000")),
                Arrays.asList(new Pedido(2, "CANCELADO", 150.9, Instant.now(), Arrays.asList(new Produto()))));
        Cliente c3 = new Cliente(" borges", Instant.now(),
                Arrays.asList(new Telefone(5, "(21) 98989-0000"), new Telefone(6, "(21) 98989-9999")),
                Arrays.asList(new Endereco("Rua Abc", "2q34", "Centro", "Rio", "RJ", "26.000-000")), Arrays.asList(
                        new Pedido(3, "AGARDANDO PAGAMENTO", 150.9, Instant.now(), Arrays.asList(new Produto()))));

        ObservableList<ClienteTelefoneDpo> listaClientes = FXCollections.observableArrayList();

        for (Cliente c : Arrays.asList(c1, c2, c3)) {
            for (Telefone t : c.getTelefones()) {
                for (Endereco e : c.getEnderecos()) {
                    for (Pedido p : c.getPedidos()) {
                        String enderecoCompleto = String.format("%s, %s - %s, %s/%s (%s)",
                                e.getLogradouro(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado(),
                                e.getCep());

                        String pedidosCompleto = String.format("%s - (%s) -  %s", p.getId(), p.getStatus(),
                                nf.format(p.getValor()));

                        listaClientes
                                .add(new ClienteTelefoneDpo(c.getNome(), c.getInstante(), t.getNumero(),
                                        enderecoCompleto, pedidosCompleto));

                    }
                }

            }
        }

        listaFiltradaCliente = new FilteredList<>(listaClientes, p -> true);

        tabelaCliente.setItems(listaFiltradaCliente);

        txtFiltrarCliente.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltradaCliente.setPredicate(cliente -> {
                if (filtro == null || filtro.isEmpty()) {
                    return true;
                }

                String dataString = cliente.getInstante() == null ? ""
                        : formatter.format(cliente.getInstante()).toLowerCase();

                return cliente.getNome().toLowerCase().contains(filtro) ||

                        dataString.contains(filtro) || cliente.getEnderecos().toLowerCase().contains(filtro)
                        || cliente.getTelefones().contains(filtro) ||
                        cliente.getPedidos().toLowerCase().contains(filtro);

            });
        });

        Label placeholderFornec = new Label("Nenhum Fornecedor encontrado!");
        placeholderFornec.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaFornec.setPlaceholder(placeholderFornec);

        tabelaFornec.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colFornec.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colTelefoneFornec.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        Fornecedor f1 = new Fornecedor("Cia Roupas", "11.111.222/0001-09",
                Arrays.asList(new Telefone(1, "(88) 88888-0000")));
        Fornecedor f2 = new Fornecedor("Roupas Cia", "22.333.444/0001-07",
                Arrays.asList(new Telefone(2, "(21) 22222-4444"), new Telefone(3, "(21) 99803-8215")));
        Fornecedor f3 = new Fornecedor("Poco Roupas", "66.111.222/0001-09",
                Arrays.asList(new Telefone(1, "(88) 80878-0000")));
        Fornecedor f4 = new Fornecedor("Rio Cia", "22.003.444/0001-07",
                Arrays.asList(new Telefone(2, "(21) 22222-8764"), new Telefone(3, "(21) 99803-8215")));

        ObservableList<FornecedorTelefoneDpo> listaFornecedor = FXCollections.observableArrayList();

        for (Fornecedor f : Arrays.asList(f1, f2, f3, f4)) {
            for (Telefone t : f.getTelefone()) {
                listaFornecedor.add(new FornecedorTelefoneDpo(f.getEmpresa(), f.getCnpj(), t.getNumero()));
            }
        }

        listaFiltradaFornecedor = new FilteredList<>(listaFornecedor, p -> true);

        tabelaFornec.setItems(listaFiltradaFornecedor);

        txtFiltrarFornec.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue.toLowerCase();
            listaFiltradaFornecedor.setPredicate(fornecedor -> {
                if (filtro == null || filtro.isEmpty()) {
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
                if (filtro == null || filtro.isEmpty()) {
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

            if (e.getClickCount() == 2) {
                Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();

                if (produtoSelecionado != null) {
                    System.out.println("Selecionado: Código " + produtoSelecionado.getId() + " Produto: "
                            + produtoSelecionado.getNome());
                }

            }
        });

    }

}
