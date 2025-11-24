package fatec.lanchoneteapp.adapters.ui.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.FornecedorDTO;
import fatec.lanchoneteapp.application.exception.ClienteNaoEncontradoException;
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

public class FornecedorController extends Controller implements Initializable, IController<FornecedorDTO> {

    private final CadastroFacade cadastroFacade;

    //Campos de busca
    @FXML private TextField tfBuscarFornecedor;

    //Tabela
    @FXML private TableView<FornecedorDTO> tvListaFornecedores;
    @FXML private TableColumn<FornecedorDTO, Integer> tcIDFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcNomeFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcCNPJFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcTelefoneFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcLogradouroFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcNumeroFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcCEPFornecedor;
    @FXML private TableColumn<FornecedorDTO, String> tcComplementoFornecedor;
    @FXML private TableColumn<FornecedorDTO, Void> tcAcoesFornecedor;
    @FXML private ObservableList<FornecedorDTO> fornecedoresObservableList;

    public FornecedorController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        fornecedoresObservableList = FXCollections.observableArrayList();
        tvListaFornecedores.setItems(fornecedoresObservableList);
        
        carregarTabela();
    }

    @FXML
    Callback<TableColumn<FornecedorDTO, Void>, TableCell<FornecedorDTO, Void>> fabricanteColunaAcoes =
    ( param ) -> new TableCell<>() {
        private Button btnApagar = new Button("Apagar");
        private Button btnEditar = new Button("Editar");

        {
            btnApagar.setOnAction(click -> {
                    onRemoverClick(tvListaFornecedores.getItems().get(getIndex()));
                }
            );

            btnEditar.setOnAction(click -> {
                    try {
                        onAtualizarClick(tvListaFornecedores.getItems().get(getIndex()));
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
                setGraphic( new HBox(btnEditar, btnApagar) );
            } else {
                setGraphic( null );
            }
        }
    };

    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cliente/CadastroFornecedor.fxml"
        ));
        Parent form = loader.load();

        FornecedorFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Novo Fornecedor");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onAtualizarClick(FornecedorDTO fornecedor) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cliente/CadastroFornecedor.fxml"
        ));
        Parent form = loader.load();

        FornecedorFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(fornecedor);

        Stage stage = new Stage();
        stage.setTitle("Atualizar Fornecedor");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onRemoverClick(FornecedorDTO fornecedor) {
        try {
            cadastroFacade.removerFornecedor(fornecedor.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @Override
    public void onBuscarClick() {
        if(tfBuscarFornecedor.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            fornecedoresObservableList.clear();
            fornecedoresObservableList.addAll(
                    cadastroFacade.buscarFornecedor(Integer.parseInt(tfBuscarFornecedor.getText()))
            );
        } catch (ClienteNaoEncontradoException e) {
            criarWarningAlert("Fornecedor não encontrado", e.getMessage());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void carregarTabela() {
        tcIDFornecedor.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomeFornecedor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcTelefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tcCNPJFornecedor.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcLogradouroFornecedor.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        tcNumeroFornecedor.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tcCEPFornecedor.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcComplementoFornecedor.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        tcAcoesFornecedor.setCellFactory(fabricanteColunaAcoes);

        try {
            fornecedoresObservableList.clear();
            fornecedoresObservableList.addAll(cadastroFacade.listarFornecedores().stream().toList());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }
}
