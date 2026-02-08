package com.canes.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.canes.util.HouverEffectUtil;
import com.canes.util.MaskTextField;
import com.canes.util.ScreenUtils;
import com.canes.util.TextFieldUtil;
import com.canes.util.UserSession;
import com.canes.model.Cliente;
import com.canes.model.Endereco;
import com.canes.model.Fornecedor;
import com.canes.model.Pedido;
import com.canes.model.Produto;
import com.canes.model.Telefone;
import com.canes.model.Usuario;
import com.canes.services.ClienteService;
import com.canes.services.EnderecoService;
import com.canes.services.PedidoService;
import com.canes.services.ProdutoService;
import com.canes.services.TelefoneService;
import com.canes.services.UsuarioService;
import com.canes.util.AlertUtil;
import com.canes.util.ValidadorSenha;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AtualizarController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAtualizarProduto;

    @FXML
    private Button btnBuscarFornec;

    @FXML
    private Button btnBuscarProduto;

    @FXML
    private ImageView btnNoVisivelReSenha;

    @FXML
    private ImageView btnNoVisivelSenha;

    @FXML
    private Label btnTel;

    @FXML
    private Label feedBackLabel;

    @FXML
    private Label feedBackLabel2;
    @FXML
    private Label labelSenhaRepita;

    @FXML
    private ImageView btnVisivelReSenha;

    @FXML
    private ImageView btnVisivelSenha;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtNoSenha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtReNoSenha;

    @FXML
    private ComboBox<String> txtSetor;

    @FXML
    private VBox vBoxMenos;

    @FXML
    private TextField txtcel;

    @FXML
    private PasswordField passwordReSenha;

    @FXML
    private PasswordField passwordSenha;

    @FXML
    private Label txtOperador;

    @FXML
    private VBox vBoxTel;

    private List<TextField> campos = new ArrayList<>();

    @FXML
    private Button btnCadastrarClient;

    @FXML
    private Button btnFornec;

    @FXML
    private Button btnProduto;

    @FXML
    private Button btnBuscarPedido;

    @FXML
    private Button btnPedido;

    @FXML
    private Pane panePedido;

    @FXML
    private TextField txtCodigoPedido;

    @FXML
    private TextField txtStatusPedido;

    @FXML
    private TextField txtValorPedido;

    @FXML
    private Button btnCadastrarFornec;

    @FXML
    private Button btnLimparClient;

    @FXML
    private Button btnLimparFornec;

    @FXML
    private Label btnTelClient;

    @FXML
    private Button btnUser;

    @FXML
    private TextField txtBairroFornec;

    @FXML
    private TextField txtCepFornec;

    @FXML
    private TextField txtCelFornec;

    @FXML
    private TextField txtCelFornec1;

    @FXML
    private TextField txtCidadeFornec;

    @FXML
    private TextField txtCnpjFornec;

    @FXML
    private TextField txtCodigoFornec;

    @FXML
    private TextField txtEstadoFornec;

    @FXML
    private TextField txtLogradouroFornec;

    @FXML
    private TextField txtNomeFornec;

    @FXML
    private TextField txtNotaFiscalFornec;

    @FXML
    private TextField txtNumeroFornec;

    @FXML
    private TextField txtProdutoFornec;

    @FXML
    private TextField txtQuantFornec;

    @FXML
    private TextField txtTamanhoFornec;

    @FXML
    private TextField txtValorCompraFornec;

    @FXML
    private TextField txtValorVendaFornec;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Label lblClient;

    @FXML
    private Label lblFornec;

    @FXML
    private Label lbluser;

    @FXML
    private Label lblPedido;

    @FXML
    private Pane paneCliente;

    @FXML
    private Pane paneUser;

    @FXML
    private Pane paneFornec;

    @FXML
    private Pane paneProduto;

    @FXML
    private TextField txtBairroClient;

    @FXML
    private TextField txtCepClient;

    @FXML
    private TextField txtCidadeClient;

    @FXML
    private TextField txtEstadoClient;

    @FXML
    private TextField txtLogradouroClient;

    @FXML
    private TextField txtNomeClient;

    @FXML
    private TextField txtNumeroClient;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnLimparProduto;

    @FXML
    private Button btnLimparPedido;

    @FXML
    private Button btnAtualizarPedido;

    @FXML
    private TextField txtCelClient1;

    @FXML
    private TextField txtcelClient;

    @FXML
    private TextField txtCelUsuario;

    @FXML
    private VBox vBoxTelClient;

    @FXML
    private VBox vBoxTelFornec;

    @FXML
    private Label lblProduto;

    private Long usuarioId;

    @FXML
    void onActionProduto(ActionEvent event) {
        Platform.runLater(() -> txtCodigoFornec.requestFocus());

        paneProduto.setVisible(true);
        lblProduto.setTextFill(Color.RED);
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        panePedido.setVisible(false);
        lblPedido.setTextFill(Color.WHITE);

    }

    @FXML
    void buscarUsuario(ActionEvent event) {

        buscarUsuario();

    }

    @FXML
    void onClickBuscar(ActionEvent event) {

        buscarUsuario();

    }

    private void atualizarUsuario() {

        try {
            Map<String, Object> dados = new HashMap<>();

            dados.put("setor", txtSetor.getValue().toString());
            dados.put("nome", txtNome.getText());
            dados.put("login", txtLogin.getText());
            dados.put("nome", txtNome.getText());
            dados.put("senha", passwordSenha.getText());

            UsuarioService usuarioService = new UsuarioService();
            usuarioService.atualizarParcial(usuarioId, dados);
            AlertUtil.mostrarSucesso("Usuário atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());
            txtCelUsuario.requestFocus();
        }

        // try {

        // Usuario usuario = new Usuario();
        // usuario.setId(usuarioId);
        // usuario.setSetor(txtSetor.getValue().toString());
        // usuario.setNome(txtNome.getText());
        // usuario.setLogin(txtLogin.getText());
        // // usuario.setInstante(instanteFormatado);
        // usuario.setSenha(passwordSenha.getText());

        // limparUsuario();
        // AlertUtil.mostrarSucesso("Usuário atualizado com sucesso!");

        // UsuarioService usuarioService = new UsuarioService();
        // usuarioService.atualizarUsuario(usuario);

        // } catch (Exception e) {
        // AlertUtil.mostrarErro("Erro ao tentar salvar no banco\n" + e.getMessage());

        // }

    }

    private void buscarUsuario() {

        try {
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();

            Map<String, Usuario> mapaTelefoneUsuario = new HashMap<>();
            Map<Long, Endereco> mapaUsuarioEndereco = new HashMap<>();

            // 1️⃣ Mapa cliente -> endereco
            for (Endereco e : enderecoService.buscarTodos()) {
                if (e.getOperador() != null) {
                    mapaUsuarioEndereco.put(e.getOperador().getId(), e);
                }
            }

            // 2️⃣ Mapa telefone -> usuario
            for (Telefone t : telefoneService.buscarTodos()) {

                if (t.getNumero() == null || t.getOperador() == null)
                    continue;

                mapaTelefoneUsuario.put(t.getNumero(), t.getOperador());
            }

            // 3️⃣ Busca
            String telefoneDigitado = txtCelUsuario.getText();
            txtcel.setText(telefoneDigitado);

            Usuario usuario = mapaTelefoneUsuario.get(telefoneDigitado);

            if (usuario != null) {

                usuarioId = usuario.getId();

                txtNome.setText(usuario.getNome());
                txtLogin.setText(usuario.getLogin());
                passwordSenha.setText(usuario.getSenha());
                txtSetor.setValue(usuario.getSetor());

                // ✅ AGORA SIM
                Endereco endereco = mapaUsuarioEndereco.get(usuario.getId());

                if (endereco != null) {
                    txtLogradouro.setText(endereco.getLogradouro());
                    txtCidade.setText(endereco.getCidade());
                    txtBairro.setText(endereco.getBairro());
                    txtNumero.setText(endereco.getNumero());
                    txtEstado.setText(endereco.getEstado());
                    txtCep.setText(endereco.getCep());

                } else {
                    AlertUtil.mostrarErro("Endereço não encontrado.");
                }

            } else {
                AlertUtil.mostrarErro("Usuário não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void buscarCliente() {

        try {
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();

            Map<String, Cliente> mapaTelefoneCliente = new HashMap<>();
            Map<Long, Endereco> mapaClienteEndereco = new HashMap<>();

            // 1️⃣ Mapa cliente -> endereco
            for (Endereco e : enderecoService.buscarTodos()) {
                if (e.getCliente() != null) {
                    mapaClienteEndereco.put(e.getCliente().getId(), e);
                }
            }

            // 2️⃣ Mapa telefone -> usuario
            for (Telefone t : telefoneService.buscarTodos()) {

                if (t.getNumero() == null || t.getCliente() == null)
                    continue;

                mapaTelefoneCliente.put(t.getNumero(), t.getCliente());
            }

            // 3️⃣ Busca
            String telefoneDigitado = txtcelClient.getText();
            txtCelClient1.setText(telefoneDigitado);

            Cliente cliente = mapaTelefoneCliente.get(telefoneDigitado);

            if (cliente != null) {
                txtNomeClient.setText(cliente.getNome());

                // ✅ AGORA SIM
                Endereco endereco = mapaClienteEndereco.get(cliente.getId());

                if (endereco != null) {
                    txtLogradouroClient.setText(endereco.getLogradouro());
                    txtCidadeClient.setText(endereco.getCidade());
                    txtBairroClient.setText(endereco.getBairro());
                    txtNumeroClient.setText(endereco.getNumero());
                    txtEstadoClient.setText(endereco.getEstado());
                    txtCepClient.setText(endereco.getCep());
                } else {
                    AlertUtil.mostrarErro("Endereço não encontrado.");
                }

            } else {
                AlertUtil.mostrarErro("Cliente não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    void onClickBuscarCliente(ActionEvent event) {

        buscarCliente();
    }

    @FXML
    void buscarCliente(ActionEvent event) {

        buscarCliente();

    }

    @FXML
    void onClickAtualizarPedido(ActionEvent event) {

    }

    @FXML
    void onClickBuscarPedido(ActionEvent event) {
        try {
            PedidoService pedidoService = new PedidoService();
            List<Pedido> pedido = pedidoService.buscarTodos();

            boolean encontrado = false;

            for (Pedido p : pedido) {
                if (txtCodigoPedido.getText().equals(p.getId().toString())) {

                    BigDecimal valorPedido = new BigDecimal(p.getValor());

                    txtStatusPedido.setText(p.getStatus());
                    txtValorPedido.setText(numberFormat(valorPedido));

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                AlertUtil.mostrarErro("Pedido não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.mostrarErro("Erro ao busca Pedido.");
        }

    }

    @FXML
    void onclickLimparPedido(ActionEvent event) {
        txtCodigoPedido.clear();
        txtStatusPedido.clear();
        txtValorPedido.clear();

    }

    @FXML
    void onClickBuscarClient(ActionEvent event) {

    }

    @FXML
    void onClickBuscarFornec(ActionEvent event) {

        try {
            TelefoneService telefoneService = new TelefoneService();
            EnderecoService enderecoService = new EnderecoService();

            Map<String, Fornecedor> mapaTelefoneFornecedor = new HashMap<>();
            Map<Long, Endereco> mapaFornecedorEndereco = new HashMap<>();

            // 1️⃣ Mapa fornecedor -> endereco
            for (Endereco e : enderecoService.buscarTodos()) {
                if (e.getFornecedor() != null) {
                    mapaFornecedorEndereco.put(e.getFornecedor().getId(), e);
                }
            }

            // 2️⃣ Mapa telefone -> fornecedor
            for (Telefone t : telefoneService.buscarTodos()) {

                if (t.getNumero() == null || t.getFornecedor() == null)
                    continue;

                mapaTelefoneFornecedor.put(t.getNumero(), t.getFornecedor());
            }

            // 3️⃣ Busca
            String telefoneDigitado = txtCelFornec.getText();
            txtCelFornec1.setText(telefoneDigitado);

            Fornecedor fornecedor = mapaTelefoneFornecedor.get(telefoneDigitado);

            if (fornecedor != null) {
                txtNomeFornec.setText(fornecedor.getEmpresa());
                txtCnpjFornec.setText(fornecedor.getCnpjCpf());

                // ✅ AGORA SIM
                Endereco endereco = mapaFornecedorEndereco.get(fornecedor.getId());

                if (endereco != null) {
                    txtLogradouroFornec.setText(endereco.getLogradouro());
                    txtCidadeFornec.setText(endereco.getCidade());
                    txtBairroFornec.setText(endereco.getBairro());
                    txtNumeroFornec.setText(endereco.getNumero());
                    txtEstadoFornec.setText(endereco.getEstado());
                    txtCepFornec.setText(endereco.getCep());
                } else {
                    AlertUtil.mostrarErro("Endereço não encontrado.");
                }

            } else {
                AlertUtil.mostrarErro("Fornecedor não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private static String numberFormat(BigDecimal valor) {

        if (valor == null) {
            return "0,00";
        }
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        return nf.format(valor);
    }

    @FXML
    void onClickBuscarProduto(ActionEvent event) {

        try {

            ProdutoService produtoService = new ProdutoService();
            List<Produto> produto = produtoService.buscarTodos();

            boolean encontrado = false;
            for (Produto p : produto) {

                if (txtCodigoFornec.getText().equals(p.getCodigo())) {
                    BigDecimal valorCompra = new BigDecimal(p.getValorCompra());
                    BigDecimal valorVenda = new BigDecimal(p.getValorVenda());

                    txtProdutoFornec.setText(p.getNome());
                    txtNotaFiscalFornec.setText(p.getNota().getNotaFiscal().toString());
                    txtValorCompraFornec.setText(numberFormat(valorCompra));
                    txtValorVendaFornec.setText(numberFormat(valorVenda));

                    txtQuantFornec.setText(p.getQuantcompra().toString());

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                AlertUtil.mostrarErro("Produto não encontrado.");
            }

        } catch (

        Exception e) {
            e.printStackTrace();
            AlertUtil.mostrarErro("Erro ao buscar produto.");

        }
    }

    @FXML
    void onclickLimparProduto(ActionEvent event) {
        txtCodigoFornec.clear();
        txtProdutoFornec.clear();
        txtNotaFiscalFornec.clear();
        txtTamanhoFornec.clear();
        txtValorCompraFornec.clear();
        txtQuantFornec.clear();
        txtValorVendaFornec.clear();
    }

    @FXML
    void onActionFornec(ActionEvent event) {
        Platform.runLater(() -> txtCelFornec.requestFocus());
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

    }

    @FXML
    void onActionPedido(ActionEvent event) {
        Platform.runLater(() -> txtCodigoPedido.requestFocus());
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
        paneUser.setVisible(false);
        paneCliente.setVisible(false);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.WHITE);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        panePedido.setVisible(true);
        lblPedido.setTextFill(Color.RED);

    }

    @FXML
    void onActionUser(ActionEvent event) {
        Platform.runLater(() -> txtCelUsuario.requestFocus());
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
    void onActionVoltar(MouseEvent event) {

        try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/view/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onactionClient(ActionEvent event) {

        Platform.runLater(() -> txtcelClient.requestFocus());

        paneUser.setVisible(false);
        paneCliente.setVisible(true);
        lbluser.setTextFill(Color.WHITE);
        lblClient.setTextFill(Color.RED);
        paneFornec.setVisible(false);
        lblFornec.setTextFill(Color.WHITE);
        paneProduto.setVisible(false);
        lblProduto.setTextFill(Color.WHITE);
    }

    //
    @FXML
    void onclickLimparFornec(ActionEvent event) {

        txtNomeFornec.clear();
        txtCelFornec.clear();
        txtLogradouroFornec.clear();
        txtNumeroFornec.clear();
        txtBairroFornec.clear();
        txtCidadeFornec.clear();
        txtEstadoFornec.clear();
        txtCepFornec.clear();

    }

    @FXML
    void onClickCadastrarFornec(ActionEvent event) {

        if (txtNomeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumeroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairroFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidadeFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstadoFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCepFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtCelFornec.getText().trim().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;
        } else {
            AlertUtil.mostrarSucesso("Cadastro do Cliente " + txtNomeFornec.getText() + "\nSalvo com sucesso");

            try {
                ScreenUtils.changeScreen(event, "/com/canes/menu.fxml", "Menu", null);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    //
    @FXML
    void onClickLimparFornec(ActionEvent event) {

        txtNomeFornec.clear();
        txtCelFornec.clear();
        txtLogradouroFornec.clear();
        txtNumeroFornec.clear();
        txtBairroFornec.clear();
        txtCidadeFornec.clear();
        txtEstadoFornec.clear();
        txtCepFornec.clear();
        txtCnpjFornec.clear();
        txtProdutoFornec.clear();
        txtValorCompraFornec.clear();
        txtValorVendaFornec.clear();
        txtTamanhoFornec.clear();
        txtCodigoFornec.clear();
        txtNotaFiscalFornec.clear();
        txtCelFornec.clear();
        txtCelFornec1.clear();

    }

    @FXML
    void onclickLimparClient(ActionEvent event) {

        Platform.runLater(() -> txtcelClient.requestFocus());

        txtNomeClient.clear();
        txtcelClient.clear();
        txtLogradouroClient.clear();
        txtNumeroClient.clear();
        txtBairroClient.clear();
        txtCidadeClient.clear();
        txtEstadoClient.clear();
        txtCepClient.clear();
        txtCelClient1.clear();

    }

    @FXML
    void onClickcadastrarClient(ActionEvent event) {

        if (txtNomeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumeroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairroClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidadeClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstadoClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCepClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtcelClient.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;
        } else {
            AlertUtil.mostrarSucesso("Cadastro do Cliente " + txtNomeClient.getText() + "\nSalvo com sucesso");

            try {
                ScreenUtils.changeScreen(event, "/com/canes/menu.fxml", "Menu", null);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    @FXML
    void onClickTelClient(MouseEvent event) {
        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTelClient.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTelClient.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTelClient.getChildren().add(linha);
    }

    @FXML
    void onClickTelFornec(MouseEvent event) {
        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTelFornec.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTelFornec.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTelFornec.getChildren().add(linha);
    }

    @FXML
    void onClickEnviar(MouseEvent event) {

    }

    @FXML
    void onClickNoVisivelReSenha(MouseEvent event) {

        passwordReSenha.setText(passwordReSenha.getText());

        passwordReSenha.setVisible(true);
        passwordReSenha.setManaged(true);
        btnVisivelReSenha.setVisible(true);
        btnVisivelReSenha.setManaged(true);

        txtReNoSenha.setVisible(false);
        txtReNoSenha.setManaged(false);
        btnNoVisivelReSenha.setVisible(false);
        btnNoVisivelReSenha.setManaged(false);

    }

    @FXML
    void onClickNoVisivelSenha(MouseEvent event) {

        passwordSenha.setText(txtNoSenha.getText());

        passwordSenha.setVisible(true);
        passwordSenha.setManaged(true);
        btnVisivelSenha.setVisible(true);
        btnVisivelSenha.setManaged(true);

        txtNoSenha.setVisible(false);
        txtNoSenha.setManaged(false);
        btnNoVisivelSenha.setVisible(false);
        btnNoVisivelSenha.setManaged(false);

    }

    @FXML
    void onClickVisivelReSenha(MouseEvent event) {

        if (passwordReSenha.isVisible()) {
            txtReNoSenha.setText(passwordReSenha.getText());

            passwordReSenha.setVisible(false);
            passwordReSenha.setManaged(false);
            btnVisivelReSenha.setVisible(false);
            btnVisivelReSenha.setManaged(false);

            txtReNoSenha.setVisible(true);
            txtReNoSenha.setManaged(true);
            btnNoVisivelReSenha.setVisible(true);
            btnNoVisivelReSenha.setManaged(true);

        }

    }

    @FXML
    void onClickVisivelSenha(MouseEvent event) {

        if (passwordSenha.isVisible()) {
            txtNoSenha.setText(passwordSenha.getText());

            passwordSenha.setVisible(false);
            passwordSenha.setManaged(false);
            btnVisivelSenha.setVisible(false);
            btnVisivelSenha.setManaged(false);

            txtNoSenha.setVisible(true);
            txtNoSenha.setManaged(true);
            btnNoVisivelSenha.setVisible(true);
            btnNoVisivelSenha.setManaged(true);

        }

    }

    @FXML
    void onClickcadastrar(ActionEvent event) {

        if (txtNome.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo nome não pode ficar vazio!.");
            return;
        } else if (txtLogradouro.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Logradouro não \npode ficar vazio!.");
            return;
        } else if (txtNumero.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo número não \npode ficar vazio!.");
            return;
        } else if (txtBairro.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Bairro não \npode ficar vazio!.");
            return;
        }

        else if (txtCidade.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Cidade não \npode ficar vazio!.");
            return;
        }

        else if (txtEstado.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Estado não \npode ficar vazio!.");
            return;
        }

        else if (txtCep.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo CEP não \npode ficar vazio!.");
            return;
        }

        else if (txtcel.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo Celular não \npode ficar vazio!.");
            return;
        } else if (txtSetor.getValue() == null) {
            AlertUtil.mostrarErro("O campo Setor não \npode ficar vazio! \nselecione uma opção");
            return;
        } else if (txtLogin.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo login não \npode ficar vazio!.");
            return;
        } else if (passwordSenha.getText().isEmpty()) {
            AlertUtil.mostrarErro("O campo senha não \npode ficar vazio!.");
            return;

        }

        atualizarUsuario();
        limparUsuario();

    }

    private void limparUsuario() {

        txtNome.clear();
        txtSetor.setValue(null);
        txtLogin.clear();
        passwordSenha.clear();
        passwordReSenha.clear();
        txtcel.clear();
        txtLogradouro.clear();
        txtNumero.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtCep.clear();
        txtReNoSenha.clear();
        txtNoSenha.clear();
        txtCelUsuario.clear();
    }

    @FXML
    void onclickLimpar(ActionEvent event) {
        limparUsuario();
    }

    @FXML
    void onclickBuscar(ActionEvent event) {

    }

    @FXML
    void onclickTel(MouseEvent event) {

        TextField newText = new TextField();
        newText.setMaxWidth(133);
        newText.setStyle("-fx-background-color: transparent;" + "-fx-border-color: fff;" +
                "-fx-border-radius: 7;" + "-fx-text-fill: fff;");

        campos.add(newText);

        MaskTextField.applyPhoneMask(newText);

        Image imgExcluir = new Image(getClass().getResourceAsStream("img/excluir.png"));

        ImageView img = new ImageView(imgExcluir);

        img.setStyle("-fx-cursor: hand;");
        img.setFitHeight(19);
        img.setFitWidth(19);
        img.setPickOnBounds(true);

        // Label labelRemover = new Label("Remover");
        // labelRemover.setStyle("-fx-text-fill: red;" +
        // "-fx-font-size: 14;" +
        // "-fx-font-weight: bold ;" +
        // "-fx-alignment: center;" +
        // "-fx-padding: 4 0 0 0;" +
        // "-fx-cursor: hand; ");

        HBox linha = new HBox(10, newText, img);
        linha.setAlignment(Pos.CENTER);

        vBoxTel.setMargin(linha, new Insets(0, 0, 10, -10));

        img.setOnMouseClicked(e -> {
            vBoxTel.getChildren().remove(linha);
            campos.remove(newText);
        });

        vBoxTel.getChildren().add(linha);

    }

    @FXML
    void onMouseEntered(MouseEvent event) {

        HouverEffectUtil.apllyHouverSobre(btnCadastrar);
    }

    @FXML
    void onMouseExited(MouseEvent event) {

        HouverEffectUtil.apllyHouverSair(btnCadastrar);
    }

    @FXML
    void onclickLimparExited(MouseEvent event) {

        // HouverEffectUtil.apllyHouverSair(btnLimpar);

    }

    @FXML
    void onclickLimparEntered(ActionEvent event) {

        // HouverEffectUtil.apllyHouverSobre(btnLimpar);

    }

    public void initialize() {
        Platform.runLater(() -> txtCelUsuario.requestFocus());

        // MaskTextField.validarNaoVazio(txtNome, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtLogradouro, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtLogin, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtNumero, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtBairro, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtCidade, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtEstado, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtCep, btnCadastrar);
        // MaskTextField.validarNaoVazio(txtcel, btnCadastrar);
        // MaskTextField.validarNaoVazio(passwordSenha, btnCadastrar);
        // MaskTextField.validarNaoVazio(passwordReSenha, btnCadastrar);

        // MaskTextField.limitarCaracteresFixos(txtcelClient, 15, btnCadastrarClient);
        // MaskTextField.limitarCaracteresFixos(txtCepClient, 9, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtNomeClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtLogradouroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtNumeroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtBairroClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtCidadeClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtEstadoClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtCepClient, btnCadastrarClient);
        // MaskTextField.validarNaoVazio(txtcelClient, btnCadastrarClient);

        lblClient.setMouseTransparent(true);
        lbluser.setMouseTransparent(true);
        lblFornec.setMouseTransparent(true);
        lblProduto.setMouseTransparent(true);
        lblPedido.setMouseTransparent(true);

        btnClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnClient);
        });

        btnUser.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnUser);
        });

        btnFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFornec);
        });

        btnProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnProduto);
        });

        btnClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnClient);
        });

        btnProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnProduto);
        });

        btnUser.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnUser);
        });

        btnFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnFornec);
        });

        btnLimpar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimpar);
        });

        btnLimpar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimpar);
        });

        btnCadastrarClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnCadastrarClient);
        });

        btnCadastrarClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnCadastrarClient);
        });

        btnLimparClient.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparClient);
        });

        btnLimparClient.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparClient);
        });

        btnLimparFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparFornec);
        });

        btnLimparFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparFornec);
        });

        btnCadastrarFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnCadastrarFornec);
        });

        btnCadastrarFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnCadastrarFornec);
        });

        btnBuscar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscar);
        });

        btnBuscar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscar);
        });

        btnBuscarCliente.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarCliente);
        });

        btnBuscarCliente.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarCliente);
        });

        btnBuscarFornec.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarFornec);
        });

        btnBuscarFornec.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarFornec);
        });

        btnBuscarProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarProduto);
        });

        btnBuscarProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarProduto);
        });

        btnLimparProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparProduto);
        });

        btnLimparProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparProduto);
        });

        btnAtualizarProduto.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnAtualizarProduto);
        });

        btnAtualizarProduto.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnAtualizarProduto);
        });

        btnPedido.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnPedido);
        });

        btnPedido.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnPedido);
        });
        btnLimparPedido.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnLimparPedido);
        });

        btnLimparPedido.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnLimparPedido);
        });

        btnAtualizarPedido.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnAtualizarPedido);
        });

        btnAtualizarPedido.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnAtualizarPedido);
        });

        btnBuscarPedido.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnBuscarPedido);
        });

        btnBuscarPedido.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnBuscarPedido);
        });

        MaskTextField.applyPhoneMask(txtcel);

        MaskTextField.applyCepMask(txtCep);

        MaskTextField.applyStateMask(txtEstado);

        TextFieldUtil.aplicarCapitalizacao(txtNome);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouro);
        TextFieldUtil.aplicarCapitalizacao(txtBairro);
        TextFieldUtil.aplicarCapitalizacao(txtCidade);

        MaskTextField.applyPhoneMask(txtcelClient);
        MaskTextField.applyPhoneMask(txtCelClient1);

        MaskTextField.applyCepMask(txtCepClient);

        MaskTextField.applyStateMask(txtEstadoClient);

        TextFieldUtil.aplicarCapitalizacao(txtNomeClient);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouroClient);
        TextFieldUtil.aplicarCapitalizacao(txtBairroClient);
        TextFieldUtil.aplicarCapitalizacao(txtCidadeClient);

        MaskTextField.applyPhoneMask(txtCelFornec);
        MaskTextField.applyPhoneMask(txtCelFornec1);

        MaskTextField.applyCepMask(txtCepFornec);

        MaskTextField.applyStateMask(txtEstadoFornec);

        TextFieldUtil.aplicarCapitalizacao(txtNomeFornec);
        TextFieldUtil.aplicarCapitalizacao(txtLogradouroFornec);
        TextFieldUtil.aplicarCapitalizacao(txtBairroFornec);
        TextFieldUtil.aplicarCapitalizacao(txtCidadeFornec);
        TextFieldUtil.aplicarCapitalizacao(txtProdutoFornec);
        MaskTextField.applyCnpjMask(txtCnpjFornec);
        MaskTextField.quantNumbery(txtCodigoFornec, 13);
        MaskTextField.quantNumbery(txtQuantFornec, 2);
        MaskTextField.valor(txtValorCompraFornec);
        MaskTextField.valor(txtValorVendaFornec);
        MaskTextField.valor(txtValorPedido);
        MaskTextField.limitarCaracteresFixos(txtTamanhoFornec, 2);
        MaskTextField.quantNumbery(txtNotaFiscalFornec, 10);

        MaskTextField.applyPhoneMask(txtCelUsuario);

        String nome = UserSession.getInstance().getNomeUsuario();
        String login = UserSession.getInstance().getlogin();

        txtOperador.setText("Operador: " + nome);

        passwordSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                feedBackLabel.setText("");
            } else if (ValidadorSenha.isSenhaValida(newVal)) {
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                passwordSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;");
                passwordSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            }

        });

        txtNoSenha.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                feedBackLabel.setText("");
                feedBackLabel2.setText("");
            } else if (ValidadorSenha.isSenhaValida(newVal)) {
                feedBackLabel.setText("Senha Válida :D");
                feedBackLabel2.setText("");
                feedBackLabel.setStyle("-fx-text-fill: green;");
                txtNoSenha.setStyle("-fx-border-color: fff;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");
            } else {
                feedBackLabel.setText("Senha deve ter letras, números e");
                feedBackLabel2.setText("no mínimo 8 caracteres!!");
                feedBackLabel.setStyle("-fx-text-fill: red;");
                feedBackLabel2.setStyle("-fx-text-fill: red;");
                txtNoSenha.setStyle("-fx-border-color: red;" + "-fx-background-color: transparent;"
                        + "-fx-border-radius: 7;" + "-fx-text-fill: fff");

            }

        });

        // não vendo senha
        // não vendo senha - repita vendo senha
        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        txtReNoSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(txtReNoSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (txtReNoSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

        passwordReSenha.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) {
                labelSenhaRepita.setText("");

            } else if (passwordSenha.getText().equals(passwordReSenha.getText())) {
                labelSenhaRepita.setText("A senha concidem ;D");
                labelSenhaRepita.setStyle("-fx-text-fill: green");

            } else {
                labelSenhaRepita.setText("A senha deve ser igual!");
                labelSenhaRepita.setStyle("-fx-text-fill: red;");
            }

        });

    }

}
