package com.canes.controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.canes.factory.PedidoFactory;
import com.canes.model.Pedido;
import com.canes.model.dpo.PedidoDPO;
import com.canes.services.PedidoService;
import com.canes.util.ScreenUtils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RelatorioController {

    @FXML
    private ImageView btnVoltar;

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

    @FXML
    public void carregarGrafico() {
        try {
            PedidoService pedidoService = PedidoFactory.getPedidoService();

            List<Pedido> pedidos = pedidoService.buscarTodos();

            long pagos = pedidos.stream()
                    .filter(p -> p.getStatus().equals("PAGO"))
                    .count();

            long pendentes = pedidos.stream()
                    .filter(p -> p.getStatus().equals("AGUARDANDO_PAGAMENTO"))
                    .count();

            ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
                    new PieChart.Data("Pagos", pagos),
                    new PieChart.Data("Pendentes", pendentes));

            pieChartRelatorio.setLabelsVisible(true);

            pieChartRelatorio.setData(dados);
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
            LocalDate dataFinal = dpDataFinal.getValue();

            if (dataInicial == null || dataFinal == null) {
                System.out.println("Selecione as duas datas!");
                return;
            }

            if (dataInicial.isAfter(dataFinal)) {
                System.out.println("Data inicial maior que final!");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            // Agrupa por data e soma os valores
            Map<LocalDate, Double> totalPorData = pedidos.stream()
                    .filter(p -> p.getData() != null)
                    .filter(p -> {
                        LocalDate dataPedido = OffsetDateTime.parse(p.getData()).toLocalDate();

                        return !dataPedido.isBefore(dataInicial)
                                && !dataPedido.isAfter(dataFinal);
                    })
                    .collect(Collectors.groupingBy(
                            p -> OffsetDateTime.parse(p.getData()).toLocalDate(),
                            Collectors.summingDouble(Pedido::getValor)));

            // Ordena por data
            Map<LocalDate, Double> ordenado = new TreeMap<>(totalPorData);

            // Limpa gráfico antes de adicionar novos dados
            areaChartRelatorio.getData().clear();

            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName("Vendas por Período");

            ordenado.forEach((data, total) -> {
                serie.getData().add(
                        new XYChart.Data<>(
                                data.format(formatter),
                                total));
            });

            if (serie.getData().isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            areaChartRelatorio.getData().add(serie);

            double max = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElse(0);

            NumberAxis yAxis = (NumberAxis) areaChartRelatorio.getYAxis();

            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);
            yAxis.setUpperBound(max + (max * 0.1)); // 10% margem
            yAxis.setTickUnit(max / 5); // só 5 marcações

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

            for (Pedido p : pedidos) {

                LocalDate dataPedido = OffsetDateTime.parse(p.getData()).toLocalDate();

                boolean dentro = !dataPedido.isBefore(dataInicial) &&
                        !dataPedido.isAfter(dataFinal);

                System.out.println(
                        "Pedido: " + dataPedido +
                                " | Dentro do período? " + dentro +
                                " | Valor: " + p.getValor());
            }

            if (dataInicial == null || dataFinal == null) {
                System.out.println("Selecione as duas datas!");
                return;
            }

            if (dataInicial.isAfter(dataFinal)) {
                System.out.println("Data inicial maior que final!");
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
                System.out.println("Nenhum pedido encontrado no período.");
            }

            pieChartRelatorio.setData(dados);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onFiltrarClick(ActionEvent event) {

        carregarGrafico2();
        carregarGraficoArea();
    }

    @FXML
    public void initialize() {

        LocalDate hoje = LocalDate.now();

        dpDataFinal.setValue(hoje);
        dpDataInicial.setValue(hoje.minusDays(30));

        // ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
        // new PieChart.Data("Vendas", 40),
        // new PieChart.Data("Pagamentos", 25),
        // new PieChart.Data("Pedidos", 35));

        // pieChartRelatorio.setData(dados);
        // pieChartRelatorio.setTitle("Relatório Geral");
    }

}