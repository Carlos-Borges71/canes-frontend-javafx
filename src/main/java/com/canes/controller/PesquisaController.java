package com.canes.controller;



import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Fornecedor;
import com.canes.model.NotaFiscal;
import com.canes.model.Pedido;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.Usuario;
import com.canes.model.dpo.ClienteTabelaDPO;
import com.canes.model.dpo.FornecedorDTO;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.ClienteService;
import com.canes.services.EnderecoService;
import com.canes.services.FornecedorService;
import com.canes.services.NotaFiscalService;
import com.canes.services.PedidoService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.services.UsuarioService;
import com.canes.util.AlertUtil;
import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.MoedaCorrenteCellFactory;
import com.canes.util.ScreenUtils;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
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
    private TableColumn<Usuario, String> colCodigo;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TableColumn<Usuario, String> colData;

    @FXML
    private TableColumn<Usuario, String> colSetor;

    @FXML
    private TableColumn<Usuario, String> colTelefone;

    @FXML
    private TableColumn<Usuario, String> colEndereco;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colTelefoneCliente;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colNomeCliente;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colCodigoCliente;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colDataCliente;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colEnderecoCliente;

    @FXML
    private TableColumn<ClienteTabelaDPO, String> colPedidoCliente;

    @FXML
    private TableColumn<FornecedorDTO, String> colFornec;

    @FXML
    private TableColumn<FornecedorDTO, String> colTelefoneFornec;

    @FXML
    private TableColumn<FornecedorDTO, String> colCnpj;

    @FXML
    private TableColumn<FornecedorDTO, String> colCodigoFornec;

    @FXML
    private TableColumn<FornecedorDTO, String> colProdutoFornec;

    @FXML
    private TableColumn<FornecedorDTO, String> colNotaFiscalFornec;

    @FXML
    private TableColumn<FornecedorDTO, String> colEnderecoFornec;

    @FXML
    private TableColumn<Produto, String> colNomeProduto;

    @FXML
    private TableColumn<Produto, String> colCodigoProduto;

    @FXML
    private TableColumn<Produto, String> colFornecedorProduto;

    @FXML
    private TableColumn<Produto, Double> colValorProduto;

    @FXML
    private TableColumn<Produto, Integer> colEstoqueProduto;

    @FXML
    private TableColumn<Produto, Integer> colIdProduto;

    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableView<ClienteTabelaDPO> tabelaCliente;

    @FXML
    private TableView<FornecedorDTO> tabelaFornec;

    @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private TableColumn<Pedido, String> colClientePedido;

    @FXML
    private TableColumn<Pedido, String> colDataPedido;

    @FXML
    private TableColumn<Pedido, Long> colIdPedido;

    @FXML
    private TableColumn<Pedido, String> colStatusPedido;

    @FXML
    private TableColumn<Pedido, String> colProdutoPedido;

    @FXML
    private TableColumn<Pedido, Double> colValorPedido;

    @FXML
    private TableColumn<Pedido, String> colPagamentoPedido;

    @FXML
    private Label lblPedido;

    @FXML
    private Pane panePedido;

    @FXML
    private Button btnLimparPedido;

    @FXML
    private Button btnPedido;

    @FXML
    private TableView<Pedido> tabelaPedido;

    @FXML
    private TextField txtFiltrarPedido;

    @FXML
    private Button btnFiltrar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField txtFiltrar;

    private FilteredList<Usuario> listaFiltrada;

    private FilteredList<ClienteTabelaDPO> listaFiltradaCliente;

    private FilteredList<FornecedorDTO> listaFiltradaFornecedor;

    private FilteredList<Produto> listaFiltradaProduto;

    private FilteredList<Pedido> listaFiltradaPedido;

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

    private String fornecedor;

    private Cliente cliente;
    private Telefone telefone;

    private ObservableList<Usuario> listaUsuarios;

    private ObservableList<ClienteTabelaDPO> listaClientes;

    private ObservableList<FornecedorDTO> listaFornecedores;

    private ObservableList<Produto> listaProdutos;

    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    @FXML
    void onactionClient(ActionEvent event) {

        Platform.runLater(() -> {
            txtFiltrarCliente.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

        paneUser.setVisible(false);
        paneCliente.setVisible(true);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.RED);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        panePedido.setVisible(false);
        lblPedido.setTextFill(Color.WHITE);

        Label placeholderCliente = new Label("Nenhum Cliente encontrado!");
        placeholderCliente.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");

        tabelaCliente.setPlaceholder(placeholderCliente);
        tabelaCliente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colCodigoCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefones"));
        colDataCliente.setCellValueFactory(new PropertyValueFactory<>("instante"));

        // colDataCliente.setCellValueFactory(cellData -> {
        // Instant data = cellData.getValue().getInstante();
        // if (data == null)
        // return new SimpleStringProperty("");
        // return new SimpleStringProperty(formatter.format(data));
        // });

        colEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colPedidoCliente.setCellValueFactory(new PropertyValueFactory<>("pedidos"));

        try {
            ClienteService clienteService = new ClienteService();
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();
            PedidoService pedidoService = new PedidoService();

            List<Cliente> clientes = clienteService.buscarTodos();
            List<Telefone> telefones = telefoneService.buscarTodos();
            List<Endereco> end = enderecoService.buscarTodos();
            List<Pedido> pedidos = pedidoService.buscarTodos();

            ObservableList<ClienteTabelaDPO> listaClientes = FXCollections.observableArrayList();

            // evita NullPointer se alguma lista vier nula
            List<Cliente> clientesSeguros = clientes != null ? clientes : List.of();
            List<Telefone> telefonesSeguros = telefones != null ? telefones : List.of();
            List<Endereco> enderecosSeguros = end != null ? end : List.of();
            List<Pedido> pedidosSeguros = pedidos != null ? pedidos : List.of();

            for (Cliente c : clientesSeguros) {

                Long clientId = c != null ? c.getId() : null;

                // Buscar todos os telefones do usuário
                List<String> clients = telefonesSeguros.stream()
                        .filter(t -> t != null && t.getCliente() != null
                                && t.getCliente().getId().equals(clientId))
                        .map(Telefone::getNumero)
                        .filter(Objects::nonNull)
                        .toList();

                // Montar string com todos os telefones
                String numerosTelefone;

                if (clients.isEmpty()) {
                    numerosTelefone = "Cliente sem telefone";

                } else {
                    numerosTelefone = String.join(", ", clients);
                }

                // Buscar endereço correspondente ao cliente
                Endereco ender = enderecosSeguros.stream()
                        .filter(e -> e != null && e.getCliente() != null && e.getCliente().getId().equals(clientId))
                        .findFirst()
                        .orElse(null);

                String endereco = "";
                if (ender != null) {
                    String logradouro = ender.getLogradouro() != null ? ender.getLogradouro() : "";
                    String numero = ender.getNumero() != null ? ender.getNumero() : "";
                    String bairro = ender.getBairro() != null ? ender.getBairro() : "";
                    String cidade = ender.getCidade() != null ? ender.getCidade() : "";
                    String estado = ender.getEstado() != null ? ender.getEstado() : "";
                    String cep = ender.getCep() != null ? ender.getCep() : "";

                    endereco = String.format("%s, %s - %s, %s/%s (%s)",
                            logradouro, numero, bairro, cidade, estado, cep);

                } else {
                    endereco = "Cliente sem endereço";

                }

                String instant = c != null && c.getInstante() != null ? c.getInstante() : null;
                String inst = formatter.format(Instant.parse(instant));

                Long codigo = c.getId();
                String nome = c != null && c.getNome() != null ? c.getNome() : "";

                List<String> orders = pedidosSeguros.stream()
                        .filter(p -> p != null && p.getCliente() != null
                                && p.getCliente().getId().equals(clientId))
                        .map(p -> {
                            String idStr = (p.getId() != null) ? p.getId().toString() : "SEM ID";
                            String statusStr = (p.getStatus() != null) ? p.getStatus() : "SEM STATUS";
                            return idStr + " - (" + statusStr + ")"; // <-- aqui pega ID e Status
                        })
                        .toList();

                // Montar string com todos os telefones
                String numeroPedido;

                if (orders.isEmpty()) {
                    numeroPedido = "Cliente sem pedido";

                } else {
                    numeroPedido = String.join(" , ", orders);
                }

                listaClientes.add(new ClienteTabelaDPO(
                        codigo,
                        nome,
                        inst,
                        numerosTelefone,
                        endereco,
                        numeroPedido));

                listaFiltradaCliente = new FilteredList<>(listaClientes, p -> true);

                tabelaCliente.setItems(listaFiltradaCliente);

                txtFiltrarCliente.textProperty().addListener((obs, oldValue, newValue) -> {
                    String filtro = newValue.toLowerCase();
                    listaFiltradaCliente.setPredicate(cliente -> {
                        if (filtro == null || filtro.isEmpty()) {
                            return true;
                        }

                        return cliente.getNome().toLowerCase().contains(filtro) ||
                                cliente.getEndereco().toLowerCase().contains(filtro) ||
                                cliente.getTelefones().contains(filtro) ||
                                cliente.getPedidos().contains(filtro) ||
                                String.valueOf(cliente.getId()).contains(filtro) ||
                                cliente.getInstante().contains(filtro) ||
                                cliente.getPedidos().toLowerCase().contains(filtro);

                    });
                });
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onActionUser(ActionEvent event) {

        Platform.runLater(() -> {
            txtFiltrar.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

        paneUser.setVisible(true);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.RED);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        panePedido.setVisible(false);
        lblPedido.setTextFill(Color.WHITE);
    }

    @FXML
    void onActionFornec(ActionEvent event) {

        Platform.runLater(() -> {
            txtFiltrarFornec.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(true);
        lblFornec.setTextFill(Color.RED);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        panePedido.setVisible(false);
        lblPedido.setTextFill(Color.WHITE);

        Label placeholderFornec = new Label("Nenhum Fornecedor encontrado!");
        placeholderFornec.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaFornec.setPlaceholder(placeholderFornec);

        tabelaFornec.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colFornec.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colTelefoneFornec.setCellValueFactory(new PropertyValueFactory<>("Telefones"));
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpjCpf"));
        colEnderecoFornec.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colCodigoFornec.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProdutoFornec.setCellValueFactory(new PropertyValueFactory<>("produtos"));
        colNotaFiscalFornec.setCellValueFactory(new PropertyValueFactory<>("notasFiscais"));

        try {
            FornecedorService fornecedorService = new FornecedorService();
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();
            ProdutoService produtoService = new ProdutoService();
            NotaFiscalService notaFiscalService = new NotaFiscalService();

            List<Fornecedor> fornecedores = fornecedorService.buscarTodos();
            List<Telefone> telefones = telefoneService.buscarTodos();
            List<Endereco> end = enderecoService.buscarTodos();
            List<Produto> prod = produtoService.buscarTodos();
            List<NotaFiscal> nota = notaFiscalService.buscarTodos();

            ObservableList<FornecedorDTO> listaFornecedores = FXCollections.observableArrayList();

            // evita NullPointer se alguma lista vier nula
            List<Fornecedor> fornecedoresSeguros = fornecedores != null ? fornecedores : List.of();
            List<Telefone> telefonesSeguros = telefones != null ? telefones : List.of();
            List<Endereco> enderecosSeguros = end != null ? end : List.of();
            List<Produto> produtosSeguros = prod != null ? prod : List.of();
            List<NotaFiscal> notasFiscaisSeguros = nota != null ? nota : List.of();

            for (Fornecedor f : fornecedoresSeguros) {

                Long userId = f != null ? f.getId() : null;

                // Buscar todos os telefones do usuário
                List<String> telefonesUsuario = telefonesSeguros.stream()
                        .filter(t -> t != null && t.getFornecedor() != null && t.getFornecedor().getId().equals(userId))
                        .map(Telefone::getNumero)
                        .filter(Objects::nonNull)
                        .toList();

                // Montar string com todos os telefones
                String numerosTelefone;
                if (telefonesUsuario.isEmpty()) {
                    numerosTelefone = "Usuário sem telefone";

                } else {
                    numerosTelefone = String.join(", ", telefonesUsuario);
                }

                // Buscar todos os produtos do fornecedor
                List<String> produtoFornecedor = produtosSeguros.stream()
                        .filter(p -> p != null && p.getFornecedor() != null && p.getFornecedor().getId().equals(userId))
                        .map(Produto::getNome)
                        .filter(Objects::nonNull)
                        .toList();

                // Montar string com todos os produtos
                String produtoF;
                if (produtoFornecedor.isEmpty()) {
                    produtoF = "fornecedor sem proditos";

                } else {
                    produtoF = String.join(", ", produtoFornecedor);
                }

                // buscar endereço correspondente
                Endereco ender = enderecosSeguros.stream()
                        .filter(e -> e != null && e.getFornecedor() != null && e.getFornecedor().getId().equals(userId))
                        .findFirst()
                        .orElse(null);

                String endereco = "";
                if (ender != null) {
                    String logradouro = ender.getLogradouro() != null ? ender.getLogradouro() : "";
                    String numero = ender.getNumero() != null ? ender.getNumero() : "";
                    String bairro = ender.getBairro() != null ? ender.getBairro() : "";
                    String cidade = ender.getCidade() != null ? ender.getCidade() : "";
                    String estado = ender.getEstado() != null ? ender.getEstado() : "";
                    String cep = ender.getCep() != null ? ender.getCep() : "";

                    endereco = String.format("%s, %s - %s, %s/%s (%s)",
                            logradouro, numero, bairro, cidade, estado, cep);
                } else {
                    endereco = "Usuário sem endereço";
                }

                NotaFiscal notas = notasFiscaisSeguros.stream()
                        .filter(n -> n != null && n.getFornecedor() != null && n.getFornecedor().getId().equals(userId))
                        .findFirst()
                        .orElse(null);

                Integer notaFiscal = null;
                if (notas != null) {

                    notaFiscal = notas.getNotaFiscal();
                }

                // proteger campos de Fornecedor também
                Long codigo = f.getId();
                String nome = f != null && f.getEmpresa() != null ? f.getEmpresa() : "";
                String cnpj = f != null && f.getCnpjCpf() != null ? f.getCnpjCpf() : "";

                listaFornecedores.add(new FornecedorDTO(
                        codigo,
                        nome,
                        cnpj,
                        numerosTelefone,
                        endereco,
                        produtoF,
                        notaFiscal

                ));
                listaFiltradaFornecedor = new FilteredList<>(listaFornecedores, p -> true);

                tabelaFornec.setItems(listaFiltradaFornecedor);

                txtFiltrarFornec.textProperty().addListener((obs, oldValue, newValue) -> {
                    String filtro = newValue.toLowerCase();
                    listaFiltradaFornecedor.setPredicate(fornecedor -> {
                        if (filtro == null || filtro.isEmpty()) {
                            return true;
                        }

                        return fornecedor.getEmpresa().toLowerCase().contains(filtro) ||
                                String.valueOf(fornecedor.getId()).contains(filtro) ||
                                fornecedor.getCnpjCpf().toLowerCase().contains(filtro) ||
                                fornecedor.getEndereco().toLowerCase().contains(filtro) ||
                                fornecedor.getProdutos().toLowerCase().contains(filtro) ||
                                fornecedor.getTelefones().toLowerCase().contains(filtro) ||
                                fornecedor.getEndereco().toLowerCase().contains(filtro) ||
                                String.valueOf(fornecedor.getNotasFiscais()).contains(filtro);

                    });
                });

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
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

        Platform.runLater(() -> {
            txtFiltrarProduto.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(true);
        lblProduto.setTextFill(Color.RED);
        txtFiltrarProduto.requestFocus();
        panePedido.setVisible(false);
        lblPedido.setTextFill(Color.WHITE);

        Label placeholderProduto = new Label("Nenhum Produto encontrado!");
        placeholderProduto.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaProduto.setPlaceholder(placeholderProduto);

        tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colIdProduto.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colEstoqueProduto.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        colValorProduto.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        colFornecedorProduto.setCellValueFactory(new PropertyValueFactory<>("fornec"));
        colValorProduto.setCellFactory(MoedaCorrenteCellFactory.forTableColumn());

        try {

            ProdutoService produtoService = new ProdutoService();
            List<Produto> prod = produtoService.buscarTodos();

            ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

            List<Produto> produtosSeguros = prod != null ? prod : List.of();

            for (Produto p : produtosSeguros) {
                Long id = p.getId();
                String codigo = p.getCodigo();
                String nome = p != null && p.getNome() != null ? p.getNome() : "";
                Integer estoque = p != null && p.getEstoque() != null ? p.getEstoque() : null;
                Double valorCompra = p != null && p.getValorCompra() != null ? p.getValorCompra() : null;
                Double valorVenda = p != null && p.getValorVenda() != null ? p.getValorVenda() : null;
                Integer quantcompra = p != null && p.getQuantcompra() != null ? p.getQuantcompra() : null;

                String fornec = p != null && p.getFornecedor().getEmpresa() != null ? p.getFornecedor().getEmpresa()
                        : "Sem fornecedor";

                listaProdutos.add(new Produto(
                        id,
                        codigo,
                        nome,
                        estoque,
                        valorCompra,
                        valorVenda,
                        quantcompra,
                        fornec

                ));
                listaFiltradaProduto = new FilteredList<>(listaProdutos, pr -> true);

                tabelaProduto.setItems(listaFiltradaProduto);

                txtFiltrarProduto.textProperty().addListener((obs, oldValue, newValue) -> {
                    String filtro = newValue.toLowerCase();
                    listaFiltradaProduto.setPredicate(produto -> {
                        if (filtro == null || filtro.isEmpty()) {
                            return true;
                        }

                        return produto.getNome().toLowerCase().contains(filtro) ||
                                produto.getFornec().toLowerCase().contains(filtro) ||
                                String.valueOf(produto.getId()).contains(filtro) ||
                                String.valueOf(produto.getEstoque()).contains(filtro) ||
                                String.valueOf(produto.getValorVenda()).contains(filtro) ||
                                String.valueOf(produto.getCodigo()).contains(filtro) ||
                                String.valueOf(produto.getQuantcompra()).contains(filtro);

                    });
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void limparCliente(ActionEvent event) {
        txtFiltrarCliente.clear();

    }

    @FXML
    void limparPedido(ActionEvent event) {

    }

    @FXML
    void onActionPedido(ActionEvent event) {

        Platform.runLater(() -> {
            txtFiltrarPedido.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        panePedido.setVisible(true);
        lblPedido.setTextFill(Color.RED);

        Label placeholderPedido = new Label("Nenhum Pedido encontrado!");
        placeholderPedido.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        tabelaPedido.setPlaceholder(placeholderPedido);

        tabelaPedido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colIdPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStatusPedido.setCellValueFactory(new PropertyValueFactory<>("status"));
        colValorPedido.setCellValueFactory(new PropertyValueFactory<>("valor"));

        colDataPedido.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDataPedido.setCellValueFactory(cell -> {
            Pedido p = cell.getValue();
            String instante = p.getData();
            if (instante == null) {
                return new SimpleStringProperty("");
            }
            String dataFormatada = formatter.format(Instant.parse(instante));
            return new SimpleStringProperty(dataFormatada);
        });

        colValorPedido.setCellFactory(MoedaCorrenteCellFactory.forTableColumn());

        colProdutoPedido.setCellValueFactory(cell -> {
            Pedido p = cell.getValue();

            if (p.getProdutos() == null || p.getProdutos().isEmpty()) {
                return new SimpleStringProperty("");
            }

            String produtos = p.getProdutos()
                    .stream()
                    .map(Produto::getNome)
                    .collect(Collectors.joining(", "));

            return new SimpleStringProperty(produtos);
        });

        colPagamentoPedido.setCellValueFactory(cell -> {
            Pedido p = cell.getValue();

            if (p == null || p.getPagamentos() == null || p.getPagamentos().isEmpty()) {
                return new SimpleStringProperty("");
            }

            String pagamentos = p.getPagamentos()
                    .stream()
                    .map(pg -> {

                        String forma = pg.getTipo() != null ? pg.getTipo() : "";

                        // double → BigDecimal (CORRETO)
                        BigDecimal valor = BigDecimal.valueOf(pg.getValorPagamento());

                        // BigDecimal → R$ 0,00
                        String valorFormatado = MaskTextField.bigDecimalParaMoedaBR(valor);

                        return forma + " - " + valorFormatado;
                    })
                    .collect(Collectors.joining(", "));

            return new SimpleStringProperty(pagamentos);
        });

        colClientePedido.setCellValueFactory(cell -> {
            Pedido p = cell.getValue();

            if (p.getCliente() == null) {
                return new SimpleStringProperty("");
            }

            return new SimpleStringProperty(p.getCliente().getNome());
        });

        try {
            PedidoService pedidoService = new PedidoService();
            List<Pedido> pedidos = pedidoService.buscarTodos();

            ObservableList<Pedido> listaPedido = FXCollections
                    .observableArrayList(pedidos != null ? pedidos : List.of());

            listaFiltradaPedido = new FilteredList<>(listaPedido, p -> true);
            tabelaPedido.setItems(listaFiltradaPedido);

        } catch (Exception e) {
            e.printStackTrace();
        }

        txtFiltrarPedido.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue == null ? "" : newValue.toLowerCase().trim();

            listaFiltradaPedido.setPredicate(pedido -> {

                if (filtro.isEmpty()) {
                    return true;
                }

                // ID
                boolean matchId = pedido.getId() != null &&
                        String.valueOf(pedido.getId()).contains(filtro);

                // STATUS
                boolean matchStatus = pedido.getStatus() != null &&
                        pedido.getStatus().toLowerCase().contains(filtro);

                // CLIENTE
                boolean matchCliente = pedido.getCliente() != null &&
                        pedido.getCliente().getNome() != null &&
                        pedido.getCliente().getNome().toLowerCase().contains(filtro);

                // PRODUTOS (lista)
                boolean matchProduto = pedido.getProdutos() != null &&
                        pedido.getProdutos().stream()
                                .anyMatch(produto -> produto.getNome() != null &&
                                        produto.getNome().toLowerCase().contains(filtro));

                // PAGAMENTOS (opcional)
                boolean matchPagamento = pedido.getPagamentos() != null &&
                        pedido.getPagamentos().stream()
                                .anyMatch(pg -> pg.getTipo() != null &&
                                        pg.getTipo().toLowerCase().contains(filtro));
                // 🔥 DATA
                boolean matchData = false;
                if (pedido.getData() != null) {
                    //matchData = pedido.getData().toLowerCase().contains(filtro);
                    // OU se for Instant:
                    Instant inst = Instant.parse(pedido.getData());
                    matchData = formatter.format(inst).contains(filtro);
                }

                return matchId
                        || matchStatus
                        || matchCliente
                        || matchProduto
                        || matchData
                        || matchPagamento;
            });
        });

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

        try {

            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

            configurarTabelaUsuario();
            configurarFiltroUsuario();

            carregarUsuarios();

            Platform.runLater(() -> txtFiltrar.requestFocus());

            lbluser.setMouseTransparent(true);
            lblClient.setMouseTransparent(true);
            lblFornec.setMouseTransparent(true);
            lblProduto.setMouseTransparent(true);
            lblPedido.setMouseTransparent(true);

            btnPedido.setOnMouseExited(e -> {
                HouverEffectUtil.apllyHouverSair(btnPedido);
            });

            btnPedido.setOnMouseEntered(e -> {
                HouverEffectUtil.apllyHouverSobre(btnPedido);

            });

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

        } catch (Exception e) {
            AlertUtil.mostrarErro("Erro no initialize: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void configurarTabelaUsuario() {

        Label placeholder = new Label("Nenhum usuário encontrado!");
        placeholder.setStyle("-fx-text-fill: white; -fx-font-size: 16px");
        tabelaUsuario.setPlaceholder(placeholder);

        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefones"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        // DATA FORMATADA
        colData.setCellValueFactory(cell -> {
            Usuario u = cell.getValue();
            return new SimpleStringProperty(
                    u != null && u.getInstante() != null ? u.getInstante() : "");
        });
    }

    private void carregarUsuarios() {

        try {
            UsuarioService usuarioService = new UsuarioService();
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();

            List<Usuario> usuarios = usuarioService.buscarTodos();
            List<Telefone> telefones = telefoneService.buscarTodos();
            List<Endereco> enderecos = enderecoService.buscarTodos();

            ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

            List<Usuario> usuariosSeguros = usuarios != null ? usuarios : List.of();
            List<Telefone> telefonesSeguros = telefones != null ? telefones : List.of();
            List<Endereco> enderecosSeguros = enderecos != null ? enderecos : List.of();

            for (Usuario u : usuariosSeguros) {

                Long userId = u.getId();

                /* ================= TELEFONES ================= */
                List<String> telefonesUsuario = telefonesSeguros.stream()
                        .filter(t -> t.getOperador() != null &&
                                t.getOperador().getId().equals(userId))
                        .map(Telefone::getNumero)
                        .filter(Objects::nonNull)
                        .toList();

                String telefonesStr = telefonesUsuario.isEmpty()
                        ? "Sem telefone"
                        : String.join(", ", telefonesUsuario);

                /* ================= ENDEREÇO ================= */
                Endereco endereco = enderecosSeguros.stream()
                        .filter(e -> e.getOperador() != null &&
                                e.getOperador().getId().equals(userId))
                        .findFirst()
                        .orElse(null);

                String enderecoStr = endereco == null ? "Sem endereço"
                        : String.format("%s, %s - %s, %s/%s (%s)",
                                safe(endereco.getLogradouro()),
                                safe(endereco.getNumero()),
                                safe(endereco.getBairro()),
                                safe(endereco.getCidade()),
                                safe(endereco.getEstado()),
                                safe(endereco.getCep()));

                /* ================= DATA ================= */
                String dataFormatada = "";
                try {
                    if (u.getInstante() != null && !u.getInstante().isBlank()) {
                        dataFormatada = formatter.format(Instant.parse(u.getInstante()));
                    }
                } catch (Exception e) {
                    dataFormatada = "";
                }

                listaUsuarios.add(new Usuario(
                        u.getId(),
                        safe(u.getNome()),
                        safe(u.getSetor()),
                        safe(u.getLogin()),
                        dataFormatada,
                        null,
                        telefonesStr,
                        enderecoStr));
            }

            listaFiltrada = new FilteredList<>(listaUsuarios, p -> true);
            tabelaUsuario.setItems(listaFiltrada);

        } catch (Exception e) {
            AlertUtil.mostrarErro("Erro ao carregar usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String safe(String valor) {
        return valor == null ? "" : valor;
    }

    private void configurarFiltroUsuario() {

        txtFiltrar.textProperty().addListener((obs, oldV, newV) -> {
            String filtro = newV.toLowerCase();

            listaFiltrada.setPredicate(u -> {
                if (filtro.isEmpty())
                    return true;

                return u.getNome().toLowerCase().contains(filtro)
                        || String.valueOf(u.getId()).contains(filtro)
                        || u.getSetor().toLowerCase().contains(filtro)
                        || u.getLogin().toLowerCase().contains(filtro)
                        || u.getEndereco().toLowerCase().contains(filtro)
                        || u.getTelefones().toLowerCase().contains(filtro);
            });
        });
    }

}
