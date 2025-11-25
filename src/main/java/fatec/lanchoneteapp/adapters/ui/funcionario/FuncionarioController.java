package fatec.lanchoneteapp.adapters.ui.funcionario;

import fatec.lanchoneteapp.adapters.ui.cliente.ClienteFormController;
import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.FuncionarioDTO;
import fatec.lanchoneteapp.application.exception.ClienteNaoEncontradoException;
import fatec.lanchoneteapp.application.exception.FuncionarioNaoEncontradoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FuncionarioController extends Controller implements Initializable, IController<FuncionarioDTO> {

    private final CadastroFacade cadastroFacade;

    @FXML
    private TextField tfBuscarFuncionario;

    //Tabela
    @FXML private TableView<FuncionarioDTO> tvListaFuncionarios;
    @FXML private TableColumn<FuncionarioDTO, Integer> tcIDFuncionario;
    @FXML private TableColumn<FuncionarioDTO, String> tcNomeFuncionario;
    @FXML private TableColumn<FuncionarioDTO, String> tcTelefoneFuncionario;
    @FXML private TableColumn<FuncionarioDTO, String> tcEmailFuncionario;
    @FXML private TableColumn<FuncionarioDTO, LocalDate> tcDataContratoFuncionario;
    @FXML private TableColumn<FuncionarioDTO, String> tcCargoFuncionario;
    @FXML private TableColumn<FuncionarioDTO, Double> tcSalarioFuncionario;
    @FXML private TableColumn<FuncionarioDTO, Void> tcAcoesFuncionario;
    @FXML private ObservableList<FuncionarioDTO> funcionariosObservableList;

    public FuncionarioController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @FXML
    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/funcionario/CadastroFuncionario.fxml"
        ));
        Parent form = loader.load();

        FuncionarioFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Novo Funcionário");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onAtualizarClick(FuncionarioDTO funcionarioDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/funcionario/CadastroFuncionario.fxml"
        ));
        Parent form = loader.load();

        FuncionarioFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(funcionarioDTO);

        Stage stage = new Stage();
        stage.setTitle("Atualizar Funcionario");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onRemoverClick(FuncionarioDTO funcionario) {
        try {
            cadastroFacade.removerFuncionario(funcionario.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void onBuscarClick() {
        if(tfBuscarFuncionario.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            funcionariosObservableList.clear();
            funcionariosObservableList.addAll(
                    cadastroFacade.buscarFuncionario(Integer.parseInt(tfBuscarFuncionario.getText()))
            );
        } catch (FuncionarioNaoEncontradoException e) {
            criarWarningAlert("Aviso", e.getMessage());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    Callback<TableColumn<FuncionarioDTO, Void>, TableCell<FuncionarioDTO, Void>> fabricanteColunaAcoes =
            ( param ) -> new TableCell<>() {
                private Button btnApagar = new Button("Apagar");
                private Button btnEditar = new Button("Editar");

                {
                    btnApagar.setOnAction(click -> {
                                onRemoverClick(tvListaFuncionarios.getItems().get(getIndex()));
                            }
                    );
                    btnApagar.setPrefWidth(100);

                    btnEditar.setOnAction(click -> {
                                try {
                                    onAtualizarClick(tvListaFuncionarios.getItems().get(getIndex()));
                                } catch (IOException e) {
                                    criarErrorAlert("Ocorreu um erro", e.getMessage());
                                }
                            }
                    );
                    btnEditar.setPrefWidth(100);
                }

                private final HBox hbox = new HBox(5, btnEditar, btnApagar);
                {
                    hbox.setStyle("-fx-alignment: CENTER;");
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic( hbox );
                    } else {
                        setGraphic( null );
                    }
                }
            };

    @Override
    public void carregarTabela() {
        tcIDFuncionario.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcTelefoneFuncionario.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tcEmailFuncionario.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcDataContratoFuncionario.setCellValueFactory(new PropertyValueFactory<>("dataContrato"));
        tcCargoFuncionario.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tcSalarioFuncionario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tcAcoesFuncionario.setCellFactory(fabricanteColunaAcoes);

        try {
            funcionariosObservableList.clear();
            funcionariosObservableList.addAll(cadastroFacade.listarFuncionarios().stream().toList());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcionariosObservableList = FXCollections.observableArrayList();
        tvListaFuncionarios.setItems(funcionariosObservableList);

        carregarTabela();
    }
}
