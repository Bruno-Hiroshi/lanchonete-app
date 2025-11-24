package fatec.lanchoneteapp.adapters.ui.categoria;

import java.net.URL;
import java.util.ResourceBundle;

import fatec.lanchoneteapp.application.dto.CategoriaDTO;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoriaController implements Initializable{

    private final CadastroFacade cadastroFacade;

    //Campos de busca
    @FXML private TextField tfBuscarCategoria;

    //Tabela
    @FXML private TableView<CategoriaDTO> tvListaCategorias;
    @FXML private TableColumn<CategoriaDTO, Integer> tcIDCategoria; 
    @FXML private TableColumn<CategoriaDTO, String> tcNomeCategoria;
    @FXML private TableColumn<CategoriaDTO, String> tcDescricaoCategoria;
    @FXML private ObservableList<CategoriaDTO> categoriasObservableList;

    public CategoriaController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML


}
