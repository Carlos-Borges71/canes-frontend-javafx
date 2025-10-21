package com.canes.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class FXLoaderUtil {

    /**
     * Carrega um FXML e retorna seu nó raiz.
     * Ideal quando você quer carregar a tela inteira.
     */
    public static Parent loadRoot(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(FXLoaderUtil.class.getResource(caminhoFXML));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Carrega um FXML e retorna um nó interno específico (como uma Pane)
     * mantendo o controller original funcional.
     * 
     * @param caminhoFXML caminho do arquivo FXML (ex: "/view/TelaB.fxml")
     * @param nomeDoNode  fx:id do node interno que deseja obter (ex:
     *                    "paneReutilizavel")
     * @return o Node (Pane, VBox, AnchorPane, etc.) correspondente
     */
    public static Node loadInnerPane(String caminhoFXML, String nomeDoNode) {
        try {
            FXMLLoader loader = new FXMLLoader(FXLoaderUtil.class.getResource(caminhoFXML));
            Parent root = loader.load();

            // Pega o controller para acessar o node
            Object controller = loader.getController();
            Node innerNode = (Node) controller.getClass()
                    .getDeclaredField(nomeDoNode)
                    .get(controller);

            return innerNode;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
