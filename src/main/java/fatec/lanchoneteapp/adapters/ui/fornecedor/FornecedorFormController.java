package fatec.lanchoneteapp.adapters.ui.fornecedor;

import java.sql.SQLException;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.FornecedorDTO;
import fatec.lanchoneteapp.application.exception.FornecedorInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FornecedorFormController extends Controller implements IFormController<FornecedorDTO> {

    private CadastroFacade cadastroFacade;

    //Botões
    @FXML private Button btnVoltarFornecedor;

    private int idFornecedor;
    @FXML private TextField tfNomeFornecedor;
    @FXML private TextField tfTelefoneFornecedor;
    @FXML private TextField tfCNPJFornecedor;
    @FXML private TextField tfLogradouroFornecedor;
    @FXML private TextField tfNumeroFornecedor;
    @FXML private TextField tfCEPFornecedor;
    @FXML private TextField tfComplementoFornecedor;

    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarFornecedor.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
       if(!validarCampos())
            return;

        if(idFornecedor > 0){
            FornecedorDTO fornecedorDTO = new FornecedorDTO(
                    idFornecedor,
                    tfNomeFornecedor.getText(),
                    tfTelefoneFornecedor.getText(),
                    tfCNPJFornecedor.getText(),
                    tfLogradouroFornecedor.getText(),
                    Integer.parseInt(tfNumeroFornecedor.getText()),
                    tfCEPFornecedor.getText(),
                    tfComplementoFornecedor.getText()
            );

            try {
                cadastroFacade.atualizarFornecedor(fornecedorDTO);

                criarInfoAlert("Sucesso!", "Fornecedor atualizado com sucesso.");
                onVoltarClick();
            } catch (FornecedorInvalidoException e) {
                criarErrorAlert("Fornecedor inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
            FornecedorDTO fornecedorDTO = new FornecedorDTO(
                    0,
                    tfNomeFornecedor.getText(),
                    tfTelefoneFornecedor.getText(),
                    tfCNPJFornecedor.getText(),
                    tfLogradouroFornecedor.getText(),
                    Integer.parseInt(tfNumeroFornecedor.getText()),
                    tfCEPFornecedor.getText(),
                    tfComplementoFornecedor.getText()
            );

            try {
                cadastroFacade.novoFornecedor(fornecedorDTO);

                criarInfoAlert("Sucesso!", "Fornecedor inserido com sucesso");
                onVoltarClick();
            } catch (FornecedorInvalidoException e) {
                criarErrorAlert("Fornecedor inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(FornecedorDTO fornecedor) {
        this.idFornecedor = fornecedor.id();
        tfNomeFornecedor.setText(fornecedor.getNome());
        tfTelefoneFornecedor.setText(fornecedor.getTel());
        tfCNPJFornecedor.setText(fornecedor.getCnpj());
        tfLogradouroFornecedor.setText(fornecedor.getLogradouro());
        tfNumeroFornecedor.setText(String.valueOf(fornecedor.getNumero()));
        tfCEPFornecedor.setText(fornecedor.getCep());
        tfComplementoFornecedor.setText(fornecedor.getComplemento());
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(tfCEPFornecedor.getText().isEmpty())
            message = "O Campo 'CEP' é obrigatório";
        if(tfNumeroFornecedor.getText().isEmpty())
            message = "O Campo 'Número' é obrigatório";
        if(tfLogradouroFornecedor.getText().isEmpty())
            message = "O Campo 'Logradouro' é obrigatório";
        if(tfCNPJFornecedor.getText().isEmpty())
            message = "O Campo 'CNPJ' é obrigatório";
        if(tfTelefoneFornecedor.getText().isEmpty())
            message = "O Campo 'Telefone' é obrigatório";
        if(tfNomeFornecedor.getText().isEmpty())
            message = "O Campo 'Nome' é obrigatório";

        if(message.isEmpty())
            return true;
        else {
            criarErrorAlert("Cadastro inválido!", message);
            return false;
        }
    }

    public void setCadastroFacade(CadastroFacade cadastroFacade) {
        this.cadastroFacade = cadastroFacade;
    }
    
}
