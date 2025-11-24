package fatec.lanchoneteapp.adapters.ui.cliente;

import fatec.lanchoneteapp.application.dto.ClienteDTO;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ClienteFormController {

    private CadastroFacade cadastroFacade;

    @FXML private Button btnVoltarCliente;

    @FXML private TextField tfNomeCliente;
    @FXML private TextField tfTelefoneCliente;
    @FXML private TextField tfCPFCliente;
    @FXML private TextField tfLogradouroCliente;
    @FXML private TextField tfNumeroCliente;
    @FXML private TextField tfCEPCliente;
    @FXML private TextField tfComplementoCliente;

    @FXML
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarCliente.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSalvarClick() {
        ClienteDTO clienteDTO = new ClienteDTO(
            0,
            tfNomeCliente.getText(),
            tfTelefoneCliente.getText(),
            tfCPFCliente.getText(),
            tfLogradouroCliente.getText(),
            Integer.parseInt(tfNumeroCliente.getText()),
            tfCEPCliente.getText(),
            tfComplementoCliente.getText()
        );

        try {
            cadastroFacade.novoCliente(clienteDTO);
        } catch (ClienteInvalidoException e) {
            criarErrorAlert("Cliente inválido!", e.getMessage());
        } catch (SQLException sql) {
            criarErrorAlert("Ocorreu um erro", sql.getMessage());
        }
    }

    private void criarErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public void setCadastroFacade(CadastroFacade cadastroFacade){
        this.cadastroFacade = cadastroFacade;
    }
}
