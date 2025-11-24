package fatec.lanchoneteapp.adapters.ui.cargo;

import java.sql.SQLException;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.CargoDTO;
import fatec.lanchoneteapp.application.exception.CargoInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CargoFormController extends Controller implements IFormController<CargoDTO>  {
    
    private CadastroFacade cadastroFacade;

    //Botões
    @FXML private Button btnVoltarCargo;

    //Campos
    private int idCargo;
    @FXML private TextField tfNomeCargo;
    @FXML private TextField tfSalarioCargo;
    @FXML private TextField tfDescricaoCargo;

    @FXML
    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarCargo.getScene().getWindow();
        stage.close();
    }

    @FXML
    @Override
    public void onSalvarClick() {
        if(idCargo > 0) {
            // Atualizar cargo existente
            CargoDTO cargoDTO = new CargoDTO(
                idCargo,
                tfNomeCargo.getText(),
                Double.parseDouble(tfSalarioCargo.getText()),
                tfDescricaoCargo.getText()
            );

            try {
                cadastroFacade.atualizarCargo(cargoDTO);
                criarInfoAlert("Sucesso!", "Cargo atualizado com sucesso");
                onVoltarClick();
            } catch (CargoInvalidoException e) {
                criarErrorAlert("Cargo inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
            // Criar novo cargo
            CargoDTO cargoDTO = new CargoDTO(
                0,
                tfNomeCargo.getText(),
                Double.parseDouble(tfSalarioCargo.getText()),
                tfDescricaoCargo.getText()
            );

            try {
                cadastroFacade.novoCargo(cargoDTO);
                criarInfoAlert("Sucesso!", "Cargo inserido com sucesso");
                onVoltarClick();
            } catch (CargoInvalidoException e) {
                criarErrorAlert("Cargo inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    public void setCadastroFacade(CadastroFacade cadastroFacade) {
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void setCampos(CargoDTO cargo) {
        this.idCargo = cargo.id();
        tfNomeCargo.setText(cargo.nome());
        tfSalarioCargo.setText(String.valueOf(cargo.salario()));
        tfDescricaoCargo.setText(cargo.descricao());
    }
}
