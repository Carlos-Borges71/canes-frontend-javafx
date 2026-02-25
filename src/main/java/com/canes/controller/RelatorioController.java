package com.canes.controller;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.canes.factory.PagamentoFactory;
import com.canes.factory.PedidoFactory;
import com.canes.model.Pagamento;
import com.canes.model.Pedido;
import com.canes.services.PagamentoService;
import com.canes.services.PedidoService;
import com.canes.util.ScreenUtils;
import com.canes.util.AlertUtil;
import com.canes.util.HouverEffectUtil;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RelatorioController {

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Button btnFiltrar;

    @FXML
    private PieChart pieChartRelatorio;

    @FXML
    private AreaChart<String, Number> areaChartRelatorio;

    @FXML
    private Label txtOperador;

    @FXML
    private DatePicker dpDataInicial;

    @FXML
    private DatePicker dpDataFinal;

    @FXML
    void onActionVoltar(MouseEvent event) {

        try {
            ScreenUtils.changeScreenMouse(event, "/com/canes/view/menu.fxml", "Menu", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregarGraficoPagamento() {
        try {
            PagamentoService pagamentoService = PagamentoFactory.getPagamentoService();
            List<Pagamento> pagamentos = pagamentoService.buscarTodos();

            LocalDate dataInicial = dpDataInicial.getValue();
            LocalDate dataFinal = dpDataFinal.getValue();

            if (dataInicial == null || dataFinal == null) {
                AlertUtil.mostrarErro("Selecione as duas datas!");
                return;
            }

            if (dataInicial.isAfter(dataFinal)) {
                AlertUtil.mostrarErro("Data inicial maior que final!");
                return;
            }

            // Filtra pedidos dentro do período
            List<Pagamento> pagamentosFiltrados = pagamentos.stream()
                    .filter(p -> p.getData() != null)
                    .filter(p -> {
                        LocalDate dataPedido = OffsetDateTime.parse(p.getData())
                                .minusHours(3) // ajusta fuso, se necessário
                                .toLocalDate();
                        return !dataPedido.isBefore(dataInicial) && !dataPedido.isAfter(dataFinal);
                    })
                    .toList();

            // Agrupa e soma por tipo de pagamento
            Map<String, Double> totalPorTipo = pagamentosFiltrados.stream()
                    .collect(Collectors.groupingBy(
                            Pagamento::getTipo, // "Pix", "Dinheiro", etc.
                            Collectors.summingDouble(Pagamento::getValorPagamento)));

            // Cria dados para o gráfico de pizza
            ObservableList<PieChart.Data> dados = FXCollections.observableArrayList();
            totalPorTipo.forEach((tipo, total) -> {
                String nome = tipo + " - R$ " + String.format("%.2f", total);
                dados.add(new PieChart.Data(nome, total));
            });

            if (dados.isEmpty()) {
                AlertUtil.mostrarErro("Nenhum pedido encontrado no período.");
            } else {
                pieChartRelatorio.setData(dados);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void carregarGraficoArea() {

        try {
            PedidoService pedidoService = PedidoFactory.getPedidoService();
            List<Pedido> pedidos = pedidoService.buscarTodos();

            LocalDate dataInicial = dpDataInicial.getValue();
            LocalDate dataFinal = dpDataFinal.getValue().plusDays(1);

            if (dataInicial == null || dataFinal == null) {
                System.out.println("Selecione as duas datas!");
                return;
            }

            if (dataInicial.isAfter(dataFinal)) {
                System.out.println("Data inicial maior que final!");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            // Filtra e agrupa por data, somando valores
            Map<LocalDate, Double> totalPorData = pedidos.stream()
                    .filter(p -> p.getData() != null)
                    .filter(p -> {
                        LocalDate dataPedido = OffsetDateTime.parse(p.getData()).toLocalDate();
                        return !dataPedido.isBefore(dataInicial) && !dataPedido.isAfter(dataFinal);
                    })
                    .collect(Collectors.groupingBy(
                            p -> OffsetDateTime.parse(p.getData()).toLocalDate(),
                            Collectors.summingDouble(Pedido::getValor)));

            if (totalPorData.isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            // Calcula a soma total
            double somaTotal = totalPorData.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            // Ordena por data
            Map<LocalDate, Double> ordenado = new TreeMap<>(totalPorData);

            // Limpa gráfico antes de adicionar novos dados
            areaChartRelatorio.getData().clear();

            // Cria série com o nome incluindo a soma total
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName("Vendas por Período - Total: R$ " + String.format("%.2f", somaTotal));

            ordenado.forEach((data, total) -> {
                serie.getData().add(new XYChart.Data<>(data.format(formatter), total));
            });

            areaChartRelatorio.getData().add(serie);

            // Configura eixo Y em reais
            double max = ordenado.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
            NumberAxis yAxis = (NumberAxis) areaChartRelatorio.getYAxis();
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);
            yAxis.setUpperBound(max + (max * 0.1)); // margem 10%
            yAxis.setTickUnit(max / 5); // 5 marcações

            yAxis.setTickLabelFormatter(new StringConverter<Number>() {
                private final NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                @Override
                public String toString(Number number) {
                    return format.format(number.doubleValue());
                }

                @Override
                public Number fromString(String string) {
                    return 0;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void carregarGrafico2() {
        try {

            Platform.runLater(() -> {

                // NumberAxis yAxis = (NumberAxis) areaChartRelatorio.getYAxis();
                // yAxis.setTickUnit(5000);

                // // pieChartRelatorio.getData().get(0).getNode().setStyle("-fx-pie-color:
                // // #cc7a2e;");
                // // pieChartRelatorio.getData().get(1).getNode().setStyle("-fx-pie-color:
                // // #e74c3c;");
                // pieChartRelatorio.setTitle("Relatório por período");

                // Node title = pieChartRelatorio.lookup(".chart-title");
                // if (title != null) {
                // title.setStyle("-fx-text-fill: #f0f3f5; -fx-font-size: 40px;");
                // }

                // // pieChartRelatorio.lookupAll(".chart-legend-item")
                // // .forEach(node -> node.setStyle("-fx-text-fill: red; -fx-font-weight:
                // // bold;"));

                // pieChartRelatorio.lookupAll(".chart-pie-label")
                // .forEach(node -> node.setStyle("-fx-fill: #ffffff; -fx-font-size: 16px;"));

            });

            PedidoService pedidoService = PedidoFactory.getPedidoService();
            List<Pedido> pedidos = pedidoService.buscarTodos();

            LocalDate dataInicial = dpDataInicial.getValue();
            LocalDate dataFinal = dpDataFinal.getValue();
            System.out.println("Datafinal : " + dataFinal);

            for (Pedido p : pedidos) {

                OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();

                boolean dentro = !dataPedido.isBefore(dataInicial) &&
                        !dataPedido.isAfter(dataFinal);

                System.out.println(
                        "Pedido: " + dataPedido +
                                " | Dentro do período? " + dentro +
                                " | Valor: " + p.getValor());
            }

            if (dataInicial == null || dataFinal == null) {
                AlertUtil.mostrarErro("Selecione as duas datas!");
                return;
            }

            if (dataInicial.isAfter(dataFinal)) {
                AlertUtil.mostrarErro("Data inicial maior que final!");
                return;
            }

            DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Map<LocalDate, Double> totalPorData = pedidos.stream()
                    .filter(p -> p.getData() != null)
                    .filter(p -> {
                        LocalDate dataPedido = OffsetDateTime.parse(p.getData()).toLocalDate();
                        return !dataPedido.isBefore(dataInicial) &&
                                !dataPedido.isAfter(dataFinal);
                    })
                    .collect(Collectors.groupingBy(
                            p -> OffsetDateTime.parse(p.getData()).toLocalDate(),
                            Collectors.summingDouble(Pedido::getValor)));

            ObservableList<PieChart.Data> dados = FXCollections.observableArrayList();

            totalPorData.forEach((data, total) -> dados.add(new PieChart.Data(
                    data.format(formatterSaida) + " - R$ " + String.format("%.2f", total),
                    total)));

            if (dados.isEmpty()) {
                AlertUtil.mostrarErro("Nenhum pedido encontrado no período.");
            }

            pieChartRelatorio.setData(dados);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onFiltrarClick(ActionEvent event) {

        carregarGraficoPagamento();
        carregarGraficoArea();
    }

    @FXML
    public void initialize() {

        LocalDate hoje = LocalDate.now();

        dpDataFinal.setValue(hoje);
        dpDataInicial.setValue(hoje.minusDays(30));

        Platform.runLater(() -> {

            carregarGraficoPagamento();
            carregarGraficoArea();

        });

        btnFiltrar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFiltrar);
        });

        btnFiltrar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnFiltrar);
        });

        // ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
        // new PieChart.Data("Vendas", 40),
        // new PieChart.Data("Pagamentos", 25),
        // new PieChart.Data("Pedidos", 35));

        // pieChartRelatorio.setData(dados);
        // pieChartRelatorio.setTitle("Relatório Geral");
    }

}