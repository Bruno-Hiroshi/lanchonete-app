package fatec.lanchoneteapp.adapters.ui.categoria;

import java.sql.SQLException;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.CategoriaDTO;
import fatec.lanchoneteapp.application.exception.CategoriaInvalidaException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoriaFormController extends Controller implements IFormController<CategoriaDTO> {

    private CadastroFacade cadastroFacade;

    //Botões
    @FXML private Button btnVoltarCategoria;

    //Campos
    private int idCategoria;
    @FXML private TextField tfNomeCategoria;
    @FXML private TextField tfDescricaoCategoria;

    @FXML
    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarCategoria.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;
        
        if(idCategoria > 0) {
            // Atualizar Categoria existente
            CategoriaDTO categoriaDTO = new CategoriaDTO(
                idCategoria,
                tfNomeCategoria.getText(),
                tfDescricaoCategoria.getText()
            );

            try {
                cadastroFacade.atualizarCategoria(categoriaDTO);
                criarInfoAlert("Sucesso!", "Categoria atualizada com sucesso");
                onVoltarClick();
            } catch (CategoriaInvalidaException e) {
                criarErrorAlert("Categoria inválida!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
            // Criar nova Categoria
            CategoriaDTO categoriaDTO = new CategoriaDTO(
                0,
                tfNomeCategoria.getText(),
                tfDescricaoCategoria.getText()
            );

            try {
                cadastroFacade.novaCategoria(categoriaDTO);
                criarInfoAlert("Sucesso!", "Categoria inserida com sucesso");
                onVoltarClick();
            } catch (CategoriaInvalidaException e) {
                criarErrorAlert("Categoria inválida!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(CategoriaDTO t) {
        this.idCategoria = t.id();
        tfNomeCategoria.setText(t.nome());
        tfDescricaoCategoria.setText(t.descricao());
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(tfDescricaoCategoria.getText().isEmpty())
            message = "O Campo 'Descrição' é obrigatório";
        if(tfNomeCategoria.getText().isEmpty())
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
