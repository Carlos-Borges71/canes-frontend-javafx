package com.canes.controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RelatorioController {

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Button btnFiltrar;

    @FXML
    private PieChart pieChartRelatorio;

    @FXML
    private ComboBox<String> cmbGrafico;

    @FXML
    private AreaChart<String, Number> areaChartRelatorio;

    @FXML
    private BarChart<String, Number> barChartRelatorio;

    @FXML
    private LineChart<String, Number> lineChartRelatorio;

    @FXML
    private Label txtOperador;

    @FXML
    private DatePicker dpDataInicial;

    @FXML
    private DatePicker dpDataFinal;

    @FXML
    private RadioButton rbDia;

    @FXML
    private RadioButton rbMes;

    @FXML
    private RadioButton rbAno;

    @FXML
    private ToggleGroup group;

    @FXML
    void comboGrafico(ActionEvent event) {

        String opcao = cmbGrafico.getValue();

        if (opcao == null)
            return;

        switch (opcao) {

            case "AREA":
                areaChartRelatorio.setVisible(true);
                barChartRelatorio.setVisible(false);
                lineChartRelatorio.setVisible(false);
                if (rbDia.isSelected()) {
                    carregarGraficoArea(TipoAgrupamento.DIA);
                } else if (rbMes.isSelected()) {
                    carregarGraficoArea(TipoAgrupamento.MES);
                } else {
                    carregarGraficoArea(TipoAgrupamento.ANO);
                }
                break;

            case "BARRA":
                areaChartRelatorio.setVisible(false);
                barChartRelatorio.setVisible(true);
                lineChartRelatorio.setVisible(false);
                if (rbDia.isSelected()) {
                    carregarGraficoBarraDiaMes(TipoAgrupamento.DIA);
                } else if (rbMes.isSelected()) {
                    carregarGraficoBarraDiaMes(TipoAgrupamento.MES);
                } else {
                    carregarGraficoBarraDiaMes(TipoAgrupamento.ANO);
                }
                break;

            case "LINHA":
                areaChartRelatorio.setVisible(false);
                barChartRelatorio.setVisible(false);
                lineChartRelatorio.setVisible(true);
                if (rbDia.isSelected()) {
                    carregarGraficoLinha(TipoAgrupamento.DIA);
                } else if (rbMes.isSelected()) {
                    carregarGraficoLinha(TipoAgrupamento.MES);
                } else {
                    carregarGraficoLinha(TipoAgrupamento.ANO);
                }
                break;

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

            // Agrupa e soma por tipo de pagamento e ordem decrescente
            Map<String, Double> totalPorTipo = pagamentosFiltrados.stream()
                    .collect(Collectors.groupingBy(
                            Pagamento::getTipo,
                            Collectors.summingDouble(Pagamento::getValorPagamento)))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // 🔥 decrescente
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new // 🔥 mantém a ordem
                    ));
            // Cria dados para o gráfico de pizza
            ObservableList<PieChart.Data> dados = FXCollections.observableArrayList();
            double somaTotal = totalPorTipo.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            totalPorTipo.forEach((tipo, total) -> {

                double porcentagem = (total / somaTotal) * 100;

                String nome = String.format(
                        "%s - R$ %.2f (%.1f%%)",
                        tipo,
                        total,
                        porcentagem);

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
    public void carregarGraficoArea(TipoAgrupamento tipo) {

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

            DateTimeFormatter formatter;
            Map<?, Double> totalAgrupado;

            if (tipo == TipoAgrupamento.DIA) {

                // 📅 AGRUPAR POR DIA
                formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    return dataOriginal.minusHours(3).toLocalDate();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else if (tipo == TipoAgrupamento.ANO) {

                // 📆 AGRUPAR POR ANO
                formatter = DateTimeFormatter.ofPattern("yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return dataPedido.getYear();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else {

                // 📆 AGRUPAR POR MÊS
                formatter = DateTimeFormatter.ofPattern("MM/yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return YearMonth.from(dataPedido);
                                },
                                Collectors.summingDouble(Pedido::getValor)));
            }

            if (totalAgrupado.isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            Map<?, Double> ordenado = new TreeMap<>(totalAgrupado);

            areaChartRelatorio.getData().clear();

            XYChart.Series<String, Number> serie = new XYChart.Series<>();

            double somaTotal = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            serie.setName("Total: R$ " + String.format("%.2f", somaTotal));

            ordenado.forEach((chave, total) -> {

                String label;

                if (chave instanceof LocalDate) {
                    label = ((LocalDate) chave).format(formatter);

                } else if (chave instanceof YearMonth) {
                    label = ((YearMonth) chave).format(formatter);

                } else if (chave instanceof Integer) {
                    label = String.valueOf(chave);

                } else {
                    label = chave.toString();
                }

                serie.getData().add(new XYChart.Data<>(label, total));
            });

            areaChartRelatorio.getData().add(serie);

            CategoryAxis xAxis = (CategoryAxis) areaChartRelatorio.getXAxis();
            xAxis.setStartMargin(5);
            xAxis.setEndMargin(5);
            xAxis.setGapStartAndEnd(false);

            // 🔹 Configuração eixo Y
            double max = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElse(0);

            NumberAxis yAxis = (NumberAxis) areaChartRelatorio.getYAxis();
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);
            yAxis.setUpperBound(max + (max * 0.1));
            yAxis.setTickUnit(max / 1);

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

            // Valor em cima do grafico
            // Group plotArea = (Group) lineChartRelatorio.lookup(".plot-content");

            Node plotBackground = areaChartRelatorio.lookup(".chart-plot-background");

            if (plotBackground == null)
                return;

            Parent plotArea = plotBackground.getParent();

            // remove textos antigos
            plotArea.getChildrenUnmodifiable()
                    .stream()
                    .filter(n -> n instanceof Text)
                    .toList()
                    .forEach(n -> ((Pane) plotArea).getChildren().remove(n));

            for (XYChart.Data<String, Number> data : serie.getData()) {

                Node node = data.getNode();

                if (node != null) {

                    Text text = new Text(
                            String.format("R$ %,.2f", data.getYValue().doubleValue()));

                    text.setStyle("""
                                -fx-font-size: 11px;
                                -fx-font-weight: bold;
                                -fx-fill: white;
                            """);

                    ((Pane) plotArea).getChildren().add(text);

                    node.boundsInParentProperty().addListener((obs, oldVal, bounds) -> {

                        double x = bounds.getMinX() + bounds.getWidth() / 2;
                        double y = bounds.getMinY();

                        text.setLayoutX(x + 45);
                        text.setLayoutY(y - 8);

                    });
                }
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void carregarGraficoLinha(TipoAgrupamento tipo) {

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

            DateTimeFormatter formatter;
            Map<?, Double> totalAgrupado;

            if (tipo == TipoAgrupamento.DIA) {

                formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    return dataOriginal.minusHours(3).toLocalDate();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else if (tipo == TipoAgrupamento.ANO) {

                formatter = DateTimeFormatter.ofPattern("yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return dataPedido.getYear();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else {

                formatter = DateTimeFormatter.ofPattern("MM/yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return YearMonth.from(dataPedido);
                                },
                                Collectors.summingDouble(Pedido::getValor)));
            }

            if (totalAgrupado.isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            Map<?, Double> ordenado = new TreeMap<>(totalAgrupado);

            lineChartRelatorio.getData().clear();

            XYChart.Series<String, Number> serie = new XYChart.Series<>();

            double somaTotal = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            serie.setName("Total: R$ " + String.format("%.2f", somaTotal));

            ordenado.forEach((chave, total) -> {

                String label;

                if (chave instanceof LocalDate) {
                    label = ((LocalDate) chave).format(formatter);

                } else if (chave instanceof YearMonth) {
                    label = ((YearMonth) chave).format(formatter);

                } else if (chave instanceof Integer) {
                    label = String.valueOf(chave);

                } else {
                    label = chave.toString();
                }

                serie.getData().add(new XYChart.Data<>(label, total));
            });

            lineChartRelatorio.getData().add(serie);

            CategoryAxis xAxis = (CategoryAxis) lineChartRelatorio.getXAxis();
            xAxis.setStartMargin(15);
            xAxis.setEndMargin(15);
            xAxis.setGapStartAndEnd(false);

            // lineChartRelatorio.setAnimated(false); //animação do grafio

            // 🔹 Configuração eixo Y
            double max = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElse(0);

            NumberAxis yAxis = (NumberAxis) lineChartRelatorio.getYAxis();
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);
            yAxis.setUpperBound(max + (max * 0.1));
            yAxis.setTickUnit(max / 1);

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

            // Valor em cima do grafico
            // Group plotArea = (Group) lineChartRelatorio.lookup(".plot-content");

            Node plotBackground = lineChartRelatorio.lookup(".chart-plot-background");

            if (plotBackground == null)
                return;

            Parent plotArea = plotBackground.getParent();

            // remove textos antigos
            plotArea.getChildrenUnmodifiable()
                    .stream()
                    .filter(n -> n instanceof Text)
                    .toList()
                    .forEach(n -> ((Pane) plotArea).getChildren().remove(n));

            for (XYChart.Data<String, Number> data : serie.getData()) {

                Node node = data.getNode();

                if (node != null) {

                    Text text = new Text(
                            String.format("R$ %,.2f", data.getYValue().doubleValue()));

                    text.setStyle("""
                                -fx-font-size: 11px;
                                -fx-font-weight: bold;
                                -fx-fill: white;
                            """);

                    ((Pane) plotArea).getChildren().add(text);

                    node.boundsInParentProperty().addListener((obs, oldVal, bounds) -> {

                        double x = bounds.getMinX() + bounds.getWidth() / 2;
                        double y = bounds.getMinY();

                        text.setLayoutX(x + 45);
                        text.setLayoutY(y - 8);

                    });
                }
            }
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

                        OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                        LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();

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
    public void carregarGraficoBarra() {

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

            // 🔹 Ajuste igual ao seu método anterior
            // dataFinal = dataFinal.plusDays(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            System.out.println(barChartRelatorio.getYAxis().getClass());
            // Filtra e agrupa por data
            Map<LocalDate, Double> totalPorData = pedidos.stream()
                    .filter(p -> p.getData() != null)
                    .filter(p -> {

                        // Horario original menos 3 horas p obter horario brasil
                        OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                        LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();

                        return !dataPedido.isBefore(dataInicial)
                                && !dataPedido.isAfter(dataFinal);
                    })
                    .collect(Collectors.groupingBy(
                            p -> OffsetDateTime.parse(p.getData()).toLocalDate(),
                            Collectors.summingDouble(Pedido::getValor)));

            if (totalPorData.isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            double somaTotal = totalPorData.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            Map<LocalDate, Double> ordenado = new TreeMap<>(totalPorData);

            // 🔹 Limpa gráfico
            barChartRelatorio.getData().clear();

            // 🔹 Cria série
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName("Vendas por Período - Total: R$ "
                    + String.format("%.2f", somaTotal));

            ordenado.forEach((data, total) -> {
                serie.getData().add(
                        new XYChart.Data<>(data.format(formatter), total));
            });

            barChartRelatorio.getData().add(serie);

            Platform.runLater(() -> {

                NumberAxis yAxis = (NumberAxis) barChartRelatorio.getYAxis();

                yAxis.setAutoRanging(false);
                yAxis.setForceZeroInRange(true);

                double max = ordenado.values()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .max()
                        .orElse(0);

                double upperBound = max == 0 ? 10 : max * 1.1;

                yAxis.setLowerBound(0);
                yAxis.setUpperBound(upperBound);
                yAxis.setTickUnit(upperBound / 1);
                System.out.println(barChartRelatorio.getYAxis().getClass());
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                yAxis.setTickLabelFormatter(new StringConverter<Number>() {

                    @Override
                    public String toString(Number number) {
                        return currencyFormat.format(number.doubleValue());
                    }

                    @Override
                    public Number fromString(String string) {
                        return 0;
                    }
                });

            });

            Platform.runLater(() -> {

                String[] cores = {
                        "#4CAF50", // verde
                        "#2196F3", // azul
                        "#FF9800", // laranja
                        "#E91E63", // rosa
                        "#9C27B0", // roxo
                        "#F44336" // vermelho
                };

                int i = 0;

                for (XYChart.Data<String, Number> data : serie.getData()) {

                    String cor = cores[i % cores.length];

                    data.getNode().setStyle("-fx-bar-fill: " + cor + ";");

                    i++;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum TipoAgrupamento {
        DIA,
        MES,
        ANO
    }

    @FXML
    public void carregarGraficoBarraDiaMes(TipoAgrupamento tipo) {

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

            // 🔥 Variáveis dinâmicas
            DateTimeFormatter formatter;
            Map<?, Double> totalAgrupado;

            if (tipo == TipoAgrupamento.DIA) {

                // 📅 AGRUPAR POR DIA
                formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    return dataOriginal.minusHours(3).toLocalDate();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else if (tipo == TipoAgrupamento.ANO) {

                // 📆 AGRUPAR POR ANO
                formatter = DateTimeFormatter.ofPattern("yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return dataPedido.getYear();
                                },
                                Collectors.summingDouble(Pedido::getValor)));

            } else {

                // 📆 AGRUPAR POR MÊS
                formatter = DateTimeFormatter.ofPattern("MM/yyyy");

                totalAgrupado = pedidos.stream()
                        .filter(p -> p.getData() != null)
                        .filter(p -> {
                            OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                            LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                            return !dataPedido.isBefore(dataInicial)
                                    && !dataPedido.isAfter(dataFinal);
                        })
                        .collect(Collectors.groupingBy(
                                p -> {
                                    OffsetDateTime dataOriginal = OffsetDateTime.parse(p.getData());
                                    LocalDate dataPedido = dataOriginal.minusHours(3).toLocalDate();
                                    return YearMonth.from(dataPedido);
                                },
                                Collectors.summingDouble(Pedido::getValor)));
            }

            if (totalAgrupado.isEmpty()) {
                System.out.println("Nenhum pedido encontrado no período.");
                return;
            }

            Map<?, Double> ordenado = new TreeMap<>(totalAgrupado);

            barChartRelatorio.getData().clear();

            XYChart.Series<String, Number> serie = new XYChart.Series<>();

            Platform.runLater(() -> {

                NumberAxis yAxis = (NumberAxis) barChartRelatorio.getYAxis();

                yAxis.setAutoRanging(false);
                yAxis.setForceZeroInRange(true);

                double max = ordenado.values()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .max()
                        .orElse(0);

                double upperBound = max == 0 ? 10 : max * 1.1;

                yAxis.setLowerBound(0);
                yAxis.setUpperBound(upperBound);
                yAxis.setTickUnit(upperBound / 1);

                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                yAxis.setTickLabelFormatter(new StringConverter<Number>() {

                    @Override
                    public String toString(Number number) {
                        return currencyFormat.format(number.doubleValue());
                    }

                    @Override
                    public Number fromString(String string) {
                        return 0;
                    }
                });

                for (XYChart.Data<String, Number> data : serie.getData()) {

                    Node node = data.getNode();

                    if (node != null) {

                        Text text = new Text(
                                String.format("R$ %,.2f", data.getYValue().doubleValue()));

                        text.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-fill: white");

                        StackPane parent = (StackPane) node;

                        parent.getChildren().add(text);

                        StackPane.setAlignment(text, Pos.TOP_CENTER);
                        text.setTranslateY(-15); // sobe o texto
                    }
                }
            });

            double somaTotal = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            serie.setName("Total: R$ " + String.format("%.2f", somaTotal));

            ordenado.forEach((chave, total) -> {

                String label;

                if (chave instanceof LocalDate) {
                    label = ((LocalDate) chave).format(formatter);

                } else if (chave instanceof YearMonth) {
                    label = ((YearMonth) chave).format(formatter);

                } else if (chave instanceof Integer) {
                    label = String.valueOf(chave);

                } else {
                    label = chave.toString();
                }

                serie.getData().add(new XYChart.Data<>(label, total));
            });

            barChartRelatorio.getData().add(serie);

            Platform.runLater(() -> {

                String[] cores = {
                        "#4CAF50", // verde
                        "#2196F3", // azul
                        "#FF9800", // laranja
                        "#E91E63", // rosa
                        "#9C27B0", // roxo
                        "#F44336" // vermelho
                };

                int i = 0;

                for (XYChart.Data<String, Number> data : serie.getData()) {

                    String cor = cores[i % cores.length];

                    data.getNode().setStyle("-fx-bar-fill: " + cor + ";");

                    i++;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onFiltrarClick(ActionEvent event) {

        carregarGraficoPagamento();
        carregarGraficoArea(TipoAgrupamento.DIA);
        group.selectToggle(rbDia);
        carregarGraficoBarraDiaMes(TipoAgrupamento.DIA);
        carregarGraficoLinha(TipoAgrupamento.DIA);

    }

    @FXML
    public void initialize() {

        LocalDate hoje = LocalDate.now();

        dpDataFinal.setValue(hoje);
        dpDataInicial.setValue(hoje.minusDays(30));

        Platform.runLater(() -> {

            carregarGraficoPagamento();
            carregarGraficoArea(TipoAgrupamento.DIA);

        });

        btnFiltrar.setOnMouseEntered(e -> {
            HouverEffectUtil.apllyHouverSobre(btnFiltrar);
        });

        btnFiltrar.setOnMouseExited(e -> {
            HouverEffectUtil.apllyHouverSair(btnFiltrar);
        });

        cmbGrafico.getSelectionModel().select("AREA");

        cmbGrafico.getItems().addAll(
                "AREA",
                "BARRA",
                "LINHA");

        // já deixa Dia selecionado
        rbDia.setSelected(true);

        // Listener do grupo (melhor forma)
        group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            if (newToggle == null)
                return;

            RadioButton selecionado = (RadioButton) newToggle;

            if (selecionado == rbDia) {
                carregarGraficoBarraDiaMes(TipoAgrupamento.DIA);
                carregarGraficoArea(TipoAgrupamento.DIA);
                carregarGraficoLinha(TipoAgrupamento.DIA);
            } else if (selecionado == rbMes) {
                carregarGraficoBarraDiaMes(TipoAgrupamento.MES);
                carregarGraficoArea(TipoAgrupamento.MES);
                carregarGraficoLinha(TipoAgrupamento.MES);

            } else if (selecionado == rbAno) {
                carregarGraficoBarraDiaMes(TipoAgrupamento.ANO);
                carregarGraficoArea(TipoAgrupamento.ANO);
                carregarGraficoLinha(TipoAgrupamento.ANO);
            }

        });
    }

}