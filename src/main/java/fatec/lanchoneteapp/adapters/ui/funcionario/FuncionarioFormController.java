package fatec.lanchoneteapp.adapters.ui.funcionario;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.CargoDTO;
import fatec.lanchoneteapp.application.dto.FuncionarioDTO;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.mapper.CargoMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FuncionarioFormController extends Controller implements IFormController<FuncionarioDTO> {

    private CadastroFacade cadastroFacade;
    private final CargoMapper cargoMapper = new CargoMapper();

    @FXML private Button btnVoltarFuncionario;

    private int idFuncionario;
    @FXML private TextField tfNomeFuncionario;
    @FXML private TextField tfTelefoneFuncionario;
    @FXML private TextField tfEmailFuncionario;
    @FXML private TextField tfDataContratoFuncionario;
    @FXML private ComboBox<CargoDTO> cbCargoFuncionario;

    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarFuncionario.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;

        if(idFuncionario > 0){
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(
                    idFuncionario,
                    tfNomeFuncionario.getText(),
                    tfTelefoneFuncionario.getText(),
                    tfEmailFuncionario.getText(),
                    LocalDate.parse(tfDataContratoFuncionario.getText()),
                    cargoMapper.toEntity(cbCargoFuncionario.getValue())
            );

            try {
                cadastroFacade.atualizarFuncionario(funcionarioDTO);

                criarInfoAlert("Sucesso!", "Funcionário atualizado com sucesso.");
                onVoltarClick();
            } catch (ClienteInvalidoException e) {
                criarErrorAlert("Funcionário inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(
                    0,
                    tfNomeFuncionario.getText(),
                    tfTelefoneFuncionario.getText(),
                    tfEmailFuncionario.getText(),
                    LocalDate.parse(tfDataContratoFuncionario.getText()),
                    cargoMapper.toEntity(cbCargoFuncionario.getValue())
            );

            try {
                cadastroFacade.novoFuncionario(funcionarioDTO);

                criarInfoAlert("Sucesso!", "Funcionário inserido com sucesso");
                onVoltarClick();
            } catch (ClienteInvalidoException e) {
                criarErrorAlert("Funcionário inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(FuncionarioDTO funcionario) {
        this.idFuncionario = funcionario.id();
        tfNomeFuncionario.setText(funcionario.getNome());
        tfTelefoneFuncionario.setText(funcionario.getTel());
        tfEmailFuncionario.setText(funcionario.getEmail());
        tfDataContratoFuncionario.setText(String.valueOf(funcionario.getDataContrato()));
        cbCargoFuncionario.setValue(funcionario.getCargoDTO());
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(cbCargoFuncionario.getSelectionModel().getSelectedItem() == null)
            message = "Selecione um 'Cargo'";
        if(tfDataContratoFuncionario.getText().isEmpty())
            message = "O Campo 'Data do Contrato' é obrigatório";
        if(tfEmailFuncionario.getText().isEmpty())
            message = "O Campo 'Email' é obrigatório";
        if(tfTelefoneFuncionario.getText().isEmpty())
            message = "O Campo 'Telefone' é obrigatório";
        if(tfNomeFuncionario.getText().isEmpty())
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
        carregarCargos();
    }

    private void carregarCargos() {
        List<CargoDTO> cargos = null;
        try {
            cargos = cadastroFacade.listarCargos();

            cbCargoFuncionario.getItems().clear();
            cbCargoFuncionario.getItems().addAll(cargos);

            cbCargoFuncionario.setCellFactory(listViwe -> new ListCell<>() {
                @Override
                protected void updateItem(CargoDTO cargo, boolean empty) {
                    super.updateItem(cargo, empty);
                    setText(empty || cargo == null ? "" : cargo.getNome());
                }
            });

            cbCargoFuncionario.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(CargoDTO cargo, boolean empty) {
                    super.updateItem(cargo, empty);
                    setText(empty || cargo == null ? "" : cargo.getNome());
                }
            });

        } catch (SQLException e) {
            criarErrorAlert("Erro", "Não foi possível carregar os cargos:\n" + e.getMessage());
        }


    }
}
