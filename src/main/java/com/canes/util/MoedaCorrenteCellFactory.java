package com.canes.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaCorrenteCellFactory {


    // Formato de moeda brasileira
    private static final NumberFormat BRL =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    /**
     * Aplica formatação de moeda (R$) a uma coluna Double.
     * Exemplo de uso:
     *   colPreco.setCellFactory(CurrencyTableCellFactory.forTableColumn());
     */
    public static <T> Callback<TableColumn<T, Double>, TableCell<T, Double>> forTableColumn() {
        return column -> new TableCell<T, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(BRL.format(value));
                }
            }
        };
    }
}

