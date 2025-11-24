package fatec.lanchoneteapp.adapters.ui.controller;

import javafx.scene.control.Alert;

public abstract class Controller {
    protected void criarErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    protected void criarInfoAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("INFO");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    protected void criarWarningAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
