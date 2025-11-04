package com.canes.controller;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Fornecedor;
import com.canes.model.FornecedorDTO;
import com.canes.model.NotaFiscal;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.Usuario;
import com.canes.model.dpo.ClienteTelefoneDpo;
import com.canes.services.EnderecoService;
import com.canes.services.FornecedorService;
import com.canes.services.NotaFiscalService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.services.UsuarioService;
import com.canes.util.HouverEffectUtil;
import com.canes.util.ScreenUtils;

import javafx.application.Platform;
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
    private TableColumn<Produto, Double> colValorProduto;

    @FXML
    private TableColumn<Produto, Integer> colEstoqueProduto;

    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableView<ClienteTelefoneDpo> tabelaCliente;

    @FXML
    private TableView<FornecedorDTO> tabelaFornec;

    @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private Button btnFiltrar;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField txtFiltrar;

    private FilteredList<Usuario> listaFiltrada;

    private FilteredList<ClienteTelefoneDpo> listaFiltradaCliente;

    private FilteredList<FornecedorDTO> listaFiltradaFornecedor;

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
    }

    @FXML
    void onActionUser(ActionEvent event) {

         Platform.runLater(() -> {
            txtFiltrar.requestFocus();
            //txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

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
        Platform.runLater(() -> {
            txtFiltrar.requestFocus();
            // txtNomeProduto.selectAll(); // opcional: seleciona todo o texto
        });

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

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefones"));
        colData.setCellValueFactory(new PropertyValueFactory<>("instante"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        // Usuario u1 = new Usuario(1, "Carlos Borges", "ADM", "Carlos",
        // Instant.parse("2025-09-10T12:00:09Z"), "123",
        // Arrays.asList(new Telefone(1, "(99) 88888-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Sebastião Soares ", Instant.now(),
        // Arrays.asList(new Telefone()), Arrays.asList(new
        // Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u2 = new Usuario(2, "Cintia Yellon", "ADMINISTRATIVO", "GRU",
        // Instant.now(), "123",
        // Arrays.asList(new Telefone(1, "(99) 99988-0000"), new Telefone(2, "(21)
        // 99888-8776")),
        // Arrays.asList(new Endereco("Rua Alan", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Antonio Pires ", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u3 = new Usuario(3, "Joâo Pedro", "ADM", "GRU", Instant.now(), "123",
        // Arrays.asList(new Telefone(3, "(65)4343-4343")),
        // Arrays.asList(new Endereco("Rua Orca", "234", "Centro", "Rio de Janeiro",
        // "RJ", "26.000-000")),
        // Arrays.asList(new Cliente("Danilo Silva", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u4 = new Usuario(4, "Terla Gomes", "ADM", "GRU", Instant.now(),
        // "123",
        // Arrays.asList(new Telefone(1, "(99) 823888-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Willian Lima", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u5 = new Usuario(5, "Joana Black", "ADM", "GRU", Instant.now(),
        // "123",
        // Arrays.asList(new Telefone(1, "(99) 88888-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("José Soares ", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u6 = new Usuario(6, "Flavio Blue", "ADM", "GRU", Instant.now(),
        // "123",
        // Arrays.asList(new Telefone(1, "(99) 877788-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Cristian Morais", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u7 = new Usuario(7, "Yasmin Red", "ADM", "GRU", Instant.now(), "123",
        // Arrays.asList(new Telefone(1, "(99) 88888-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Washington Torres Soares ", Instant.now(),
        // Arrays.asList(new Telefone()), Arrays.asList(new
        // Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u8 = new Usuario(8, "Washington Pink", "ADM", "GRU", Instant.now(),
        // "123",
        // Arrays.asList(new Telefone(1, "(99) 76588-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Flavio Tulio", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));
        // Usuario u9 = new Usuario(9, "Karla Khaki", "ADM", "GRU", Instant.now(),
        // "123",
        // Arrays.asList(new Telefone(1, "(99) 09038-0000")),
        // Arrays.asList(new Endereco("Rua Abc", "234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Cliente("Luis Carlos", Instant.now(), Arrays.asList(new
        // Telefone()), Arrays.asList(new Endereco()),Arrays.asList(new Pedido())),new
        // Cliente("Lucca ferraz Carlos", Instant.now(), Arrays.asList(new Telefone()),
        // Arrays.asList(new Endereco()),Arrays.asList(new Pedido()))));

        try {
            UsuarioService usuarioService = new UsuarioService();
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();

            List<Usuario> usuarios = usuarioService.buscarTodos();
            List<Telefone> telefones = telefoneService.buscarTodos();
            List<Endereco> end = enderecoService.buscarTodos();

            ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

            // evita NullPointer se alguma lista vier nula
            List<Usuario> usuariosSeguros = usuarios != null ? usuarios : List.of();
            List<Telefone> telefonesSeguros = telefones != null ? telefones : List.of();
            List<Endereco> enderecosSeguros = end != null ? end : List.of();

            for (Usuario u : usuariosSeguros) {

                Long userId = u != null ? u.getId() : null;

                // Buscar todos os telefones do usuário
                List<String> telefonesUsuario = telefonesSeguros.stream()
                        .filter(t -> t != null && t.getOperador() != null && t.getOperador().getId().equals(userId))
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

                // buscar endereço correspondente
                Endereco ender = enderecosSeguros.stream()
                        .filter(e -> e != null && e.getOperador() != null && e.getOperador().getId().equals(userId))
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

                // proteger campos de Usuario também
                Long codigo = u.getId();
                String nome = u != null && u.getNome() != null ? u.getNome() : "";
                String login = u != null && u.getLogin() != null ? u.getLogin() : "";
                String setor = u != null && u.getSetor() != null ? u.getSetor() : "";
                String instant = u != null && u.getInstante() != null ? u.getInstante() : null;
                String inst = formatter.format(Instant.parse(instant));
                listaUsuarios.add(new Usuario(
                        codigo,
                        nome,
                        setor,
                        login,
                        inst,
                        null,
                        numerosTelefone,
                        endereco

                ));
                System.out.println(u.getLogin() + u.getNome() + numerosTelefone + endereco + inst + codigo);
                listaFiltrada = new FilteredList<>(listaUsuarios, p -> true);

                tabelaUsuario.setItems(listaFiltrada);
                txtFiltrar.textProperty().addListener((obs, oldValue, newValue) -> {
                    String filtro = newValue.toLowerCase();
                    listaFiltrada.setPredicate(usuario -> {
                        if (filtro == null || filtro.isEmpty()) {
                            return true;
                        }

                        return usuario.getNome().toLowerCase().contains(filtro) ||
                                String.valueOf(usuario.getId()).contains(filtro) ||
                                usuario.getSetor().toLowerCase().contains(filtro) ||
                                usuario.getEndereco().toLowerCase().contains(filtro) ||
                                usuario.getLogin().contains(filtro) ||
                                usuario.getInstante().contains(filtro) ||
                                usuario.getSetor().toLowerCase().contains(filtro) ||
                                usuario.getTelefones().toLowerCase().contains(filtro);

                    });
                });

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

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

        // Cliente c1 = new Cliente("Carlos borges", Instant.now(),
        // Arrays.asList(new Telefone(1, "(21) 98989-0000"), new Telefone(2, "(21)
        // 98989-9999")),
        // Arrays.asList(new Endereco("Rua Abacaxi", "23234", "Centro", "Rio", "RJ",
        // "26.000-000")),
        // Arrays.asList(new Pedido(1, "PAGO", 150.9, Instant.now(), Arrays.asList(new
        // Produto()))));
        // Cliente c2 = new Cliente("Carlos ", Instant.now(),
        // Arrays.asList(new Telefone(3, "(21) 98359-0550"), new Telefone(4, "(21)
        // 98989-9999")),
        // Arrays.asList(new Endereco("Rua Abc", "2w34", "Cuica", "São Paulo", "SP",
        // "02.000-000")),
        // Arrays.asList(new Pedido(2, "CANCELADO", 150.9, Instant.now(),
        // Arrays.asList(new Produto()))));
        // Cliente c3 = new Cliente(" borges", Instant.now(),
        // Arrays.asList(new Telefone(5, "(21) 98989-0000"), new Telefone(6, "(21)
        // 98989-9999")),
        // Arrays.asList(new Endereco("Rua Abc", "2q34", "Centro", "Rio", "RJ",
        // "26.000-000")), Arrays.asList(
        // new Pedido(3, "AGARDANDO PAGAMENTO", 150.9, Instant.now(), Arrays.asList(new
        // Produto()))));

        ObservableList<ClienteTelefoneDpo> listaClientes = FXCollections.observableArrayList();

        // for (Cliente c : Arrays.asList(c1, c2, c3)) {
        // for (Telefone t : c.getTelefones()) {
        // for (Endereco e : c.getEnderecos()) {
        // for (Pedido p : c.getPedidos()) {
        // String enderecoCompleto = String.format("%s, %s - %s, %s/%s (%s)",
        // e.getLogradouro(), e.getNumero(), e.getBairro(), e.getCidade(),
        // e.getEstado(),
        // e.getCep());

        // String pedidosCompleto = String.format("%s - (%s) - %s", p.getId(),
        // p.getStatus(),
        // nf.format(p.getValor()));

        // listaClientes
        // .add(new ClienteTelefoneDpo(c.getNome(), c.getInstante(), t.getNumero(),
        // enderecoCompleto, pedidosCompleto));

        // // }
        // // }

        // // }
        // }

        // //listaFiltradaCliente = new FilteredList<>(listaClientes, p -> true);

        // // tabelaCliente.setItems(listaFiltradaCliente);

        // // txtFiltrarCliente.textProperty().addListener((obs, oldValue, newValue) ->
        // {
        // // String filtro = newValue.toLowerCase();
        // // listaFiltradaCliente.setPredicate(cliente -> {
        // // if (filtro == null || filtro.isEmpty()) {
        // // return true;
        // // }

        // // String dataString = cliente.getInstante() == null ? ""
        // // : formatter.format(cliente.getInstante()).toLowerCase();

        // // return cliente.getNome().toLowerCase().contains(filtro) ||

        // // dataString.contains(filtro) ||
        // cliente.getEnderecos().toLowerCase().contains(filtro)
        // // || cliente.getTelefones().contains(filtro) ||
        // // cliente.getPedidos().toLowerCase().contains(filtro);

        // // });
        // // });

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

        // Fornecedor f1 = new Fornecedor("Cia Roupas", "11.111.222/0001-09",
        // Arrays.asList(new Telefone(1, "(88) 88888-0000")));
        // Fornecedor f2 = new Fornecedor("Roupas Cia", "22.333.444/0001-07",
        // Arrays.asList(new Telefone(2, "(21) 22222-4444"), new Telefone(3, "(21)
        // 99803-8215")));
        // Fornecedor f3 = new Fornecedor("Poco Roupas", "66.111.222/0001-09",
        // Arrays.asList(new Telefone(1, "(88) 80878-0000")));
        // Fornecedor f4 = new Fornecedor("Rio Cia", "22.003.444/0001-07",
        // Arrays.asList(new Telefone(2, "(21) 22222-8764"), new Telefone(3, "(21)
        // 99803-8215")));

        // for (Fornecedor f : Arrays.asList(f1, f2, f3, f4)) {
        // for (Telefone t : f.getTelefone()) {
        // listaFornecedor.add(new FornecedorTelefoneDpo(f.getEmpresa(), f.getCnpj(),
        // t.getNumero()));
        // }
        // }

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

                // System.out.println("Fornecedor ID: " + f.getId());
                // enderecosSeguros.forEach(e -> System.out.println("Produto: " + e.getId() + "
                // | FornecedorID: " + e.getFornecedor().getId()));

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

                System.out.println();
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

        // txtFiltrarFornec.textProperty().addListener((obs, oldValue, newValue) -> {
        // String filtro = newValue.toLowerCase();
        // listaFiltradaFornecedor.setPredicate(fornecedor -> {
        // if (filtro == null || filtro.isEmpty()) {
        // return true;
        // }

        // return fornecedor.getEmpresa().toLowerCase().contains(filtro) ||
        // fornecedor.getTelefone().toLowerCase().contains(filtro) ||
        // fornecedor.getCnpj().toLowerCase().contains(filtro);

        // });
        // });

        // Label placeholderProduto = new Label("Nenhum Produto encontrado!");
        // placeholderProduto.setStyle("-fx-text-fill: fff; -fx-font-size: 16px");
        // tabelaProduto.setPlaceholder(placeholderProduto);

        // tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        // colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        // colEstoqueProduto.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        // colValorProduto.setCellValueFactory(new
        // PropertyValueFactory<>("valorVenda"));

        // listaProdutos = FXCollections.observableArrayList(

        // new Produto("876577777", "Vestido", 10, 123.9, 660.8, 10),
        // new Produto("876577777", "Vestido", 10, 123.9, 660.8, 10),
        // new Produto("8333564598", "Calça Jeans", 6, 123.9, 760.8, 6),
        // new Produto("2457665598", "Vestido Rusti", 4, 123.9, 160.8, 4)

        // );

        // listaFiltradaProduto = new FilteredList<>(listaProdutos, p -> true);

        // tabelaProduto.setItems(listaFiltradaProduto);

        // txtFiltrarProduto.textProperty().addListener((obs, oldValue, newValue) -> {
        // String filtro = newValue.toLowerCase();
        // listaFiltradaProduto.setPredicate(produto -> {
        // if (filtro == null || filtro.isEmpty()) {
        // return true;
        // }

        // String codigoStr = String.valueOf(produto.getCodigo());
        // String estoqueStr = String.valueOf(produto.getEstoque());
        // String valorStr = String.valueOf(produto.getValorVenda());

        // return produto.getNome().toLowerCase().contains(filtro) ||
        // codigoStr.toLowerCase().contains(filtro) ||
        // estoqueStr.toLowerCase().contains(filtro) ||
        // valorStr.toLowerCase().contains(filtro);

        // });
        // });

        // tabelaProduto.setOnMouseClicked(e -> {

        // if (e.getClickCount() == 2) {
        // Produto produtoSelecionado =
        // tabelaProduto.getSelectionModel().getSelectedItem();

        // if (produtoSelecionado != null) {
        // System.out.println("Selecionado: Código " + produtoSelecionado.getId() + "
        // Produto: "
        // + produtoSelecionado.getNome());
        // }

        // }
        // });

    }

}
