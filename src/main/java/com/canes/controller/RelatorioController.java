package com.canes.controller;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.canes.controller.RelatorioController.TipoAgrupamento;
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
import javafx.scene.chart.BarChart;
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
                if (rbDia.isSelected()) {
                    carregarGraficoArea(TipoAgrupamento.DIA);
                } else {
                    carregarGraficoArea(TipoAgrupamento.MES);
                }
                break;

            case "BARRA":
                areaChartRelatorio.setVisible(false);
                barChartRelatorio.setVisible(true);
                if (rbDia.isSelected()) {
                    carregarGraficoBarraDiaMes(TipoAgrupamento.DIA);
                } else {
                    carregarGraficoBarraDiaMes(TipoAgrupamento.MES);
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
                } else {
                    label = ((YearMonth) chave).format(formatter);
                }

                serie.getData().add(new XYChart.Data<>(label, total));
            });

            areaChartRelatorio.getData().add(serie);

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
            yAxis.setTickUnit(max / 5);

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

            // 🔹 Configuração eixo Y
            double max = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElse(0);

            NumberAxis yAxis = (NumberAxis) barChartRelatorio.getYAxis();
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(0);
            yAxis.setUpperBound(max + (max * 0.1));
            yAxis.setTickUnit(max / 5);

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
        MES
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

            double somaTotal = ordenado.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            serie.setName("Total: R$ " + String.format("%.2f", somaTotal));

            ordenado.forEach((chave, total) -> {

                String label;

                if (chave instanceof LocalDate) {
                    label = ((LocalDate) chave).format(formatter);
                } else {
                    label = ((YearMonth) chave).format(formatter);
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

        cmbGrafico.getItems().addAll(
                "BARRA",
                "AREA");

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
            } else if (selecionado == rbMes) {
                carregarGraficoBarraDiaMes(TipoAgrupamento.MES);
                carregarGraficoArea(TipoAgrupamento.MES);

            }
        });
    }

}