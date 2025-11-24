package fatec.lanchoneteapp.adapters.ui.categoria;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.CategoriaDTO;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CategoriaController extends Controller implements Initializable, IController<CategoriaDTO>{

    private final CadastroFacade cadastroFacade;

    //Campos de busca
    @FXML private TextField tfBuscarCategoria;

    //Tabela
    @FXML private TableView<CategoriaDTO> tvListaCategorias;
    @FXML private TableColumn<CategoriaDTO, Integer> tcIDCategoria; 
    @FXML private TableColumn<CategoriaDTO, String> tcNomeCategoria;
    @FXML private TableColumn<CategoriaDTO, String> tcDescricaoCategoria;
    @FXML private TableColumn<CategoriaDTO, Void> tcAcoesCategoria;
    @FXML private ObservableList<CategoriaDTO> categoriasObservableList;

    public CategoriaController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriasObservableList = FXCollections.observableArrayList();
        tvListaCategorias.setItems(categoriasObservableList);

        carregarTabela();
    }

    @FXML
    Callback<TableColumn<CategoriaDTO, Void>, TableCell<CategoriaDTO, Void>> fabricanteColunaAcoes =
    ( param ) -> new TableCell<>() {
        private Button btnApagar = new Button("Apagar");
        private Button btnEditar = new Button("Editar");
    
        { 
            btnApagar.setOnAction(click -> { 
                    onRemoverClick(tvListaCategorias.getItems().get(getIndex()));
                }
            );

            btnEditar.setOnAction(click -> { 
                    try {
                        onAtualizarClick(tvListaCategorias.getItems().get(getIndex()));
                    } catch (IOException e) {
                        criarErrorAlert("Ocorreu um erro", e.getMessage());
                    }
                }
            );
        }

        @Override
        public void updateItem(Void item, boolean empty) { 
            super.updateItem(item, empty);
            if (!empty) { 
                setGraphic( new HBox(btnApagar, btnEditar) );
            } else { 
                setGraphic( null );
            }
        }
    };

    @FXML
    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cargo/CadastroCategoria.fxml"
        ));
        Parent form = loader.load();

        CategoriaFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Nova Categoria");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onAtualizarClick(CategoriaDTO categoria) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cargo/CadastroCategoria.fxml"
        ));
        Parent form = loader.load();

        CategoriaFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(categoria);

        Stage stage = new Stage();
        stage.setTitle("Nova Categoria");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onRemoverClick(CategoriaDTO categoria) {
         try {
            cadastroFacade.removerCategoria(categoria.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void onBuscarClick() {
        if(tfBuscarCategoria.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            categoriasObservableList.clear();
            categoriasObservableList.addAll(
                cadastroFacade.buscarCategoria(Integer.parseInt(tfBuscarCategoria.getText()))
            );
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void carregarTabela() {
        tcIDCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaDTO, Integer>("id"));
        tcNomeCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaDTO, String>("nome"));
        tcDescricaoCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaDTO, String>("descricao"));
        tcAcoesCategoria.setCellFactory(fabricanteColunaAcoes);

        try {
            categoriasObservableList.clear();
            categoriasObservableList.addAll(cadastroFacade.listarCategorias());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }
}
