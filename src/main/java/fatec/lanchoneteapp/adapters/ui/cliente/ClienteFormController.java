package fatec.lanchoneteapp.adapters.ui.cliente;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.ClienteDTO;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ClienteFormController extends Controller implements IFormController<ClienteDTO>{

    private CadastroFacade cadastroFacade;

    @FXML private Button btnVoltarCliente;

    private int idCliente;
    @FXML private TextField tfNomeCliente;
    @FXML private TextField tfTelefoneCliente;
    @FXML private TextField tfCPFCliente;
    @FXML private TextField tfLogradouroCliente;
    @FXML private TextField tfNumeroCliente;
    @FXML private TextField tfCEPCliente;
    @FXML private TextField tfComplementoCliente;

    @FXML
    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarCliente.getScene().getWindow();
        stage.close();
    }

    @FXML
    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;

        if(idCliente > 0){
            ClienteDTO clienteDTO = new ClienteDTO(
                    idCliente,
                    tfNomeCliente.getText(),
                    tfTelefoneCliente.getText(),
                    tfCPFCliente.getText(),
                    tfLogradouroCliente.getText(),
                    Integer.parseInt(tfNumeroCliente.getText()),
                    tfCEPCliente.getText(),
                    tfComplementoCliente.getText()
            );

            try {
                cadastroFacade.atualizarCliente(clienteDTO);

                criarInfoAlert("Sucesso!", "Cliente atualizado com sucesso.");
                onVoltarClick();
            } catch (ClienteInvalidoException e) {
                criarErrorAlert("Cliente inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
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

                criarInfoAlert("Sucesso!", "Cliente inserido com sucesso");
                onVoltarClick();
            } catch (ClienteInvalidoException e) {
                criarErrorAlert("Cliente inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(ClienteDTO cliente) {
        this.idCliente = cliente.id();
        tfNomeCliente.setText(cliente.getNome());
        tfTelefoneCliente.setText(cliente.getTel());
        tfCPFCliente.setText(cliente.getCpf());
        tfLogradouroCliente.setText(cliente.getLogradouro());
        tfNumeroCliente.setText(String.valueOf(cliente.getNumero()));
        tfCEPCliente.setText(cliente.getCep());
        tfComplementoCliente.setText(cliente.getComplemento());
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(tfCEPCliente.getText().isEmpty())
            message = "O Campo 'CEP' é obrigatório";
        if(tfNumeroCliente.getText().isEmpty())
            message = "O Campo 'Número' é obrigatório";
        if(tfLogradouroCliente.getText().isEmpty())
            message = "O Campo 'Logradouro' é obrigatório";
        if(tfCPFCliente.getText().isEmpty())
            message = "O Campo 'CPF' é obrigatório";
        if(tfTelefoneCliente.getText().isEmpty())
            message = "O Campo 'Telefone' é obrigatório";
        if(tfNomeCliente.getText().isEmpty())
            message = "O Campo 'Nome' é obrigatório";

        if(message.isEmpty())
            return true;
        else {
            criarErrorAlert("Cadastro inválido!", message);
            return false;
        }
    }

    public void setCadastroFacade(CadastroFacade cadastroFacade){
        this.cadastroFacade = cadastroFacade;
    }
}
